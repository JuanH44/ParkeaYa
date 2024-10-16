package com.example.parkeaya.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Soporte", color = Color.Black, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFBBDEFB)) // Barra superior azul intenso para mayor impacto visual
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp), // Mayor espacio entre botones para una apariencia más limpia
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Estamos aquí para ayudarte",
                style = MaterialTheme.typography.headlineMedium.copy(),
                color = Color(0xFF1976D2), // Azul intenso para enfatizar el mensaje
                modifier = Modifier.padding(bottom = 16.dp)
            )
            SupportOptionButton(
                icon = Icons.Filled.Call,
                label = "Llamar al soporte",
                onClick = { /* Acción para llamar */ }
            )
            SupportOptionButton(
                icon = Icons.Filled.Chat,
                label = "Chatear con soporte",
                onClick = { /* Acción para iniciar chat */ }
            )
        }
    }
}

@Composable
fun SupportOptionButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(60.dp), // Altura fija para mejorar la consistencia visual
        shape = RoundedCornerShape(24.dp), // Bordes más redondeados para una apariencia más amigable
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE3F2FD), contentColor = Color(0xFF424242)), // Azul medio con texto blanco para mejor visibilidad
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp) // Elevación para dar sensación de profundidad
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = Color(0xFF1976D2), modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = label, style = MaterialTheme.typography.bodyLarge, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}