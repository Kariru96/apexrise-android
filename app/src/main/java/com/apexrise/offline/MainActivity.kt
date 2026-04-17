package com.apexrise.offline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.apexrise.offline.ui.ApexRiseApp
import com.apexrise.offline.ui.theme.ApexRiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApexRiseTheme {
                Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
                    ApexRiseApp()
                }
            }
        }
    }
}

