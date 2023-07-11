package com.spectrum.assignment.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.spectrum.assignment.R
import com.spectrum.assignment.components.GenresUiModel
import com.spectrum.assignment.components.MovieItemDecoration
import com.spectrum.assignment.components.MovieListAdapter
import com.spectrum.assignment.components.MovieUiModel
import com.spectrum.assignment.databinding.FragmentNowPlayingBinding
import com.spectrum.features.core.utils.viewBinding
import kotlinx.coroutines.launch


class NowPlayingFragment : Fragment(R.layout.fragment_now_playing) {

    private val binding by viewBinding(FragmentNowPlayingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = MovieListAdapter()
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rcvPlayingMovie.adapter = adapter
        binding.rcvPlayingMovie.setHasFixedSize(true)
        binding.rcvPlayingMovie.layoutManager = layoutManager

        val margin = com.spectrum.features.core.R.dimen.margin_regular
        val marginDimen = resources.getDimensionPixelSize(margin)
        binding.rcvPlayingMovie.addItemDecoration(MovieItemDecoration(marginDimen))

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitData(PagingData.from(getFakeData()))
        }
    }

    private fun getFakeData(): List<MovieUiModel> {
        return listOf(
            MovieUiModel(
                adult = false,
                backdropPath = "Jackeline",
                genres = listOf(GenresUiModel(1, "Action"), GenresUiModel(2, "Thrill")),
                id = 1538,
                originalLanguage = "Enoch",
                originalTitle = "Lezlie",
                overview = "Jospeh",
                popularity = 1.870,
                posterPath = "Jeanpierre",
                releaseDate = "Trebor",
                title = "Shakeita",
                video = false,
                voteAverage = 7.640,
                voteCount = 4823
            ),
            MovieUiModel(
                adult = true,
                backdropPath = "Shakira",
                genres = listOf(),
                id = 383,
                originalLanguage = "Kayli",
                originalTitle = "Babak",
                overview = "Carah",
                popularity = 49.060,
                posterPath = "Gladis",
                releaseDate = "Abel",
                title = "Kiah",
                video = false,
                voteAverage = 89.951,
                voteCount = 6714
            ),
            MovieUiModel(
                adult = false,
                backdropPath = "Roxann",
                genres = listOf(),
                id = 8879,
                originalLanguage = "Franklyn",
                originalTitle = "Kori",
                overview = "Ronnie",
                popularity = 55.115,
                posterPath = "Apolonia",
                releaseDate = "Scottie",
                title = "Frederica",
                video = false,
                voteAverage = 11.005,
                voteCount = 743
            ),
            MovieUiModel(
                adult = false,
                backdropPath = "Thu",
                genres = listOf(),
                id = 198,
                originalLanguage = "Kerri",
                originalTitle = "Crystall",
                overview = "Maryrose",
                popularity = 13.129,
                posterPath = "Ila",
                releaseDate = "Myles",
                title = "Leslee",
                video = true,
                voteAverage = 10.913,
                voteCount = 9239
            ),
            MovieUiModel(
                adult = false,
                backdropPath = "Jannie",
                genres = listOf(),
                id = 8187,
                originalLanguage = "Reem",
                originalTitle = "Talena",
                overview = "Miguelangel",
                popularity = 71.687,
                posterPath = "Aleasha",
                releaseDate = "Darell",
                title = "Makayla",
                video = true,
                voteAverage = 41.571,
                voteCount = 2608
            ),
            MovieUiModel(
                adult = false,
                backdropPath = "Waylon",
                genres = listOf(),
                id = 9189,
                originalLanguage = "Narissa",
                originalTitle = "Adrain",
                overview = "Demario",
                popularity = 65.594,
                posterPath = "Jacklynn",
                releaseDate = "Shanaya",
                title = "Declan",
                video = true,
                voteAverage = 57.840,
                voteCount = 7708
            )
        )
    }
}
