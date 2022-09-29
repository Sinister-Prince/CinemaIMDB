package com.example.kinorecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kinorecyclerview.databinding.ActivityMovieDetailsBinding


class MovieDetailsActivity: AppCompatActivity() {
private lateinit var binding: ActivityMovieDetailsBinding
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.MovieTitle2.text = intent.getStringExtra("Title")
        binding.MovieReleaseData2.text = intent.getStringExtra("ReleaseDate")
        binding.MovieOverview.text = intent.getStringExtra("Overview")
        Glide.with(binding.MoviePoster2).load(IMAGE_BASE + intent.getStringExtra("Poster")).into(binding.MoviePoster2)
        binding.button.setOnClickListener{
            finish()
        }

    }

}