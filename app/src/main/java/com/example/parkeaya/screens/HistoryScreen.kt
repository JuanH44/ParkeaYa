package com.example.parkeaya.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun HistoryScreen(navController: NavController) {
    val historyEntries = listOf(
        HistoryEntry("2024-10-01 14:30", "Ingreso al estacionamiento", "Estacionamiento A", "\$10.00"),
        HistoryEntry("2024-10-01 16:00", "Pago realizado", "Estacionamiento B", "\$15.00"),
        HistoryEntry("2024-10-02 09:15", "Intento de pago fallido", "Estacionamiento C", "\$0.00"),
        HistoryEntry("2024-10-02 11:45", "Salida del estacionamiento", "Estacionamiento A", "\$0.00"),
        HistoryEntry("2024-10-03 13:00", "Actualización de métodos de pago", "N/A", "N/A")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Historial", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFBBDEFB)) // Barra superior azul claro para transmitir calma y seguridad
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(historyEntries) { entry ->
                HistoryCard(entry)
            }
        }
    }
}

@Composable
fun HistoryCard(entry: HistoryEntry) {
    Card(
        shape = RoundedCornerShape(16.dp), // Forma más redondeada para una apariencia moderna
        elevation = CardDefaults.cardElevation(8.dp), // Mayor elevación para resaltar el componente
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3F2FD), // Fondo ligeramente azulado
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
                    text = "Fecha y Hora: ${entry.dateTime}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Arrow Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp) // Icono de flecha para indicar más detalles
                )
            }
            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos para mejor legibilidad
            Text(text = "Evento: ${entry.event}", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF616161))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Lugar: ${entry.location}", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF616161))
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Costo: ${entry.cost}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = if (entry.cost != "\$0.00") Color(0xFF444444) else Color(0xFF388E3C), // Rojo para costo y verde para eventos sin costo
                fontSize = 20.sp
            )
        }
    }
}

data class HistoryEntry(
    val dateTime: String,
    val event: String,
    val location: String,
    val cost: String
)