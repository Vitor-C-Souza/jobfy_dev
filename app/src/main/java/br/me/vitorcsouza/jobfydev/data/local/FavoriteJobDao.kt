package br.me.vitorcsouza.jobfydev.data.local

import androidx.room.*
import br.me.vitorcsouza.jobfydev.data.local.entity.FavoriteJobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteJobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteJob(job: FavoriteJobEntity)

    @Delete
    suspend fun deleteFavoriteJob(job: FavoriteJobEntity)

    @Query("SELECT * FROM favorite_jobs")
    fun getAllFavoriteJobs(): Flow<List<FavoriteJobEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_jobs WHERE id = :id)")
    fun isJobFavorite(id: Long): Flow<Boolean>
}
