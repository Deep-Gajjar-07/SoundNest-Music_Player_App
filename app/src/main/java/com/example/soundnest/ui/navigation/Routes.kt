package com.example.soundnest.ui.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object Splash : Routes()

    @Serializable
    object Welcome : Routes()

    @Serializable
    object CreateProfile : Routes()

    @Serializable
    object Player : Routes()

    @Serializable
    object Library : Routes()

    @Serializable
    object Profile : Routes()

}