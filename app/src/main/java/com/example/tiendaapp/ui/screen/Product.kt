package com.example.tiendaapp.ui.screen

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val emoji: String
)

val sampleProducts = listOf(
    Product(1, "Camiseta básica", "100% Algodón, cómoda para el día a día", 35000, "👕"),
    Product(2, "Tenis urbanos", "Estilo casual, suela ergonómica y moderna", 120000, "👟"),
    Product(3, "Mochila de viaje", "Impermeable con compartimento para laptop", 65000, "🎒"),
    Product(4, "Gorra clásica", "Ajustable, excelente protección solar", 28000, "🧢"),
    Product(5, "Reloj inteligente", "Monitor cardiaco, pasos y notificaciones", 150000, "⌚"),
    Product(6, "Audífonos inalámbricos", "Cancelación de ruido y excelente batería", 85000, "🎧"),
    Product(7, "Chaqueta casual", "Diseño liviano e ideal para clima frío", 180000, "🧥"),
    Product(8, "Gafas de sol", "Protección UV400 con marco elegante", 45000, "🕶️")
)
