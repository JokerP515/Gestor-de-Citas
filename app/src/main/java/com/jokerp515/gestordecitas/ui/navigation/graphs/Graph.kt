package com.jokerp515.gestordecitas.ui.navigation.graphs

import kotlinx.serialization.Serializable

sealed class Graph {
    @Serializable
    data object StartGraph : Graph()
    @Serializable
    data object MenuGraph : Graph()
}