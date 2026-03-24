package br.me.vitorcsouza.jobifydev.data.remote.dto

import com.google.gson.annotations.SerializedName

data class JobResponseDto(
    @SerializedName("job-count")
    val jobCount: Int,
    @SerializedName("jobs")
    val jobs: List<JobDto>
)

data class JobDto(
    val id: Long,
    val url: String,
    val title: String,
    @SerializedName("company_name")
    val companyName: String,
    @SerializedName("company_logo")
    val companyLogo: String?,
    val category: String,
    @SerializedName("job_type")
    val jobType: String,
    @SerializedName("publication_date")
    val publicationDate: String,
    val salary: String?,
    val description: String,
    val tags: List<String>,
    val location: String
) {
}
