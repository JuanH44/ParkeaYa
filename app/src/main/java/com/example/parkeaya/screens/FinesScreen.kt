package com.example.parkeaya.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
fun FinesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Multas Pendientes", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFBBDEFB)) // Barra superior azul claro para transmitir calma y seguridad
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            items(2) { index ->
                FineItem(
                    date = "2024-10-${index + 1}",
                    time = "10:${index + 1}0 AM",
                    place = "Lugar ${index + 1}",
                    cost = "\$${50 + index * 10}.00"
                )
            }
        }
    }
}

@Composable
fun FineItem(date: String, time: String, place: String, cost: String) {
    Card(
        shape = RoundedCornerShape(16.dp), // Forma más redondeada para una apariencia moderna
        elevation = CardDefaults.cardElevation(8.dp), // Mayor elevación para resaltar el componente
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE9F8EE), // Fondo verde claro para un ambiente más cálido
            contentColor = Color(0xFF424242) // Color de contenido más oscuro para contraste
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Fecha: $date",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    fontSize = 18.sp,
                    color = Color(0xFF13568 6)
                )
            }
            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos para mejor legibilidad
            Text(text = "Hora: $time", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF616161))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Lugar: $place", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF616161))
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Costo: $cost",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF872324), // Rojo menos brillante para el costo
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = { /* Acción de reportar */ }) {
                    Text(
                        text = "Reportar",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Medium),
                        color = Color(0xFF1976D2)
                    )
                }
            }
        }
    }
}