package com.example.pw4_2.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pw4_2.data.ReactiveStation

@Composable
fun BasicApp() {
    val stations = remember {
        listOf(
            ReactiveStation("КУ-110 кВ (ПС «Північна»)", "Батарея конденсаторів", "Підстанція 110/35 кВ"),
            ReactiveStation("СШК-35 кВ (ПС «Центральна»)", "Статичний тиристорний компенсатор", "Розподільчий пункт"),
            ReactiveStation("ДУ-10 кВ (ПС «Заводська»)", "Дросель для компенсації", "Цехова підстанція"),
            ReactiveStation("КУ-35 кВ (ПС «Східна»)", "Автоматична батарея конденсаторів", "Вузлова підстанція"),
            ReactiveStation("ФКУ-10 кВ (ПС «Південна»)", "Фільтро-компенсувальна установка", "Кабельна мережа")
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = "Електричні мережі",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Компенсація реактивної потужності",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(stations) { station ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = station.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Тип: ${station.type}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Розташування: ${station.location}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}