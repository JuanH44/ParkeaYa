package com.example.parkeaya

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationHelper {

    private const val ID_CANAL = "parkeaya_canal_id"
    private const val ID_NOTIFICACION = 1

    fun mostrarNotificacion(contexto: Context) {
        crearCanalNotificacion(contexto)

        val intent = Intent(contexto, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("from_notification", true) // Agregamos un extra que indique que la app se abrió desde la notificación
        }


        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                contexto,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                contexto,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val largeIcon = BitmapFactory.decodeResource(contexto.resources, R.drawable.ic_notification)

        val notificacion = NotificationCompat.Builder(contexto, ID_CANAL)
            .setSmallIcon(R.drawable.ic_notification) // Ícono pequeño (monocromático)
            .setLargeIcon(largeIcon) // Ícono grande en color
            .setContentTitle("Te has estacionado")
            .setContentText("Pulsa para abrir la aplicación.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager =
            contexto.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(ID_NOTIFICACION, notificacion)
    }

    private fun crearCanalNotificacion(contexto: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nombre = "Canal de ParkeaYa"
            val descripcion = "Canal para notificaciones de ParkeaYa"
            val importancia = NotificationManager.IMPORTANCE_DEFAULT
            val canal = NotificationChannel(ID_CANAL, nombre, importancia).apply {
                description = descripcion
            }
            val notificationManager: NotificationManager =
                contexto.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(canal)
        }
    }
}
