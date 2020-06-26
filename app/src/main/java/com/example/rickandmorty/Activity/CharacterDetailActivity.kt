package com.example.rickandmorty.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.Services.Endpoints
import com.example.rickandmorty.Services.Services
import com.example.rickandmorty.Utils.NetworkUtils
import com.example.rickandmorty.VO.Character.CharacterVO
import com.example.rickandmorty.VO.Character.ResultCharacterVO
import kotlinx.android.synthetic.main.activity_character_detail.*
import kotlinx.android.synthetic.main.activity_character_detail.tvStatus
import kotlinx.android.synthetic.main.card_character.*
import kotlinx.android.synthetic.main.card_character.view.*
import kotlinx.android.synthetic.main.card_character.view.tvName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        var id = intent.getIntExtra("id", 0)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getCharacter(id)
    }

    fun getCharacter(id : Int){
        val api = NetworkUtils.getRetrofitInstance(Services.getBaseUrl())
        val endpoint = api.create(Endpoints::class.java)
        val callback = endpoint.getCharacterDetail(id)

        callback.enqueue(object : Callback<CharacterVO> {
            override fun onFailure(call: Call<CharacterVO>, t: Throwable) {
                var nice = ""
            }

            override fun onResponse(call: Call<CharacterVO>, response: Response<CharacterVO>) {
                var characterVO = response.body()
                characterVO?.let { setupView(it) }
            }
        })
    }

    fun setupView(characterVO: CharacterVO){
        Glide.with(this).load(characterVO.image).into(ivCharacter)
        tvStatus.text = characterVO.status
        tvNome.text = characterVO.name
        tvSpecie.text = characterVO.species
        tvGender.text = characterVO.gender
        tvOrigin.text = characterVO.origin?.name
        tvType.text = characterVO.type
        tvLocation.text = characterVO.location?.name
        supportActionBar?.title = characterVO.name
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
