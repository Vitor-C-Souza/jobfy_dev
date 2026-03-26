package br.me.vitorcsouza.jobfydev.domain.usecase

import br.me.vitorcsouza.jobfydev.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsJobFavoriteUseCase @Inject constructor(
    private val repository: JobRepository
) {
    operator fun invoke(jobId: Long): Flow<Boolean> {
        return repository.isJobFavorite(jobId)
    }
}
