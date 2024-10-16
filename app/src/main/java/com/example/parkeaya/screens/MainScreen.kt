package com.example.parkeaya.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight // Import added
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parkeaya.navigation.AppScreens
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Support
import androidx.compose.material.icons.filled.Info
import com.example.parkeaya.NotificationHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

@Composable
fun MainScreen(navController: NavController) {
    var showParkingAlert by rememberSaveable { mutableStateOf(true) }
    var startChronometer by rememberSaveable { mutableStateOf(false) }
    var startTime by rememberSaveable { mutableStateOf(0L) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("ParqueaYa", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFBBDEFB))
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .background(Color.White)
            ) {
                items(6) { index ->
                    CustomCard(
                        index = index,
                        navController = navController,
                        startChronometer = startChronometer,
                        startTime = startTime
                    )
                }
            }

            if (showParkingAlert) {
                ParkingAlert(
                    onOkClick = {
                        showParkingAlert = false
                        startChronometer = true
                        startTime = System.currentTimeMillis() // Almacenamos el tiempo de inicio
                    },
                    onNotMeClick = { showParkingAlert = false }
                )
            }
        }
    }
}

@Composable
fun CustomCard(index: Int, navController: NavController, startChronometer: Boolean, startTime: Long) {
    val (title, icon, route) = when (index) {
        0 -> Triple("Estado actual", Icons.Filled.Home, "")
        1 -> Triple("Datos de usuario", Icons.Filled.Person, "user_data_screen")
        2 -> Triple("Historial", Icons.Filled.History, "history_screen")
        3 -> Triple("Adeudos", Icons.Filled.MonetizationOn, "fines_screen")
        4 -> Triple("Métodos de pago", Icons.Filled.Payment, "payment_methods_screen")
        5 -> Triple("Soporte", Icons.Filled.Support, "support_screen")
        else -> Triple("", Icons.Filled.Info, "")
    }

    val contexto = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                if (index == 0) {
                    NotificationHelper.mostrarNotificacion(contexto)
                } else if (route.isNotEmpty()) {
                    navController.navigate(route)
                }
            },
        shape = MaterialTheme.shapes.large, // Cambiar a una forma más redondeada
        elevation = CardDefaults.cardElevation(4.dp), // Agregar elevación para dar un efecto de profundidad
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3F2FD),
            contentColor = Color(0xFF424242) // Color de contenido más oscuro para mejor contraste
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFE3F2FD), shape = MaterialTheme.shapes.large), // Fondo ligeramente azulado
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFBBDEFB), shape = MaterialTheme.shapes.medium), // Icono con fondo redondeado
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "$title Icon",
                    tint = Color(0xFF1976D2), // Cambiar color del icono para mayor contraste
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp)) // Añadir más espacio entre el icono y el texto
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF424242)
                )
                Spacer(modifier = Modifier.height(4.dp)) // Añadir espacio entre el título y el contenido
                if (title == "Estado actual" && startChronometer) {
                    Chronometer(startTime = startTime)
                } else {
                    Text(
                        text = "Este es el contenido de $title.",
                        color = Color(0xFF616161),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

        }
    }
}

@Composable
fun ParkingAlert(onOkClick: () -> Unit, onNotMeClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Parking Alert Icon",
                    tint = Color(0xFF1976D2), // Azul más oscuro para transmitir calma y seriedad
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Notificación de estacionamiento",
                    color = Color(0xFF1976D2), // Azul más oscuro para consistencia
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
            }
        },
        text = {
            Column {
                val currentTime = remember {
                    SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
                }
                Text(
                    text = "Hora de inicio: $currentTime",
                    color = Color(0xFF1976D2), // Azul más oscuro para resaltar la hora de inicio
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "¿Eres tú quien está estacionado aquí?",
                    color = Color(0xFF424242), // Texto principal gris oscuro
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onOkClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Sí, soy yo", color = Color(0xFF388E3C)) // Verde para una acción positiva
            }
        },
        dismissButton = {
            TextButton(
                onClick = onNotMeClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "No soy yo", color = Color(0xFFD32F2F)) // Rojo para una acción de advertencia
            }
        },
        containerColor = Color(0xFFF1F8E9), // Verde claro para un fondo más cálido
        tonalElevation = 8.dp // Elevación para dar sensación de profundidad
    )
}

@Composable
fun Chronometer(startTime: Long) {
    var currentTime by remember { mutableStateOf(System.currentTimeMillis()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                delay(1000L)
                currentTime = System.currentTimeMillis()
            }
        }
    }

    val elapsedMillis = currentTime - startTime
    val hours = (elapsedMillis / 3600000).toInt()
    val minutes = ((elapsedMillis % 3600000) / 60000).toInt()
    val seconds = ((elapsedMillis % 60000) / 1000).toInt()

    Text(
        text = String.format(
            "Tiempo transcurrido: %02d:%02d:%02d",
            hours, minutes, seconds
        ),
        color = Color(0xFF2196F3)
    )
}
