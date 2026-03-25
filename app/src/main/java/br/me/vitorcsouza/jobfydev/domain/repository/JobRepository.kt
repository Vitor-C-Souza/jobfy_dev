package br.me.vitorcsouza.jobfydev.domain.repository

import br.me.vitorcsouza.jobfydev.domain.model.Job

interface JobRepository {
    suspend fun getRemoteJobs(): Result<List<Job>>
}
