package br.me.vitorcsouza.jobfydev.ui.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.me.vitorcsouza.jobfydev.domain.usecase.GetJobByIdUseCase
import br.me.vitorcsouza.jobfydev.domain.usecase.IsJobFavoriteUseCase
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
class JobDetailsViewModel @Inject constructor(
    private val getJobByIdUseCase: GetJobByIdUseCase,
    private val isJobFavoriteUseCase: IsJobFavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(JobDetailsStates())
    val state = _state.asStateFlow()

    private val jobId: Long? = savedStateHandle.get<Long>("jobId")

    init {
        loadJobFromCache()
        observeFavoriteStatus()
    }

    private fun loadJobFromCache() {
        jobId?.let { id ->
            val cachedJob = getJobByIdUseCase(id)
            if (cachedJob != null) {
                _state.update { it.copy(job = cachedJob, isLoading = false) }
            } else {
                _state.update { it.copy(error = "Job not found in cache", isLoading = false) }
            }
        }
    }

    private fun observeFavoriteStatus() {
        jobId?.let { id ->
            isJobFavoriteUseCase(id).onEach { isFavorite ->
                _state.update { it.copy(isFavorite = isFavorite) }
            }.launchIn(viewModelScope)
        }
    }

    fun onToggleFavorite() {
        viewModelScope.launch {
            _state.value.job?.let { job ->
                toggleFavoriteUseCase(job, _state.value.isFavorite)
            }
        }
    }
}
