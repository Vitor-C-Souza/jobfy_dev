package br.me.vitorcsouza.jobifydev.domain.model

data class Job(
    val id: Long,
    val title: String,
    val companyName: String,
    val category: String,
    val jobType: String,
    val url: String,
    val logoUrl: String?
)
