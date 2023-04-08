package com.alejandro.kotlinvideoteca.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.alejandro.kotlinvideoteca.adapter.MoviesAdapter
import com.alejandro.kotlinvideoteca.databinding.ActivityMainBinding
import com.alejandro.kotlinvideoteca.model.Movie
import com.alejandro.kotlinvideoteca.utils.getJsonFromAssets
import com.google.gson.Gson
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MoviesAdapter

    private val copyList = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View binding setup
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = MoviesAdapter(::onMovieClicked)
        binding.recyclerView.adapter = adapter

        adapter.refreshList(getListFromJson())

        binding.searchField.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val filteredList = copyList.filter { it.name.toLowerCase(Locale.getDefault()).contains(newText) }
                    adapter.filterByName(filteredList)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }
        })

        binding.btnSort.setOnClickListener {
            adapter.orderByName()
            // TODO: Create UI and logic for CRUD operations
            //adapter.insertObject()
            //adapter.deleteObject()
            //adapter.modifyObject()
        }
    }

    private fun getListFromJson(): ArrayList<Movie> {
        var movieList = arrayListOf<Movie>()
        val json: String? = getJsonFromAssets("movies.json")
        json?.let {
            movieList = ArrayList(Gson().fromJson(json, Array<Movie>::class.java).toList())
            copyList.addAll(movieList)  // Updates copyList for searching
        }
        return ArrayList(movieList)
    }

    private fun onMovieClicked(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}