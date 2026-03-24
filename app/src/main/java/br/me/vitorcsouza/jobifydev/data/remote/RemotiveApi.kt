package br.me.vitorcsouza.jobifydev.data.remote

import br.me.vitorcsouza.jobifydev.data.remote.dto.JobResponseDto
import retrofit2.http.GET

interface RemotiveApi {
    @GET("remote-jobs")
    suspend fun getJobs(): JobResponseDto

    companion object {
        const val BASE_URL = "https://remotive.com/api/"
    }
}