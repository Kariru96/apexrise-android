package com.apexrise.offline.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.apexrise.offline.ui.viewmodel.AppViewModels
import com.apexrise.offline.util.Formatters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordsScreen(navController: NavHostController) {
    val viewModel = AppViewModels.records()
    val cows by viewModel.cows
    val records by viewModel.records

    var expanded by remember { mutableStateOf(false) }
    var selectedCowId by remember { mutableStateOf<Long?>(null) }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Records")

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                ) {
                    val selectedCow = cows.firstOrNull { it.id == selectedCowId }
                    OutlinedTextField(
                        value = selectedCow?.let { "${it.name} (${it.tagNumber})" } ?: "All cows",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Filter by cow") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(type = MenuAnchorType.PrimaryNotEditable),
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        DropdownMenuItem(
                            text = { Text("All cows") },
                            onClick = {
                                selectedCowId = null
                                viewModel.setCowFilter(null)
                                expanded = false
                            },
                        )
                        for (cow in cows) {
                            DropdownMenuItem(
                                text = { Text("${cow.name} (${cow.tagNumber})") },
                                onClick = {
                                    selectedCowId = cow.id
                                    viewModel.setCowFilter(cow.id)
                                    expanded = false
                                },
                            )
                        }
                    }
                }
            }
        }

        if (records.isEmpty()) {
            Text("No milk records yet.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(records) { r ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("${r.date} · ${r.cowName} (${r.cowTagNumber})")
                            Text(
                                "S1 ${Formatters.litres(r.session1)} · S2 ${Formatters.litres(r.session2)} · S3 ${Formatters.litres(r.session3)} · S4 ${Formatters.litres(r.session4)}",
                                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Text("Daily total: ${Formatters.litres(r.dailyTotal)} L")
                            Button(
                                onClick = { viewModel.deleteRecord(r.id) },
                                modifier = Modifier.fillMaxWidth(),
                            ) { Text("Delete") }
                        }
                    }
                }
            }
        }
    }
}
