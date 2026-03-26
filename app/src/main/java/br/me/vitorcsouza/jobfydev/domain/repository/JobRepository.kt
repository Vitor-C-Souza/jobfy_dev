package br.me.vitorcsouza.jobfydev.domain.repository

import br.me.vitorcsouza.jobfydev.domain.model.Job
import kotlinx.coroutines.flow.Flow

interface JobRepository {
    suspend fun getRemoteJobs(): Result<List<Job>>
    fun getJobById(id: Long): Job?
    
    suspend fun insertFavoriteJob(job: Job)
    suspend fun deleteFavoriteJob(job: Job)
    fun getAllFavoriteJobs(): Flow<List<Job>>
    fun isJobFavorite(id: Long): Flow<Boolean>
}
