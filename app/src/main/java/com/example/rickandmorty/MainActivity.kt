package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickandmorty.Services.Endpoints
import com.example.rickandmorty.Services.Services
import com.example.rickandmorty.Utils.NetworkUtils
import com.example.rickandmorty.VO.ResultCharacterVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCharacters()
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
