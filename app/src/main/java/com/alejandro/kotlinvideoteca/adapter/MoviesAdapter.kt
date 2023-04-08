package com.alejandro.kotlinvideoteca.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.kotlinvideoteca.databinding.ItemMovieBinding
import com.alejandro.kotlinvideoteca.model.Movie
import kotlin.collections.ArrayList

class MoviesAdapter(private val listener: (Movie) -> Unit): RecyclerView.Adapter<MoviesViewHolder>() {

    private val movieList = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    override fun getItemCount(): Int = movieList.size

    fun refreshList(movieList: ArrayList<Movie>) {
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    fun filterByName(movies: List<Movie>) {
        this.movieList.clear()
        this.movieList.addAll(movies)
        notifyDataSetChanged()
    }

    fun orderByName() {
        val sortedList = this.movieList.sortedBy { it.name }
        this.movieList.clear()
        this.movieList.addAll(sortedList)
        notifyDataSetChanged()
    }

    fun insertObject() {
        // TODO Lógica de inserción de nuevos ítems según lo requiera la app
        val item = Movie(13, "Prueba", "Descripción",1986, "https://image.tmdb.org/t/p/original//9dlcOgehxDK4QaC2QDfqwQFbk5C.jpg")
        this.movieList.add(0, item)
        notifyItemInserted(0)
    }

    fun modifyObject() {
        // TODO Lógica de modificación de ítems según requiera la app.
        // La modificación requiere que las propiedades del objeto no sean inmutables
        // movieList[0].name = "Ooops"
        notifyItemChanged(0)
    }

    fun deleteObject() {
        // TODO Lógica de eliminación de ítems según lo requiera la app
        this.movieList.removeAt(0)
        notifyItemRemoved(0)
    }

}