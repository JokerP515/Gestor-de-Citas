package com.jokerp515.gestordecitas.ui.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jokerp515.gestordecitas.ui.navigation.routes.Routes
import com.jokerp515.gestordecitas.ui.screen.MenuPrincipalScreen
import com.jokerp515.gestordecitas.ui.screen.usuarios.RegisterScreen
import com.jokerp515.gestordecitas.viewmodel.usuarios.MenuPrincipalViewModel
import com.jokerp515.gestordecitas.viewmodel.usuarios.RegisterViewModel

fun NavGraphBuilder.startGraph(startDestination: Routes ,go: (Any) -> Unit) {
    navigation<Graph.StartGraph>(startDestination = startDestination){
        composable<Routes.Registro> {
            val viewModel: RegisterViewModel = hiltViewModel()
            RegisterScreen(viewModel, go)
        }
        composable<Routes.MenuPrincipal> {
            val viewModel: MenuPrincipalViewModel = hiltViewModel()
            MenuPrincipalScreen(viewModel, go)
        }
    }
}