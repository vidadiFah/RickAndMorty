package com.example.rickandmorty.presentation.detailed

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
import coil3.request.crossfade
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityDetailedBinding
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.presentation.base.BaseActivity
import com.example.rickandmorty.presentation.viewModel.DetailCharacterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailedBinding
    private val viewModel: DetailCharacterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getIntExtra("id", -1)
        if (id != -1) {
            viewModel.getCharacterById(id)
        } else {
            Toast.makeText(this, "Error: Character ID missing", Toast.LENGTH_SHORT).show()
            finish()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.characterState.handleState(
            onLoading = {},
            onSuccess = {
                setData(it)
            }
        )
    }

    private fun setData(character: Character){
        character?.let {
            with(binding) {
                tvNameDt.text = it.name
                tvStatusDt.text = it.status
                tvSpeciesDt.text = " - ${it.species}"
                tvGenderDt.text = it.gender
                tvLocationDt.text = it.location.name
                tvFirstSeenDt.text = it.origin.name
                tvEpisodes.text = it.episode.toString()

                ivCharacterImage.load(it.image) {
                    crossfade(true)
                }

                val statusColor = when (it.status.lowercase()) {
                    "alive" -> R.color.green
                    "dead" -> R.color.red
                    else -> R.color.tv_gray
                }
                lifeStatusCircleDt.setColorFilter(
                    ContextCompat.getColor(this@DetailedActivity, statusColor)
                )
            }
        }
    }
}
