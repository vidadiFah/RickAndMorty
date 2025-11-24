package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.domain.models.Character

class CharactersAdapter(
    val onClick: (id: Int) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private val characters = mutableListOf<Character>()

    fun setData(newCharacters: List<Character>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        position: Int
    ) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) = with(binding) {
            tvName.text = character.name
            tvStatus.text = character.status
            tvSpecies.text = " - ${character.species}"
            tvLocation.text = character.location.name
            tvFirstSeen.text = character.episode.first()
            ivCharacterImage.load(character.image)

            itemView.setOnClickListener {
                character.id.let {
                    onClick(it)
                }
            }
        }
    }
}