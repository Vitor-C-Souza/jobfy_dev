package br.me.vitorcsouza.jobifydev.domain.repository

import br.me.vitorcsouza.jobifydev.domain.model.Job


interface JobRepository {
    suspend fun getRemoteJobs(): Result<List<Job>>
}