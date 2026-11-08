package com.example.my3c

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CoffeeShopApp()
            }
        }
    }
}

@Composable
fun CoffeeShopApp() {
    var currentScreen by remember { mutableStateOf("profile") }

    val orderList = remember {
        mutableStateListOf(
            "Капучино - 200 ₽",
            "Латте - 250 ₽",
            "Раф ванильный - 280 ₽",
            "Американо - 150 ₽",
            "Круассан с шоколадом - 140 ₽"
        )
    }

    if (currentScreen == "profile") {
        ProfileScreen(
            onNavigateToOrder = { currentScreen = "order" },
            onNavigateToBonus = { currentScreen = "bonus" }
        )
    } else if (currentScreen == "order") {
        ShoppingListScreen(
            items = orderList,
            onBack = { currentScreen = "profile" }
        )
    } else if (currentScreen == "bonus") {
        BonusCardScreen(
            onBack = { currentScreen = "profile" }
        )
    }
}

@Composable
fun ProfileHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0E0)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Аватар",
                modifier = Modifier.size(56.dp),
                tint = Color(0xFF6F4E37)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Данила Зубец",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "grak1ro.01@gmail.com",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun SettingsItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color(0xFF6F4E37),
            modifier = Modifier.size(26.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = ">",
            fontSize = 18.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun ProfileScreen(onNavigateToOrder: () -> Unit, onNavigateToBonus: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        ProfileHeader()

        Spacer(modifier = Modifier.height(32.dp))
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        SettingsItem(
            icon = Icons.Default.ShoppingCart,
            title = "Мой заказ и меню кофейни",
            onClick = onNavigateToOrder
        )
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        SettingsItem(
            icon = Icons.Default.Star,
            title = "Карта лояльности и статус",
            onClick = onNavigateToBonus
        )
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        SettingsItem(
            icon = Icons.Default.Notifications,
            title = "Уведомления об акциях",
            onClick = onNavigateToBonus
        )
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        SettingsItem(
            icon = Icons.Default.Favorite,
            title = "Любимые напитки",
            onClick = onNavigateToOrder
        )
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavigateToOrder,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6F4E37)
            )
        ) {
            Text(
                text = "Перейти к заказу",
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ShoppingItem(item: String, onItemClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5EFE6))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Товар",
                tint = Color(0xFF6F4E37)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = item,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = onItemClicked) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Удалить"
                )
            }
        }
    }
}

@Composable
fun ShoppingListScreen(items: MutableList<String>, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Корзина кофейни",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        Text(
            text = "Позиций в заказе: ${items.size}",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(items) { item ->
                ShoppingItem(
                    item = item,
                    onItemClicked = {
                        items.remove(item)
                    }
                )
            }
        }

        Button(
            onClick = { items.add("Эспрессо - 150 ₽") },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
        ) {
            Text(text = "+ Добавить Эспрессо", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Назад в профиль", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun BonusCardScreen(onBack: () -> Unit) {
    var cardLevel by remember { mutableStateOf("Bronze (5%)") }
    var cupsCount by remember { mutableStateOf(1) }

    val badgeColor = when (cardLevel) {
        "Bronze (5%)" -> Color(0xFFCD7F32)
        "Silver (10%)" -> Color(0xFF607D8B)
        "Gold (15%)" -> Color(0xFFD4AF37)
        else -> Color.Gray
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Карта лояльности",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = cardLevel,
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(badgeColor)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .clickable {
                    cardLevel = when (cardLevel) {
                        "Bronze (5%)" -> "Silver (10%)"
                        "Silver (10%)" -> "Gold (15%)"
                        else -> "Bronze (5%)"
                    }
                }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Выпито чашек кофе: $cupsCount",
            fontSize = 20.sp
        )

        Button(
            onClick = { cupsCount++ },
            modifier = Modifier.padding(top = 12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6F4E37))
        ) {
            Text("Выпить ещё чашку (+1)")
        }

        Button(
            onClick = {
                cardLevel = when (cardLevel) {
                    "Bronze (5%)" -> "Silver (10%)"
                    "Silver (10%)" -> "Gold (15%)"
                    else -> "Bronze (5%)"
                }
            },
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text("Сменить уровень карты")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Вернуться в профиль", color = Color.White, fontSize = 16.sp)
        }
    }
}