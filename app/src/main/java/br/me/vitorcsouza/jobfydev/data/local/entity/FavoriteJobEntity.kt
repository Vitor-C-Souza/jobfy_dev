package br.me.vitorcsouza.jobfydev.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_jobs")
data class FavoriteJobEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val companyName: String,
    val category: String,
    val jobType: String,
    val url: String,
    val logoUrl: String?,
    val location: String,
    val salary: String?,
    val tags: String, // String separated by commas
    val publicationDate: String
)
