package br.me.vitorcsouza.jobfydev.ui.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.me.vitorcsouza.jobfydev.domain.model.Job
import br.me.vitorcsouza.jobfydev.ui.components.home.FilterChips
import br.me.vitorcsouza.jobfydev.ui.components.home.HomeHeader
import br.me.vitorcsouza.jobfydev.ui.components.home.JobCard
import br.me.vitorcsouza.jobfydev.ui.components.home.SearchBar
import br.me.vitorcsouza.jobfydev.ui.components.home.SectionHeader
import br.me.vitorcsouza.jobfydev.ui.theme.JobfyDevTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    HomeContent(
        state = state,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onFilterSelected = viewModel::onFilterSelected
    )
}

@Composable
fun HomeContent(
    state: HomeStates,
    onSearchQueryChange: (String) -> Unit,
    onFilterSelected: (String) -> Unit
) {
    val filteredJobs = state.jobs.filter { job ->
        val matchesFilter = state.selectedFilter == "All" || job.category == state.selectedFilter
        val matchesSearch = job.title.contains(state.searchQuery, ignoreCase = true) ||
                job.companyName.contains(state.searchQuery, ignoreCase = true)
        matchesFilter && matchesSearch
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF8F9FB)
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    HomeHeader()
                }

                item {
                    SearchBar(
                        value = state.searchQuery,
                        onValueChange = onSearchQueryChange,
                    )
                }

                item {
                    FilterChips(
                        categories = state.categories,
                        selectedFilter = state.selectedFilter,
                        onFilterSelected = onFilterSelected
                    )
                }

                item {
                    SectionHeader(jobsCount = filteredJobs.size)
                }

                items(filteredJobs) { job ->
                    JobCard(job = job)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    JobfyDevTheme {
        HomeContent(
            state = HomeStates(
                jobs = listOf(
                    Job(
                        id = 1,
                        title = "Senior Android Developer",
                        companyName = "Google",
                        category = "Software Development",
                        jobType = "full_time",
                        url = "",
                        logoUrl = null,
                        location = "Remote",
                        salary = "$150k",
                        tags = listOf("Kotlin", "Compose"),
                        publicationDate = "2023-09-01T00:00:00+00:00"
                    ),
                    Job(
                        id = 2,
                        title = "Product Designer",
                        companyName = "Airbnb",
                        category = "Design",
                        jobType = "full_time",
                        url = "",
                        logoUrl = null,
                        location = "San Francisco",
                        salary = "$130k",
                        tags = listOf("Figma", "UI/UX"),
                        publicationDate = "2023-09-01T00:00:00+00:00"
                    )
                ),
                categories = listOf("All", "Software Development", "Design")
            ),
            onSearchQueryChange = {},
            onFilterSelected = {}
        )
    }
}
