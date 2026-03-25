package br.me.vitorcsouza.jobfydev.data.mapper

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
        salary = this.salary,
        tags = this.tags
    )
}
