package br.me.vitorcsouza.jobfydev.ui.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.domain.usecase.GetFavoriteJobsUseCase
import br.me.vitorcsouza.jobfydev.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SavedJobsState(
    val jobs: List<Job> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class SavedJobsViewModel @Inject constructor(
    private val getFavoriteJobsUseCase: GetFavoriteJobsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SavedJobsState())
    val state = _state.asStateFlow()

    init {
        observeSavedJobs()
    }

    private fun observeSavedJobs() {
        getFavoriteJobsUseCase().onEach { jobs ->
            _state.update { it.copy(jobs = jobs, isLoading = false) }
        }.launchIn(viewModelScope)
    }

    fun onRemoveFavorite(job: Job) {
        viewModelScope.launch {
            toggleFavoriteUseCase(job, isFavorite = true)
        }
    }

    fun onClearAll() {
        viewModelScope.launch {
            _state.value.jobs.forEach { job ->
                toggleFavoriteUseCase(job, isFavorite = true)
            }
        }
    }
}
