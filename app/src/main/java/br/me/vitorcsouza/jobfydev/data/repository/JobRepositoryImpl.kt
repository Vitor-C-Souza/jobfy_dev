package br.me.vitorcsouza.jobfydev.data.repository

import br.me.vitorcsouza.jobfydev.data.local.FavoriteJobDao
import br.me.vitorcsouza.jobfydev.data.mapper.toDomain
import br.me.vitorcsouza.jobfydev.data.mapper.toFavoriteEntity
import br.me.vitorcsouza.jobfydev.data.remote.RemotiveApi
import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.Result.Companion.success

class JobRepositoryImpl @Inject constructor(
    private val api: RemotiveApi,
    private val favoriteJobDao: FavoriteJobDao
) : JobRepository {

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

    override suspend fun insertFavoriteJob(job: Job) {
        favoriteJobDao.insertFavoriteJob(job.toFavoriteEntity())
    }

    override suspend fun deleteFavoriteJob(job: Job) {
        favoriteJobDao.deleteFavoriteJob(job.toFavoriteEntity())
    }

    override fun getAllFavoriteJobs(): Flow<List<Job>> {
        return favoriteJobDao.getAllFavoriteJobs().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun isJobFavorite(id: Long): Flow<Boolean> {
        return favoriteJobDao.isJobFavorite(id)
    }
}
