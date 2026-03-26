package br.me.vitorcsouza.jobfydev.ui.components.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.ui.theme.JobfyDevTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TechStackSection(
    job: Job,
    modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "Tech Stack",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp), // Espaço entre as linhas
            maxItemsInEachRow = Int.MAX_VALUE
        ) {
            job.tags.forEach { tag ->
                TagCard(tag = tag)
            }
        }
    }
}

@Composable
fun TagCard(
    tag: String,
    modifier: Modifier = Modifier) {

    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp
    ) {
        Text(
            text = tag,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
    
}

@Preview(showBackground = true)
@Composable
private fun TechStackSectionPreview() {
    val mockJob = Job(
        id = 1,
        title = "Senior React Developer",
        companyName = "TechCorp Inc.",
        category = "Software Development",
        jobType = "full_time",
        url = "https://remotive.com",
        logoUrl = null,
        location = "Remote - US",
        salary = "",
        tags = listOf("React", "TypeScript", "Node.js", "TypeScript", "Node.js", "TypeScript", "Node.js"),
        publicationDate = "2023-09-01T00:00:00+00:00"
    )
    JobfyDevTheme {
        TechStackSection(job = mockJob)
    }
}

@Preview(showBackground = true)
@Composable
private fun TagCardPreview() {
    JobfyDevTheme {
        TagCard(tag = "React")
    }
}