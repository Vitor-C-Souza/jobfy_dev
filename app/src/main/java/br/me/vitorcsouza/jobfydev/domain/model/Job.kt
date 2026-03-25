package br.me.vitorcsouza.jobfydev.domain.model

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

data class Job(
    val id: Long,
    val title: String,
    val companyName: String,
    val location: String,
    val salary: String?,
    val category: String,
    val jobType: String,
    val url: String,
    val logoUrl: String?,
    val tags: List<String>,
    val publicationDate: String
) {
    fun getFormattedJobType(): String {
        return jobType.split("_")
            .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
    }

    fun getPublishedDaysAgo(): String {
        return try {
            val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
            val publishedDate = OffsetDateTime.parse(publicationDate, formatter).toLocalDateTime()
            val now = LocalDateTime.now()
            val days = ChronoUnit.DAYS.between(publishedDate, now)
            
            when {
                days <= 0 -> "Posted today"
                days == 1L -> "Posted yesterday"
                else -> "${days}d ago"
            }
        } catch (e: Exception) {
            "Data indisponível"
        }
    }
}
