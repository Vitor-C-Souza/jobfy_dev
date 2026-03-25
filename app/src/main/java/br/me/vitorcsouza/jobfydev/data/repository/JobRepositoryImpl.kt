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

    private var cachedJobs: List<Job> = emptyList()

    override suspend fun getRemoteJobs(): Result<List<Job>> {
        return try {
            val response = api.getJobs()
            val domainJobs = response.jobs.map { it.toDomain() }
            cachedJobs = domainJobs
            success(domainJobs)
        } catch (e: Exception) {
            if (cachedJobs.isNotEmpty()) success(cachedJobs)
            else Result.failure(e)
        }
    }

    override fun getJobById(id: Long): Job? {
        return cachedJobs.find { it.id == id }
    }


}
