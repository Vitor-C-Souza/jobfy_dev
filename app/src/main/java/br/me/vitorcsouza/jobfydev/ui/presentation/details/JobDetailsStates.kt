package br.me.vitorcsouza.jobfydev.ui.presentation.details

import br.me.vitorcsouza.jobfydev.domain.model.Job

data class JobDetailsStates(
    val isLoading: Boolean = false,
    val job: Job? = null,
    val error: String? = null
)
