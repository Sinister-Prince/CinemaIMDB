package com.example.kinorecyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinorecyclerview.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter(
     val callback: (Movie) -> Unit
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private val movies: MutableList<Movie> = mutableListOf()

    fun setMovies(newMovies:List<Movie>){
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged() // дает понять адаптору что данные поменялись
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie){
            itemView.movie_title.text=movie.title
            itemView.movie_release_date.text = movie.release
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_poster)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
        holder.itemView.setOnClickListener{
            callback.invoke(movies.get(position)) //вызвать
        }
    }

    override fun getItemCount(): Int = movies.size
    }

