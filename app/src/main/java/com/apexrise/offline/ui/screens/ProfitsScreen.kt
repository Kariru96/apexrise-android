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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.apexrise.offline.data.entity.ExpenseEntity
import com.apexrise.offline.data.entity.WakulimaRateEntity
import com.apexrise.offline.data.entity.WakulimaSaleEntity
import com.apexrise.offline.ui.components.MetricCard
import com.apexrise.offline.ui.viewmodel.AppViewModels
import com.apexrise.offline.util.Formatters
import com.apexrise.offline.util.Time

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfitsScreen(navController: NavHostController) {
    val viewModel = AppViewModels.profits()
    val uiState by viewModel.uiState

    var month by remember { mutableStateOf(Time.currentMonthIso()) }
    var rate by remember { mutableStateOf("") }

    var saleDate by remember { mutableStateOf(Time.todayIso()) }
    var saleS1 by remember { mutableStateOf("") }
    var saleS2 by remember { mutableStateOf("") }
    var saleS3 by remember { mutableStateOf("") }

    var expenseDate by remember { mutableStateOf(Time.todayIso()) }
    var expenseCategory by remember { mutableStateOf("") }
    var expenseAmount by remember { mutableStateOf("") }
    var expenseDescription by remember { mutableStateOf("") }
    var categoryExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Profits", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = month,
                    onValueChange = {
                        month = it.trim()
                        viewModel.setMonth(it.trim())
                    },
                    label = { Text("Month (YYYY-MM)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            MetricCard("Revenue", Formatters.money(uiState.revenue), modifier = Modifier.weight(1f))
            MetricCard("Expenses", Formatters.money(uiState.expenses), modifier = Modifier.weight(1f))
        }
        MetricCard("Net profit", Formatters.money(uiState.netProfit), modifier = Modifier.fillMaxWidth())

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Wakulima monthly rate", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = rate,
                    onValueChange = { rate = it },
                    label = { Text("Price per litre") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = { Text(uiState.rate?.let { Formatters.money(it) } ?: "Set rate to calculate revenue") },
                )
                Button(
                    onClick = {
                        val v = rate.trim().toDoubleOrNull() ?: return@Button
                        if (v < 0) return@Button
                        viewModel.saveRate(WakulimaRateEntity(month = month, pricePerLitre = v))
                        rate = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("Save rate") }

                Spacer(Modifier.height(6.dp))
                Text("Wakulima totals", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                Text(
                    "S1 ${Formatters.litres(uiState.salesTotals.session1Total)} · S2 ${Formatters.litres(uiState.salesTotals.session2Total)} · S3 ${Formatters.litres(uiState.salesTotals.session3Total)} · Litres ${Formatters.litres(uiState.salesTotals.litresTotal)}",
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Log Wakulima milk (sessions 1–3)", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = saleDate,
                    onValueChange = { saleDate = it.trim() },
                    label = { Text("Date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )
                OutlinedTextField(
                    value = saleS1,
                    onValueChange = { saleS1 = it },
                    label = { Text("Session 1 litres") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                OutlinedTextField(
                    value = saleS2,
                    onValueChange = { saleS2 = it },
                    label = { Text("Session 2 litres") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                OutlinedTextField(
                    value = saleS3,
                    onValueChange = { saleS3 = it },
                    label = { Text("Session 3 litres") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                Button(
                    onClick = {
                        val v1 = saleS1.trim().takeIf { it.isNotEmpty() }?.toDoubleOrNull() ?: 0.0
                        val v2 = saleS2.trim().takeIf { it.isNotEmpty() }?.toDoubleOrNull() ?: 0.0
                        val v3 = saleS3.trim().takeIf { it.isNotEmpty() }?.toDoubleOrNull() ?: 0.0
                        if (listOf(v1, v2, v3).any { it < 0 }) return@Button
                        val litres = v1 + v2 + v3
                        if (litres <= 0) return@Button
                        viewModel.addSale(
                            WakulimaSaleEntity(
                                date = saleDate,
                                session1 = v1,
                                session2 = v2,
                                session3 = v3,
                                litres = litres,
                            )
                        )
                        saleS1 = ""
                        saleS2 = ""
                        saleS3 = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("Save Wakulima record") }
            }
        }

        if (uiState.sales.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Wakulima records", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(uiState.sales) { s ->
                            Card(modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Text(s.date)
                                    Text(
                                        "S1 ${Formatters.litres(s.session1)} · S2 ${Formatters.litres(s.session2)} · S3 ${Formatters.litres(s.session3)} · Litres ${Formatters.litres(s.litres)}",
                                        color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                    val amount = uiState.rate?.let { s.litres * it }
                                    Text(
                                        text = "Amount: ${amount?.let { Formatters.money(it) } ?: "—"}",
                                    )
                                    Button(
                                        onClick = { viewModel.deleteSale(s) },
                                        modifier = Modifier.fillMaxWidth(),
                                    ) { Text("Delete") }
                                }
                            }
                        }
                    }
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Add expense", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = expenseDate,
                    onValueChange = { expenseDate = it.trim() },
                    label = { Text("Date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )

                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = !categoryExpanded },
                ) {
                    OutlinedTextField(
                        value = expenseCategory,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Category") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(type = MenuAnchorType.PrimaryNotEditable),
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false },
                    ) {
                        val options = listOf("Feed", "Vet", "Medicine", "Equipment", "Labor", "Utilities", "Other")
                        for (opt in options) {
                            DropdownMenuItem(
                                text = { Text(opt) },
                                onClick = {
                                    expenseCategory = opt
                                    categoryExpanded = false
                                },
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = expenseAmount,
                    onValueChange = { expenseAmount = it },
                    label = { Text("Amount") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                OutlinedTextField(
                    value = expenseDescription,
                    onValueChange = { expenseDescription = it },
                    label = { Text("Description (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                )

                Button(
                    onClick = {
                        val amt = expenseAmount.trim().toDoubleOrNull() ?: return@Button
                        if (amt < 0) return@Button
                        if (expenseCategory.isBlank()) return@Button
                        viewModel.addExpense(
                            ExpenseEntity(
                                date = expenseDate,
                                category = expenseCategory,
                                amount = amt,
                                description = expenseDescription.trim().ifBlank { null },
                            )
                        )
                        expenseAmount = ""
                        expenseDescription = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("Save expense") }
            }
        }

        if (uiState.expensesList.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Expenses", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(uiState.expensesList) { e ->
                            Card(modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Text("${e.date} · ${e.category}")
                                    Text(
                                        text = e.description ?: "—",
                                        color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                    Text("Amount: ${Formatters.money(e.amount)}")
                                    Button(
                                        onClick = { viewModel.deleteExpense(e) },
                                        modifier = Modifier.fillMaxWidth(),
                                    ) { Text("Delete") }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
