package br.me.vitorcsouza.jobfydev.data.repository

import br.me.vitorcsouza.jobfydev.data.mapper.toDomain
import br.me.vitorcsouza.jobfydev.data.remote.RemotiveApi
import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.domain.repository.JobRepository
import javax.inject.Inject
import kotlin.Result.Companion.success

class JobRepositoryImpl @Inject constructor(
    private val api: RemotiveApi
) : JobRepository{
    override suspend fun getRemoteJobs(): Result<List<Job>> {
        return try {
            val response = api.getJobs()

            success(response.jobs.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}
