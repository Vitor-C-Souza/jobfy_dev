package br.me.vitorcsouza.jobfydev.ui.presentation.home

import br.me.vitorcsouza.jobfydev.domain.model.Job


data class HomeStates(
    val isLoading: Boolean = false,
    val jobs: List<Job> = emptyList(),
    val categories: List<String> = listOf("All", "Remote", "Full-time", "Design"),
    val searchQuery: String = "",
    val selectedFilter: String = "All",
    val error: String? = null
)
