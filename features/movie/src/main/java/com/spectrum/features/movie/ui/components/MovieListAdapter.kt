package com.spectrum.features.movie.ui.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.spectrum.feature.movie.databinding.ItemMovieCardBinding
import com.spectrum.features.movie.ui.components.MovieListAdapter.MovieItemViewHolder
import com.spectrum.features.movie.utils.ImageSourceSelector
import com.spectrum.features.movie.utils.PosterSize

fun interface OnMovieSelected {
    operator fun invoke(movie: MovieUiModel)
}

class MovieListAdapter constructor(
    private val onMovieClicked: OnMovieSelected
) : PagingDataAdapter<MovieUiModel, MovieItemViewHolder>(movieComparator) {

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

    inner class MovieItemViewHolder constructor(
        private val binding: ItemMovieCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(movie: MovieUiModel) {
            with(binding) {
                tvMovieName.text = movie.title
                chipAdult.isVisible = movie.adult ?: false
                tvVoteAvg.text = movie.voteAverage.toString()
                tvLanguage.text = movie.originalLanguage
                tvReleaseDate.text = movie.releaseDate
                loadPosterImage(movie)
            }

            binding.root.setOnClickListener { onMovieClicked(movie) }
        }

        private fun loadPosterImage(movie: MovieUiModel) {
            val posterUrl = ImageSourceSelector.getImageUrl(movie.posterPath, PosterSize.Width342)
            binding.imgPoster.load(posterUrl)
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
