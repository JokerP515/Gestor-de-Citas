package com.jokerp515.gestordecitas.ui.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jokerp515.gestordecitas.ui.navigation.routes.Routes
import com.jokerp515.gestordecitas.ui.screen.citas.ListaCitasScreen
import com.jokerp515.gestordecitas.ui.screen.MenuPrincipalScreen
import com.jokerp515.gestordecitas.ui.screen.citas.ModificarCitaScreen
import com.jokerp515.gestordecitas.ui.screen.citas.SolicitudCitaScreen
import com.jokerp515.gestordecitas.ui.screen.usuarios.ModificarUsuarioScreen
import com.jokerp515.gestordecitas.viewmodel.citas.ListaCitasViewModel
import com.jokerp515.gestordecitas.viewmodel.citas.ModificarCitaViewModel
import com.jokerp515.gestordecitas.viewmodel.citas.SolicitudCitaViewModel
import com.jokerp515.gestordecitas.viewmodel.usuarios.MenuPrincipalViewModel
import com.jokerp515.gestordecitas.viewmodel.usuarios.ModificarUsuarioViewModel

fun NavGraphBuilder.menuGraph(go: (Any) -> Unit) {
    navigation<Graph.MenuGraph>(startDestination = Routes.MenuPrincipal) {
        composable<Routes.MenuPrincipal> {
            val viewModel: MenuPrincipalViewModel = hiltViewModel()
            MenuPrincipalScreen(viewModel, go)
        }
        composable<Routes.RegistroCitas> {
            val viewModel: SolicitudCitaViewModel = hiltViewModel()
            SolicitudCitaScreen(viewModel, go)
        }
        composable<Routes.ListaCitas> {
            val viewModel: ListaCitasViewModel = hiltViewModel()
            ListaCitasScreen(viewModel, go)
        }

        composable<Routes.ModificarUsuario> {
            val viewModel: ModificarUsuarioViewModel = hiltViewModel()
            ModificarUsuarioScreen(viewModel, go)
        }

        composable<Routes.ModificarCitaId> {
            val citaId = it.arguments?.getLong("citaId") ?: -1L
            val viewModel: ModificarCitaViewModel = hiltViewModel()
            ModificarCitaScreen(citaId, viewModel, go)
        }

//        composable<Routes.ModificarCita> { navBackStackEntry ->
//            val citaId = navBackStackEntry.arguments?.getLong("citaId") ?: 1L
//            val viewModel: ModificarCitaViewModel = hiltViewModel()
//            ModificarCitaScreen(citaId, viewModel, go)
//        }




    }
}