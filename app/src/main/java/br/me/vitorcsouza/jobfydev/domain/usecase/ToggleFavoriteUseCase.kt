package br.me.vitorcsouza.jobfydev.domain.usecase

import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.domain.repository.JobRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: JobRepository
) {
    suspend operator fun invoke(job: Job, isFavorite: Boolean) {
        if (isFavorite) {
            repository.deleteFavoriteJob(job)
        } else {
            repository.insertFavoriteJob(job)
        }
    }
}
