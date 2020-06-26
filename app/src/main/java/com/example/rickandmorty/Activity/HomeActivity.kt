package com.example.rickandmorty.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
                supportActionBar?.title = "Character"
                replaceFragment(CharacterFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.location -> {
                supportActionBar?.title = "Location"
                replaceFragment(LocationFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.episode -> {
                supportActionBar?.title = "Episode"
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
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Character"

        replaceFragment(CharacterFragment())
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.filterCharacter -> {
                val intent = Intent(this, CharacterFilterActivity::class.java)
                startActivityForResult(intent, 0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null){
            if (resultCode == Activity.RESULT_OK){
                var search = data.getStringExtra("filter_values")
                if (!search.isEmpty()){
                    var fragment = CharacterFragment()
                    var args = Bundle()
                    args.putString("filter", search)
                    fragment.arguments = args
                    replaceFragment(fragment)
                }
            }
        }
    }
}
