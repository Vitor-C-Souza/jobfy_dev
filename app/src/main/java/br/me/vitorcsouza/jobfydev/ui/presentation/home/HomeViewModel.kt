package br.me.vitorcsouza.jobfydev.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.domain.usecase.GetFavoriteJobsUseCase
import br.me.vitorcsouza.jobfydev.domain.usecase.GetJobsUseCase
import br.me.vitorcsouza.jobfydev.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getJobsUseCase: GetJobsUseCase,
    private val getFavoriteJobsUseCase: GetFavoriteJobsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeStates())
    val state = _state.asStateFlow()

    init {
        getJobs()
        observeFavorites()
    }

    private fun getJobs() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getJobsUseCase()
                .onSuccess { jobsList ->
                    val dynamicCategories = listOf("All") + jobsList.map { it.category }.distinct()
                    _state.update {
                        it.copy(
                            isLoading = false,
                            jobs = jobsList,
                            categories = dynamicCategories
                        )
                    }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    private fun observeFavorites() {
        getFavoriteJobsUseCase().onEach { favorites ->
            _state.update { it.copy(favoriteJobIds = favorites.map { job -> job.id }.toSet()) }
        }.launchIn(viewModelScope)
    }

    fun onSearchQueryChange(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    fun onFilterSelected(category: String) {
        _state.update { it.copy(selectedFilter = category) }
    }

    fun onToggleFavorite(job: Job) {
        viewModelScope.launch {
            val isFavorite = _state.value.favoriteJobIds.contains(job.id)
            toggleFavoriteUseCase(job, isFavorite)
        }
    }
}
