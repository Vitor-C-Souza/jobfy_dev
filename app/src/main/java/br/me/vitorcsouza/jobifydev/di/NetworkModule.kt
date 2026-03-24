package br.me.vitorcsouza.jobifydev.di

import br.me.vitorcsouza.jobifydev.data.remote.RemotiveApi
import br.me.vitorcsouza.jobifydev.data.repository.JobRepositoryImpl
import br.me.vitorcsouza.jobifydev.domain.repository.JobRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRemotiveApi(): RemotiveApi {
        return Retrofit.Builder()
            .baseUrl(RemotiveApi.BASE_URL)
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