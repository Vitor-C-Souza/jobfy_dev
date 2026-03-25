package br.me.vitorcsouza.jobfydev.ui.components.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Work
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.ui.theme.JobfyDevTheme

@Composable
fun JobInfoGrid(
    job: Job,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        InfoCard(
            icon = Icons.Outlined.LocationOn,
            label = "Location",
            value = job.location,
        )
        InfoCard(
            icon = Icons.Outlined.Work,
            label = "Type",
            value = job.jobType.replaceFirstChar { it.uppercase() }.replace("_", "-"),
        )
        InfoCard(
            icon = Icons.Outlined.AccessTime,
            label = "Posted",
            value = job.getPublishedDaysAgo(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun JobInfoGridPreview() {
    val mockJob = Job(
        id = 1,
        title = "Senior React Developer",
        companyName = "TechCorp Inc.",
        category = "Software Development",
        jobType = "full_time",
        url = "https://remotive.com",
        logoUrl = null,
        location = "Remote - US",
        salary = "$120k - $160k",
        tags = listOf("React", "TypeScript", "Node.js"),
        publicationDate = "2023-09-01T00:00:00+00:00"
    )
    JobfyDevTheme {
        JobInfoGrid(job = mockJob)
    }
}