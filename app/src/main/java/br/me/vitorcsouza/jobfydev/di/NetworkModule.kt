package br.me.vitorcsouza.jobfydev.di

import br.me.vitorcsouza.jobfydev.data.remote.RemotiveApi
import br.me.vitorcsouza.jobfydev.data.repository.JobRepositoryImpl
import br.me.vitorcsouza.jobfydev.domain.repository.JobRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRemotiveApi(okHttpClient: OkHttpClient): RemotiveApi {
        return Retrofit.Builder()
            .baseUrl("https://remotive.com/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemotiveApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJobRepository(api: RemotiveApi): JobRepository {
        return JobRepositoryImpl(api)
    }
}