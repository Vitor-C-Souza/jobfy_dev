package br.me.vitorcsouza.jobfydev.di

import android.content.Context
import androidx.room.Room
import br.me.vitorcsouza.jobfydev.data.local.FavoriteJobDao
import br.me.vitorcsouza.jobfydev.data.local.JobDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideJobDatabase(@ApplicationContext context: Context): JobDatabase {
        return Room.databaseBuilder(
            context,
            JobDatabase::class.java,
            JobDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteJobDao(database: JobDatabase): FavoriteJobDao {
        return database.favoriteJobDao
    }
}
