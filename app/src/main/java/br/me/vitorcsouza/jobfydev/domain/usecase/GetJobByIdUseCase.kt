package br.me.vitorcsouza.jobfydev.domain.usecase

import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.domain.repository.JobRepository
import javax.inject.Inject

class GetJobByIdUseCase @Inject constructor(
    private val repository: JobRepository
) {
    operator fun invoke(id: Long): Job? {
        return repository.getJobById(id)
    }
}
