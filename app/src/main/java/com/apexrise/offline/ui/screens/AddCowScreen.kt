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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.apexrise.offline.data.entity.CowEntity
import com.apexrise.offline.ui.viewmodel.AppViewModels

@Composable
fun AddCowScreen(navController: NavHostController) {
    val viewModel = AppViewModels.cows()

    var name by remember { mutableStateOf("") }
    var tagNumber by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var purchaseDate by remember { mutableStateOf("") }
    var purchaseCost by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(text = "Add cow")
        if (error != null) {
            Text(text = error ?: "", color = androidx.compose.material3.MaterialTheme.colorScheme.error)
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )
                OutlinedTextField(
                    value = tagNumber,
                    onValueChange = { tagNumber = it },
                    label = { Text("Tag number") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )
                OutlinedTextField(
                    value = breed,
                    onValueChange = { breed = it },
                    label = { Text("Breed (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )
                OutlinedTextField(
                    value = purchaseDate,
                    onValueChange = { purchaseDate = it },
                    label = { Text("Purchase date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )
                OutlinedTextField(
                    value = purchaseCost,
                    onValueChange = { purchaseCost = it },
                    label = { Text("Purchase cost (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )

                Spacer(Modifier.height(6.dp))

                Button(
                    onClick = {
                        error = null
                        if (name.isBlank() || tagNumber.isBlank()) {
                            error = "Name and tag number are required."
                            return@Button
                        }
                        val cost = purchaseCost.trim().takeIf { it.isNotEmpty() }?.toDoubleOrNull()
                        if (purchaseCost.isNotBlank() && cost == null) {
                            error = "Purchase cost must be a number."
                            return@Button
                        }
                        viewModel.addCow(
                            CowEntity(
                                name = name.trim(),
                                tagNumber = tagNumber.trim(),
                                breed = breed.trim().ifBlank { null },
                                purchaseDate = purchaseDate.trim().ifBlank { null },
                                purchaseCost = cost,
                            )
                        )
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Save")
                }
            }
        }
    }
}
