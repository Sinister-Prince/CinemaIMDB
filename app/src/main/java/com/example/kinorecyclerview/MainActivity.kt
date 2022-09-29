package com.example.kinorecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinorecyclerview.databinding.ActivityMainBinding
import com.example.kinorecyclerview.models.Movie
import com.example.kinorecyclerview.models.MovieResponse
import com.example.kinorecyclerview.services.MovieAPIService
import com.example.kinorecyclerview.services.MovieApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val API_KEY = "7d5be5fed494f8d015d1fcecd765ab93"
    val moviesAdapter = MovieAdapter(){
        val intent = Intent(this,MovieDetailsActivity::class.java)
        intent.putExtra("Title", it.title)
        intent.putExtra("ReleaseDate", it.release)
        intent.putExtra("Poster", it.poster)
        intent.putExtra("Overview", it.overview)
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rc_movie_list.layoutManager = LinearLayoutManager(this)
        rc_movie_list.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            moviesAdapter.setMovies(movies)
            rc_movie_list.adapter = moviesAdapter

        }
    }


    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieAPIService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovielist(API_KEY).enqueue(object: Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }

}