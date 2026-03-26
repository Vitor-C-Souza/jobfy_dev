package br.me.vitorcsouza.jobfydev.domain.usecase

import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.domain.repository.JobRepository
import javax.inject.Inject

class GetJobsUseCase @Inject constructor(
    private val repository: JobRepository
) {
    suspend operator fun invoke(): Result<List<Job>> {
        return repository.getRemoteJobs()
    }
}
