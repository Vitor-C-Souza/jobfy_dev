package br.me.vitorcsouza.jobfydev.ui.components.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.ui.theme.JobfyDevTheme
import coil.compose.AsyncImage

@Composable
fun JobHeaderSection(
    job: Job,
    modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = job.logoUrl,
            contentDescription = "${job.companyName} logo",
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentScale = ContentScale.Fit,
        )

        Column() {
            Text(
                text = job.title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = job.companyName,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun JobHeaderSectionPreview() {
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
        JobHeaderSection(job = mockJob)
    }
}