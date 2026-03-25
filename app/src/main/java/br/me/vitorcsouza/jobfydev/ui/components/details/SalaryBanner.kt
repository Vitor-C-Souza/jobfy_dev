package br.me.vitorcsouza.jobfydev.ui.components.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.ui.theme.JobfyDevTheme

@Composable
fun SalaryBanner(
    job: Job,
    modifier: Modifier = Modifier
) {
    val isSalaryAvailable = !job.salary.isNullOrBlank()
    val salaryText = if (isSalaryAvailable) job.salary!! else "Salary Negotiable"

    val periodText = if (isSalaryAvailable) {
        if (salaryText.contains("hour", ignoreCase = true)) "per hour" else "per year"
    } else ""

    val cleanSalary = if (isSalaryAvailable) {
        salaryText.replace("/hour", "", ignoreCase = true).trim()
    } else salaryText

    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF0B3C5D),
            Color(0xFF165A8F)
        )
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp,
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .background(gradientBrush)
                .padding(20.dp)
        ) {
            Text(
                text = "Salary Range",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium
            )

            Text(
                text = cleanSalary,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = periodText,
                color = Color.White.copy(alpha = 0.7f),
                style = MaterialTheme.typography.labelSmall
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SalaryBannerPreview() {
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
        tags = listOf("React", "TypeScript", "Node.js"),
        publicationDate = "2023-09-01T00:00:00+00:00"
    )
    JobfyDevTheme {
        SalaryBanner(job = mockJob)
    }
}