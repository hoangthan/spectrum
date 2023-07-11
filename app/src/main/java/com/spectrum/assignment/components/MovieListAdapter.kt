package com.spectrum.assignment.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.chip.Chip
import com.spectrum.assignment.R
import com.spectrum.assignment.components.MovieListAdapter.MovieItemViewHolder
import com.spectrum.assignment.databinding.ItemMovieCardBinding

class MovieListAdapter : PagingDataAdapter<MovieUiModel, MovieItemViewHolder>(movieComparator) {

    private lateinit var inflater: LayoutInflater

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movie = getItem(position) ?: return
        holder.bindData(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieCardBinding.inflate(inflater, parent, false)
        return MovieItemViewHolder(binding)
    }

    class MovieItemViewHolder constructor(
        private val binding: ItemMovieCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(movie: MovieUiModel) {
            binding.tvMovieName.text = movie.title
            binding.imgPoster.load("https://image.tmdb.org/t/p/w500/kVG8zFFYrpyYLoHChuEeOGAd6Ru.jpg")
            //binding.imgPoster.load(movie.posterPath)
            binding.chipAdult.isVisible = movie.adult
            binding.tvVoteAvg.text = movie.voteAverage.toString()
            binding.tvLanguage.text = movie.originalLanguage
            binding.tvReleaseDate.text = movie.releaseDate

            binding.chipGroupGenres.removeAllViews()
            binding.chipGroupContainer.isGone = movie.genres.isEmpty()

            movie.genres.forEach {
                val chip = Chip(binding.root.context, null, R.style.ChipGenresStyle)
                chip.text = it.name
                binding.chipGroupGenres.addView(chip)
            }
        }
    }

    companion object {
        val movieComparator = object : DiffUtil.ItemCallback<MovieUiModel>() {
            override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
