package com.example.rickandmorty.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rickandmorty.Fragment.CharacterFragment
import com.example.rickandmorty.Fragment.EpisodeFragment
import com.example.rickandmorty.Fragment.LocationFragment
import com.example.rickandmorty.R
import com.example.rickandmorty.Services.Endpoints
import com.example.rickandmorty.Services.Services
import com.example.rickandmorty.Utils.NetworkUtils
import com.example.rickandmorty.VO.Character.ResultCharacterVO
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.character -> {
                replaceFragment(CharacterFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.location -> {
                replaceFragment(LocationFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.episode -> {
                replaceFragment(EpisodeFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(CharacterFragment())
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    fun getCharacters(){
        val api = NetworkUtils.getRetrofitInstance(Services.getBaseUrl())
        val endpoint = api.create(Endpoints::class.java)
        val callback = endpoint.getCharacters()

        callback.enqueue(object : Callback<ResultCharacterVO>{
            override fun onFailure(call: Call<ResultCharacterVO>, t: Throwable) {
                var nice = ""
            }

            override fun onResponse(call: Call<ResultCharacterVO>, response: Response<ResultCharacterVO>) {
                var nice = ""
            }

        })
    }
}
