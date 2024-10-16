package com.example.parkeaya.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun UserDataScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Datos del Usuario", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFBBDEFB))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Perfil del Usuario",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF1976D2),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            UserInfoRow(label = "Nombre completo", value = "Ulises Vázquez Rodríguez")
            UserInfoRow(label = "Correo", value = "ulises.vazquez@gmail.com")
            UserInfoRow(label = "Teléfono", value = "+52 555 678 1234")
            UserInfoRow(label = "CURP", value = "VAXU920505HDFRRL03")
            UserInfoRow(label = "Placa", value = "XYZ-9876")
        }
    }
}

@Composable
fun UserInfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = Color(0xFF1976D2))
        Text(text = value, style = MaterialTheme.typography.bodyLarge, color = Color(0xFF424242))
    }
}