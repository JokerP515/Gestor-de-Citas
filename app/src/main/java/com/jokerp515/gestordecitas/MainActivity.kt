package com.jokerp515.gestordecitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jokerp515.gestordecitas.ui.navigation.graphs.Graph
import com.jokerp515.gestordecitas.ui.navigation.graphs.menuGraph
import com.jokerp515.gestordecitas.ui.navigation.graphs.startGraph
import com.jokerp515.gestordecitas.ui.navigation.routes.Routes
import com.jokerp515.gestordecitas.ui.theme.GestorDeCitasTheme
import com.jokerp515.gestordecitas.viewmodel.usuarios.MainViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestorDeCitasTheme {
                val viewModel: MainViewModel = viewModel()
                val isUserRegistered by viewModel.isUserRegistered.collectAsState()

                when (isUserRegistered) {
                    null -> LoadingScreen() // Pantalla de carga
                    true -> AppNavigation(Routes.MenuPrincipal)
                    false -> AppNavigation(Routes.Registro)
                }

            }
        }

    }
}

@Composable
fun AppNavigation(startDestination: Routes) {
    val navController = rememberNavController()

    Surface (
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = Graph.StartGraph
        ) {

            startGraph(startDestination = startDestination) { route ->
                navController.navigate(route)
            }

            menuGraph { route ->
                navController.navigate(route)
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onBackground,
                strokeWidth = 4.dp
            )
        }
    }
}
