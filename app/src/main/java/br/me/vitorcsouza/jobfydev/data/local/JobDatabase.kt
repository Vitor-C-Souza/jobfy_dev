package br.me.vitorcsouza.jobfydev.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.me.vitorcsouza.jobfydev.data.local.entity.FavoriteJobEntity

@Database(
    entities = [FavoriteJobEntity::class],
    version = 1,
    exportSchema = false
)
abstract class JobDatabase : RoomDatabase() {
    abstract val favoriteJobDao: FavoriteJobDao

    companion object {
        const val DATABASE_NAME = "job_db"
    }
}
