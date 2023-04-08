package com.alejandro.kotlinvideoteca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alejandro.kotlinvideoteca.databinding.ActivityDetailBinding
import com.alejandro.kotlinvideoteca.model.Movie
import com.alejandro.kotlinvideoteca.utils.loadImage

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View binding setup
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        retrieveMovie()
        renderUi()
    }

    private fun retrieveMovie() {
        movie = intent.getSerializableExtra("movie") as Movie?
    }

    private fun renderUi() {
        binding.detailName.text = movie?.name
        binding.detailDescription.text = movie?.description
        movie?.cover?.let { mCover ->
            binding.detailImage.loadImage(mCover)
        }
    }
}