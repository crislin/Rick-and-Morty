package com.example.rickandmorty.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Filter
import android.widget.RadioButton
import com.example.rickandmorty.R
import kotlinx.android.synthetic.main.activity_character_filter.*

class CharacterFilterActivity : AppCompatActivity() {

    var genderOption = ""
    var statusOption = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_filter)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Filter"

        setButtons()
    }

    fun getSearch() : String{
        var search = ""
        if (!etName.text.isEmpty()){
            search += "name=" + etName.text.toString().toLowerCase()
        }

        if (!etSpecies.text.isEmpty()){
            search += "species=" + etSpecies.text.toString().toLowerCase()
        }

        if (!genderOption.isEmpty()){
            if (search.isEmpty()){
                search += "gender=" + genderOption.toLowerCase()
            } else {
                search += "&gender=" + genderOption.toLowerCase()
            }
        }

        if (!statusOption.isEmpty()){
            if (search.isEmpty()){
                search += "status=" + statusOption.toLowerCase()
            } else {
                search += "&status=" + statusOption.toLowerCase()
            }
        }

        if (!search.isEmpty()){
            search = "?" + search
        }
        return search
    }


    fun setButtons(){
        btApply.setOnClickListener {
            var intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("filter_values", getSearch())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        rgGender.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            genderOption = radio.text.toString()
        }

        rgStatus.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            statusOption = radio.text.toString()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
