package com.example.pw4_4.composables
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.example.pw4_4.data.EnergyStation
@Composable
fun BasicApp() {
    val context = LocalContext.current
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    val stations = remember {
        listOf(
            EnergyStation("ПС «Оболонь» 110 кВ", 50.5038, 30.4950, "Підстанція", "Головний вузол живлення району", 2000, "Активна"),
            EnergyStation("КУ-10 кВ (Оболонський проспект)", 50.5012, 30.5015, "Конденсаторна установка", "Компенсація реактивної потужності", 1000, "Активна"),
            EnergyStation("РП-35 кВ (Набережна)", 50.4950, 30.5120, "Розподільчий пункт", "Живлення набережної", 600, "Активна")
        )
    }
    var userLocation by remember {
        mutableStateOf(LatLng(50.5015, 30.5011))
    }
    var hasLocationPermission by remember {
        mutableStateOf(false)
    }
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasLocationPermission = isGranted
        if (isGranted) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        userLocation = LatLng(it.latitude, it.longitude)
                    }
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                hasLocationPermission = true
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                        location?.let {
                            userLocation = LatLng(it.latitude, it.longitude)
                        }
                    }
                }
            }
            else -> {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 14f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = true)
    ) {
        stations.forEach { station ->
            Marker(
                state = rememberMarkerState(position = LatLng(station.latitude, station.longitude)),
                title = station.name,
                snippet = "${station.type} • ${station.description}"
            )
        }
        if (hasLocationPermission) {
            Marker(
                state = rememberMarkerState(position = userLocation),
                title = "Ви тут",
                snippet = "Поточна позиція пристрою"
            )
        }
    }
}