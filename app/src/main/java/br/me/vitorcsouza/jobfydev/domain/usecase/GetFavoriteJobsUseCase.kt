package br.me.vitorcsouza.jobfydev.domain.usecase

import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteJobsUseCase @Inject constructor(
    private val repository: JobRepository
) {
    operator fun invoke(): Flow<List<Job>> {
        return repository.getAllFavoriteJobs()
    }
}
