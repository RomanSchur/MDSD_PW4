package com.example.pw4_3.composables
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pw4_3.viewModels.ReactiveStationViewModel
@Composable
fun BasicApp(viewModel: ReactiveStationViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = "Мережі з компенсацією реактивної потужності",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = viewModel.searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            label = { Text("Пошук за назвою") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { viewModel.updateSortBy("Name") }) {
                Text("Сортувати за назвою")
            }
            Button(onClick = { viewModel.updateSortBy("Type") }) {
                Text("Сортувати за типом")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.filteredStations) { station ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Об'єкт: ${station.name}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Тип: ${station.type} | Потужність: ${station.power} кВАр")
                        Text(text = "Статус: ${station.status} (Оновлено: ${station.lastUpdate})")
                    }
                }
            }
        }
    }
}