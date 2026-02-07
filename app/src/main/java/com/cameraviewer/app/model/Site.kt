package com.cameraviewer.app.model

import java.io.Serializable

data class Site(
    var id: Long = 0,
    var name: String = "",
    var description: String = "",
    var cameras: MutableList<Camera> = mutableListOf()
) : Serializable
