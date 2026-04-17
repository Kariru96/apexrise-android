package com.apexrise.offline.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.apexrise.offline.data.entity.MilkRecordEntity
import com.apexrise.offline.ui.viewmodel.AppViewModels
import com.apexrise.offline.util.Time

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MilkingScreen(navController: NavHostController) {
    val entry by navController.currentBackStackEntryAsState()
    val initialCowId = entry?.arguments?.getString("cowId")?.toLongOrNull()

    val viewModel = AppViewModels.milking()
    val cows by viewModel.cows

    var expanded by remember { mutableStateOf(false) }
    var selectedCowId by remember { mutableStateOf<Long?>(initialCowId) }
    var date by remember { mutableStateOf(Time.todayIso()) }

    val record by viewModel.observeRecord(selectedCowId, date).collectAsState(initial = null)

    var s1 by remember { mutableStateOf("") }
    var s2 by remember { mutableStateOf("") }
    var s3 by remember { mutableStateOf("") }
    var s4 by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(cows, selectedCowId) {
        if (selectedCowId == null && cows.isNotEmpty()) {
            selectedCowId = cows.first().id
        }
    }

    LaunchedEffect(record?.id) {
        s1 = record?.session1?.takeIf { it > 0 }?.toString() ?: ""
        s2 = record?.session2?.takeIf { it > 0 }?.toString() ?: ""
        s3 = record?.session3?.takeIf { it > 0 }?.toString() ?: ""
        s4 = record?.session4?.takeIf { it > 0 }?.toString() ?: ""
        notes = record?.notes.orEmpty()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Milking (4 sessions)")
        if (error != null) {
            Text(text = error ?: "", color = androidx.compose.material3.MaterialTheme.colorScheme.error)
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                ) {
                    val selectedCow = cows.firstOrNull { it.id == selectedCowId }
                    OutlinedTextField(
                        value = selectedCow?.let { "${it.name} (${it.tagNumber})" } ?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Cow") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(type = MenuAnchorType.PrimaryNotEditable),
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        for (cow in cows) {
                            DropdownMenuItem(
                                text = { Text("${cow.name} (${cow.tagNumber})") },
                                onClick = {
                                    selectedCowId = cow.id
                                    expanded = false
                                },
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it.trim() },
                    label = { Text("Date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )

                OutlinedTextField(
                    value = s1,
                    onValueChange = { s1 = it },
                    label = { Text("Session 1 (L)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                )
                OutlinedTextField(
                    value = s2,
                    onValueChange = { s2 = it },
                    label = { Text("Session 2 (L)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                )
                OutlinedTextField(
                    value = s3,
                    onValueChange = { s3 = it },
                    label = { Text("Session 3 (L)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                )
                OutlinedTextField(
                    value = s4,
                    onValueChange = { s4 = it },
                    label = { Text("Session 4 (Evening) (L)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                )
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(Modifier.height(6.dp))

                Button(
                    onClick = {
                        error = null
                        val cowId = selectedCowId
                        if (cowId == null) {
                            error = "Please add a cow first."
                            return@Button
                        }
                        val v1 = s1.trim().takeIf { it.isNotEmpty() }?.toDoubleOrNull() ?: 0.0
                        val v2 = s2.trim().takeIf { it.isNotEmpty() }?.toDoubleOrNull() ?: 0.0
                        val v3 = s3.trim().takeIf { it.isNotEmpty() }?.toDoubleOrNull() ?: 0.0
                        val v4 = s4.trim().takeIf { it.isNotEmpty() }?.toDoubleOrNull() ?: 0.0
                        if (listOf(v1, v2, v3, v4).any { it < 0 }) {
                            error = "Litres must be 0 or more."
                            return@Button
                        }
                        viewModel.saveMilkRecord(
                            MilkRecordEntity(
                                id = record?.id ?: 0,
                                cowId = cowId,
                                date = date,
                                session1 = v1,
                                session2 = v2,
                                session3 = v3,
                                session4 = v4,
                                notes = notes.trim().ifBlank { null },
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Save")
                }
            }
        }
    }
}
