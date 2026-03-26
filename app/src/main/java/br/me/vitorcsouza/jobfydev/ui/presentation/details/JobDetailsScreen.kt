package br.me.vitorcsouza.jobfydev.ui.presentation.details

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.ui.components.details.ApplyButton
import br.me.vitorcsouza.jobfydev.ui.components.details.DetailTopBar
import br.me.vitorcsouza.jobfydev.ui.components.details.JobHeaderSection
import br.me.vitorcsouza.jobfydev.ui.components.details.JobInfoGrid
import br.me.vitorcsouza.jobfydev.ui.components.details.SalaryBanner
import br.me.vitorcsouza.jobfydev.ui.components.details.TechStackSection
import br.me.vitorcsouza.jobfydev.ui.theme.JobfyDevTheme

@Composable
fun JobDetailsScreen(
    viewModel: JobDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    JobDetailsContent(
        job = state.job,
        isFavorite = state.isFavorite,
        onBackClick = onBackClick,
        onBookmarkClick = viewModel::onToggleFavorite
    )
}

@Composable
fun JobDetailsContent(
    job: Job?,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    if (job == null) return

    val context = LocalContext.current
    
    val handleShare = {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Confira esta vaga: ${job.title} na ${job.companyName}\n${job.url}")
        }
        context.startActivity(Intent.createChooser(intent, "Compartilhar Vaga"))
    }

    Scaffold(
        topBar = {
            DetailTopBar(
                isFavorite = isFavorite,
                onBackClick = onBackClick,
                onShareClick = handleShare,
                onBookmarkClick = onBookmarkClick
            )
        },
        containerColor = Color(0xFFF8F9FB),
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                ApplyButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, job.url.toUri())
                        context.startActivity(intent)
                    },
                    modifier = Modifier.padding(24.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            JobHeaderSection(job = job)

            JobInfoGrid(job = job)

            SalaryBanner(job = job)

            TechStackSection(job = job)
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun JobDetailsScreenPreview() {
    val mockJob = Job(
        id = 1,
        title = "Senior React Developer",
        companyName = "TechCorp Inc.",
        category = "Software Development",
        jobType = "full_time",
        url = "https://remotive.com",
        logoUrl = null,
        location = "USA",
        salary = "$120k - $160k",
        tags = listOf("React", "TypeScript", "Node.js"),
        publicationDate = "2023-09-01T00:00:00+00:00"
    )
    JobfyDevTheme {
        JobDetailsContent(
            job = mockJob,
            isFavorite = true,
            onBackClick = {},
            onBookmarkClick = {}
        )
    }
}
