package com.apexrise.offline.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.apexrise.offline.ui.components.MetricCard
import com.apexrise.offline.ui.viewmodel.AppViewModels
import com.apexrise.offline.util.Formatters

@Composable
fun DashboardScreen(navController: NavHostController) {
    val viewModel = AppViewModels.dashboard()
    val uiState by viewModel.uiState

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = "Dashboard", style = MaterialTheme.typography.headlineSmall)

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            MetricCard(
                label = "Today’s milk (L)",
                value = Formatters.litres(uiState.todayMilkLitres),
                modifier = Modifier.weight(1f),
            )
            MetricCard(
                label = "Active cows",
                value = uiState.cowsCount.toString(),
                modifier = Modifier.weight(1f),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            MetricCard(
                label = "Monthly revenue",
                value = Formatters.money(uiState.monthlyRevenue),
                modifier = Modifier.weight(1f),
            )
            MetricCard(
                label = "Net profit",
                value = Formatters.money(uiState.monthlyNetProfit),
                modifier = Modifier.weight(1f),
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { navController.navigate("milking") }, modifier = Modifier.weight(1f)) { Text("Log milk") }
            Button(onClick = { navController.navigate("records") }, modifier = Modifier.weight(1f)) { Text("Records") }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { navController.navigate("cows") }, modifier = Modifier.weight(1f)) { Text("Cows") }
            Button(onClick = { navController.navigate("profits") }, modifier = Modifier.weight(1f)) { Text("Profits") }
        }
    }
}

