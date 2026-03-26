package br.me.vitorcsouza.jobfydev.data.mapper

import br.me.vitorcsouza.jobfydev.data.local.entity.FavoriteJobEntity
import br.me.vitorcsouza.jobfydev.data.remote.dto.JobDto
import br.me.vitorcsouza.jobfydev.domain.model.Job

fun JobDto.toDomain(): Job {
    return Job(
        id = this.id,
        title = this.title,
        companyName = this.companyName,
        category = this.category,
        jobType = this.jobType,
        url = this.url,
        logoUrl = this.companyLogo,
        location = this.location,
        salary = if (this.salary.isNullOrBlank()) null else this.salary,
        tags = this.tags,
        publicationDate = this.publicationDate
    )
}

fun Job.toFavoriteEntity(): FavoriteJobEntity {
    return FavoriteJobEntity(
        id = id,
        title = title,
        companyName = companyName,
        category = category,
        jobType = jobType,
        url = url,
        logoUrl = logoUrl,
        location = location,
        salary = salary,
        tags = tags.joinToString(","),
        publicationDate = publicationDate
    )
}

fun FavoriteJobEntity.toDomain(): Job {
    return Job(
        id = id,
        title = title,
        companyName = companyName,
        category = category,
        jobType = jobType,
        url = url,
        logoUrl = logoUrl,
        location = location,
        salary = salary,
        tags = tags.split(","),
        publicationDate = publicationDate
    )
}
