package com.apexrise.offline.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.apexrise.offline.network.ConflictItem
import kotlinx.coroutines.launch

@Composable
fun ConflictResolutionScreen(
    conflicts: List<ConflictItem>,
    onConflictsResolved: () -> Unit,
    onResolveConflict: suspend (ConflictItem, String) -> Boolean
) {
    var resolvedCount by remember { mutableStateOf(0) }
    var remainingConflicts by remember { mutableStateOf(conflicts) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Resolve Data Conflicts",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            "Found ${remainingConflicts.size} conflicts. Choose to keep local or server version for each.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (remainingConflicts.isEmpty()) {
            // All resolved
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "All conflicts resolved!",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onConflictsResolved) {
                    Text("Continue")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(remainingConflicts) { conflict ->
                    ConflictCard(
                        conflict = conflict,
                        onResolve = { resolution ->
                            coroutineScope.launch {
                                val success = onResolveConflict(conflict, resolution)
                                if (success) {
                                    remainingConflicts = remainingConflicts.filter { it != conflict }
                                    resolvedCount++
                                }
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Resolved: $resolvedCount/${conflicts.size}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
private fun ConflictCard(
    conflict: ConflictItem,
    onResolve: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Entity Type: ${conflict.entityType} (ID: ${conflict.entityId})",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                "Local Version:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            DataPreview(conflict.localData)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Server Version:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            DataPreview(conflict.serverData)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { onResolve("local") },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Keep Local")
                }
                Button(
                    onClick = { onResolve("server") },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Keep Server")
                }
            }
        }
    }
}

@Composable
private fun DataPreview(data: Map<String, Any?>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        data.forEach { (key, value) ->
            Row {
                Text(
                    "$key: ",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.width(100.dp)
                )
                Text(
                    value?.toString() ?: "null",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
