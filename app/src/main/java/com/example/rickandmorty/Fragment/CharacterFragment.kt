package com.example.rickandmorty.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCharacterList()
    }

    fun getCharacterList(){
        val api = NetworkUtils.getRetrofitInstance(Services.getBaseUrl())
        val endpoint = api.create(Endpoints::class.java)
        val callback = endpoint.getCharacters()

        callback.enqueue(object : Callback<ResultCharacterVO> {
            override fun onFailure(call: Call<ResultCharacterVO>, t: Throwable) {
                var nice = ""
            }

            override fun onResponse(call: Call<ResultCharacterVO>, response: Response<ResultCharacterVO>) {
                var resultCharacterVO = response.body()
                var list = resultCharacterVO?.results
                initView(list as List<CharacterVO>)
            }

        })
    }

    fun initView(list: List<CharacterVO>){
        recyclerViewCharacters.layoutManager = GridLayoutManager(context, 2)
        val characterAdapter = CharacterAdapter(list)
        recyclerViewCharacters.adapter = characterAdapter
    }

}
