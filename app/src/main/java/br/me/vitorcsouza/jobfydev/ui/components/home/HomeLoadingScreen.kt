package br.me.vitorcsouza.jobfydev.ui.components.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.me.vitorcsouza.jobfydev.ui.theme.shimmerEffect

@Composable
fun HomeLoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(32.dp).clip(CircleShape).shimmerEffect())
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(modifier = Modifier.size(width = 110.dp, height = 24.dp).clip(
                        RoundedCornerShape(4.dp)
                    ).shimmerEffect())
                }
                Box(modifier = Modifier.size(40.dp).clip(CircleShape).shimmerEffect())
            }
            Spacer(modifier = Modifier.height(24.dp))
            // Search Bar Skeleton
            Box(modifier = Modifier.fillMaxWidth().height(56.dp).clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp)).shimmerEffect())
        }

        // Filter Chips Skeleton
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(5) {
                Box(modifier = Modifier.size(width = 80.dp, height = 36.dp).clip(androidx.compose.foundation.shape.RoundedCornerShape(18.dp)).shimmerEffect())
            }
        }

        // Job Cards Skeleton
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Box(modifier = Modifier.size(width = 120.dp, height = 20.dp).clip(androidx.compose.foundation.shape.RoundedCornerShape(4.dp)).shimmerEffect())
                Box(modifier = Modifier.size(width = 80.dp, height = 16.dp).clip(androidx.compose.foundation.shape.RoundedCornerShape(4.dp)).shimmerEffect())
            }

            repeat(3) {
                JobCardSkeleton()
            }
        }
    }
}

@Composable
fun JobCardSkeleton() {
    Surface(
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
        color = Color.White,
        border = BorderStroke(1.dp, Color(0xFFF1F1F1))
    ) {
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Box(modifier = Modifier.size(48.dp).clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp)).shimmerEffect())
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.fillMaxWidth(0.7f).height(16.dp).shimmerEffect())
                Box(modifier = Modifier.fillMaxWidth(0.5f).height(12.dp).shimmerEffect())
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(3) { Box(modifier = Modifier.size(width = 60.dp, height = 24.dp).clip(
                        androidx.compose.foundation.shape.RoundedCornerShape(8.dp)).shimmerEffect()) }
                }
                Box(modifier = Modifier.size(width = 100.dp, height = 16.dp).shimmerEffect())
            }
        }
    }
}