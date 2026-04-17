package com.apexrise.offline.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.apexrise.offline.ui.components.MetricCard
import com.apexrise.offline.ui.viewmodel.AppViewModels
import com.apexrise.offline.util.Formatters

@Composable
fun CowDetailScreen(navController: NavHostController) {
    val entry by navController.currentBackStackEntryAsState()
    val cowId = entry?.arguments?.getString("cowId")?.toLongOrNull() ?: return

    val viewModel = AppViewModels.cowDetail(cowId)
    val uiState by viewModel.uiState

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = uiState.title, style = MaterialTheme.typography.headlineSmall)

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            MetricCard(
                label = "Total milk (L)",
                value = Formatters.litres(uiState.totalMilkLitres),
                modifier = Modifier.weight(1f),
            )
            MetricCard(
                label = "Daily average (L)",
                value = Formatters.litres(uiState.dailyAverageLitres),
                modifier = Modifier.weight(1f),
            )
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Full record history", style = MaterialTheme.typography.titleMedium)
                if (uiState.records.isEmpty()) {
                    Text("No milk records yet.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(uiState.records) { r ->
                            Card(modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(r.date, style = MaterialTheme.typography.titleSmall)
                                    Text(
                                        "S1 ${Formatters.litres(r.session1)} · S2 ${Formatters.litres(r.session2)} · S3 ${Formatters.litres(r.session3)} · S4 ${Formatters.litres(r.session4)}",
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text("Daily total: ${Formatters.litres(r.dailyTotal)} L")
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(6.dp))
                Button(
                    onClick = { navController.navigate("milking?cowId=$cowId") },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Log milk for this cow")
                }
            }
        }
    }
}

