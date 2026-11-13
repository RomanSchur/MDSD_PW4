package com.example.pw4_3.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pw4_3.data.ReactiveStation
import java.time.LocalDate
class ReactiveStationViewModel : ViewModel() {
    private val _stations = mutableStateListOf(
        ReactiveStation("КУ-110 кВ", "Capacitor", 1500.0, LocalDate.of(2026, 6, 1), "Active"),
        ReactiveStation("СШК-35 кВ", "Filtre", 800.0, LocalDate.of(2026, 6, 3), "Active"),
        ReactiveStation("ДУ-10 кВ", "Reactor", 400.0, LocalDate.of(2026, 6, 2), "Maintenance"),
        ReactiveStation("КУ-35 кВ", "Capacitor", 1200.0, LocalDate.of(2026, 6, 4), "Active")
    )
    var searchQuery by mutableStateOf("")
    var sortBy by mutableStateOf("Name")
    val filteredStations: List<ReactiveStation>
        get() {
            var result = _stations.toList()
            if (searchQuery.isNotEmpty()) {
                result = result.filter { it.name.contains(searchQuery, ignoreCase = true) }
            }
            result = when (sortBy) {
                "Name" -> result.sortedBy { it.name }
                "Type" -> result.sortedBy { it.type }
                else -> result
            }

            return result
        }
    fun updateSearchQuery(query: String) {
        searchQuery = query
    }
    fun updateSortBy(criteria: String) {
        sortBy = criteria
    }
}