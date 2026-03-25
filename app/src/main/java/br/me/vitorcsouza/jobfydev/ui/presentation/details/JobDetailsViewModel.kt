package br.me.vitorcsouza.jobfydev.ui.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.me.vitorcsouza.jobfydev.domain.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val repository: JobRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(JobDetailsStates())
    val state = _state.asStateFlow()

    private val jobId: Long? = savedStateHandle.get<Long>("jobId")

    init {
        loadJobFromCache()
    }

    private fun loadJobFromCache() {
        jobId?.let { id ->
            val cachedJob = repository.getJobById(id)
            if (cachedJob != null) {
                _state.update { it.copy(job = cachedJob, isLoading = false) }
            } else {
                _state.update { it.copy(error = "Job not found in cache", isLoading = false) }
            }
        }
    }
}