package com.example.rickandmorty.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.VO.Character.CharacterVO
import kotlinx.android.synthetic.main.card_character.view.*

class CharacterAdapter(var characterList: List<CharacterVO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_character, parent, false))
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val characterViewHolder = holder as CharacterViewHolder
        characterViewHolder.bindView(characterList[position])
    }
}

class CharacterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun bindView(characterList: CharacterVO) {
        itemView.tvStatus.text = characterList.status
        itemView.tvName.text = characterList.name

        Glide.with(itemView.context).load(characterList.image).into(itemView.ivCardCharacter)
    }
}