package br.me.vitorcsouza.jobfydev.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.me.vitorcsouza.jobfydev.domain.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: JobRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeStates())
    val state = _state.asStateFlow()

    init {
        getJobs()
    }

    private fun getJobs() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            repository.getRemoteJobs()
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

    fun onSearchQueryChange(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    fun onFilterSelected(category: String) {
        _state.update { it.copy(selectedFilter = category) }
    }
}
