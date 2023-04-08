package com.alejandro.kotlinvideoteca.adapter

import androidx.recyclerview.widget.RecyclerView
import com.alejandro.kotlinvideoteca.databinding.ItemMovieBinding
import com.alejandro.kotlinvideoteca.model.Movie
import com.alejandro.kotlinvideoteca.utils.loadImage

class MoviesViewHolder(private val itemBinding: ItemMovieBinding):
RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Movie) {
        itemBinding.movieTitle.text = movie.name
        itemBinding.movieCover.loadImage(movie.cover)
    }
}