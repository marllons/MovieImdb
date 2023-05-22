package com.marllons.movieimdb.presenter.ui.fragment

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.marllons.movieimdb.R
import com.marllons.movieimdb.databinding.FragmentMainBinding
import com.marllons.movieimdb.presenter.ui.adapter.MovieAdapter
import com.marllons.movieimdb.presenter.ui.adapter.MovieLoaderAdapter
import com.marllons.movieimdb.presenter.vm.MainViewModel
import com.marllons.movieimdb.utils.setOnBackPressedListener
import com.marllons.movieimdb.utils.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
    private var binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.listUiMovies.observe(viewLifecycleOwner) { flow ->
            lifecycleScope.launch {
                flow.collectLatest {
                    movieAdapter.submitData(it)
                }
            }
        }
    }

    private fun setupUi() {
        binding.run {
            setupAdapter()

            searchEditText.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.getListUiMovies(textView.text.toString())
                }
                false
            }

            searchInput.setEndIconOnClickListener {
                handleInitial()
                searchEditText.setText("")
            }
        }
    }

    private fun FragmentMainBinding.setupAdapter() {
        movieAdapter =  MovieAdapter {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailsFragment(it)
            )
        }
        with(recyclerView) {
            scrollToPosition(0)
            adapter = movieAdapter.withLoadStateFooter(MovieLoaderAdapter())
        }
        initialViewState()
    }

    private fun FragmentMainBinding.initialViewState() {
        lifecycleScope.launch {
            movieAdapter.loadStateFlow.collectLatest { state ->
                when (state.refresh) {
                    is LoadState.NotLoading -> handleSuccess()
                    LoadState.Loading -> isLoading()
                    is LoadState.Error -> handleError()
                }
            }
        }
    }

    private fun FragmentMainBinding.handleError() {
        layoutError.root.isVisible = true
        progress.root.isVisible = false
        recyclerView.isVisible = false
        layoutInitial.root.isVisible = false

        layoutError.tryAgainButton.setOnClickListener {
            movieAdapter.refresh()
        }
    }

    private fun FragmentMainBinding.handleSuccess() {
        layoutError.root.isVisible = false
        progress.root.isVisible = false
        recyclerView.isVisible = true
        layoutInitial.root.isVisible = false
    }

    private fun FragmentMainBinding.isLoading() {
        layoutError.root.isVisible = false
        progress.root.isVisible = true
        recyclerView.isVisible = false
        layoutInitial.root.isVisible = false
    }

    private fun FragmentMainBinding.handleInitial() {
        layoutError.root.isVisible = false
        progress.root.isVisible = false
        recyclerView.isVisible = false
        layoutInitial.root.isVisible = true
    }

}


