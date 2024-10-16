package com.example.parkeaya.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Métodos de Pago", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFBBDEFB))
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Acción para agregar un nuevo método de pago */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Método de Pago")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PaymentCard(
                cardNumber = "**** **** **** 1234",
                cardHolder = "Ulises Pérez García",
                expiryDate = "12/24"
            )
        }
    }
}

@Composable
fun PaymentCard(cardNumber: String, cardHolder: String, expiryDate: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1565C0))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = cardNumber, style = MaterialTheme.typography.headlineMedium, color = Color.White, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Titular: $cardHolder", style = MaterialTheme.typography.bodyLarge, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Expira: $expiryDate", style = MaterialTheme.typography.bodyLarge, color = Color.White)
        }
    }
}