package com.example.rickandmorty.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Adapter.CharacterAdapter

import com.example.rickandmorty.R
import com.example.rickandmorty.Services.Endpoints
import com.example.rickandmorty.Services.Services
import com.example.rickandmorty.Utils.NetworkUtils
import com.example.rickandmorty.VO.Character.CharacterVO
import com.example.rickandmorty.VO.Character.ResultCharacterVO
import kotlinx.android.synthetic.main.fragment_character.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class CharacterFragment : Fragment() {

    var visibleItemCount : Int = 0
    var pastVisibleItemCount : Int = 0
    var totalItemCount : Int = 0
    var loading : Boolean = false
    var adapter : CharacterAdapter? = null
    var currentPosition : Int = 0
    lateinit var layoutManager : RecyclerView.LayoutManager
    lateinit var recyclerView : RecyclerView
    var characterList : MutableList<CharacterVO> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = GridLayoutManager(context, 2)
        recyclerView = recyclerViewCharacters
        recyclerView.layoutManager = layoutManager

        var result = arguments?.getString("filter")
        if (result != null){
            getCharacterListFirstPageSearch(result)
        } else {
            getCharacterListFirstPage()
        }

    }

    fun getCharacterListFirstPageSearch(search : String){
        progressBar.visibility = View.VISIBLE
        val api = NetworkUtils.getRetrofitInstance(Services.getBaseUrl())
        val endpoint = api.create(Endpoints::class.java)
        val callback = endpoint.getCharacterSearch(search)

        callback.enqueue(object : Callback<ResultCharacterVO> {
            override fun onFailure(call: Call<ResultCharacterVO>, t: Throwable) {
                progressBar.visibility = View.GONE
            }

            override fun onResponse(call: Call<ResultCharacterVO>, response: Response<ResultCharacterVO>) {
                progressBar.visibility = View.GONE
                loading = true
                var resultCharacterVO = response.body()
                var nextPage = resultCharacterVO?.info?.next?.replace("https://rickandmortyapi.com/api/character/?page=", "")?.toInt()
                var list = resultCharacterVO?.results
                if (nextPage != null) {
                    setUpAdapter(list as MutableList<CharacterVO>, nextPage)
                }
            }

        })
    }

    fun getCharacterListFirstPage(){
        progressBar.visibility = View.VISIBLE
        val api = NetworkUtils.getRetrofitInstance(Services.getBaseUrl())
        val endpoint = api.create(Endpoints::class.java)
        val callback = endpoint.getCharacters()

        callback.enqueue(object : Callback<ResultCharacterVO> {
            override fun onFailure(call: Call<ResultCharacterVO>, t: Throwable) {
                progressBar.visibility = View.GONE
            }

            override fun onResponse(call: Call<ResultCharacterVO>, response: Response<ResultCharacterVO>) {
                progressBar.visibility = View.GONE
                loading = true
                var resultCharacterVO = response.body()
                var nextPage = resultCharacterVO?.info?.next?.replace("https://rickandmortyapi.com/api/character/?page=", "")?.toInt()
                var list = resultCharacterVO?.results
                setUpAdapter(list as MutableList<CharacterVO>, nextPage)
            }

        })
    }

    fun getCharacterListNextPage(nextPage : Int?){
        progressBar.visibility = View.VISIBLE
        val api = NetworkUtils.getRetrofitInstance(Services.getBaseUrl())
        val endpoint = api.create(Endpoints::class.java)
        val callback = endpoint.getCharactersPage(nextPage)

        callback.enqueue(object : Callback<ResultCharacterVO> {
            override fun onFailure(call: Call<ResultCharacterVO>, t: Throwable) {
                progressBar.visibility = View.GONE
            }

            override fun onResponse(call: Call<ResultCharacterVO>, response: Response<ResultCharacterVO>) {
                progressBar.visibility = View.GONE
                loading = true
                var resultCharacterVO = response.body()
                var nextPage = resultCharacterVO?.info?.next?.replace("https://rickandmortyapi.com/api/character/?page=", "")?.toInt()
                var list = resultCharacterVO?.results
                setUpAdapter(list as MutableList<CharacterVO>, nextPage)
            }

        })
    }

    fun setUpAdapter(list: MutableList<CharacterVO>, nextPage: Int?){
        if (characterList.size == 0) {
            characterList.addAll(list)
            adapter = CharacterAdapter(characterList)
            recyclerViewCharacters.adapter = adapter
        } else {
            currentPosition = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            adapter?.notifyDataSetChanged()
            characterList.addAll(list)
            adapter = CharacterAdapter(characterList)
            recyclerViewCharacters.adapter = adapter
            recyclerViewCharacters.scrollToPosition(currentPosition)
        }
        recyclerViewCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0){
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItemCount = (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                    if (loading){
                        if ((visibleItemCount + pastVisibleItemCount) >= totalItemCount){
                            loading = false
                            getCharacterListNextPage(nextPage)
                        }
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

}
