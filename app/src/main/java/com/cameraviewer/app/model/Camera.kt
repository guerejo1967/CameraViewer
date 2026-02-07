package com.cameraviewer.app.model

import java.io.Serializable

data class Camera(
    var id: Long = 0,
    var siteId: Long = 0,
    var name: String = "",
    var rtspUrl: String = "",
    var username: String = "",
    var password: String = ""
) : Serializable {
    fun getRtspUrlWithAuth(): String {
        return if (username.isNotEmpty() && password.isNotEmpty()) {
            // Inserisce username e password nell'URL RTSP
            rtspUrl.replaceFirst("rtsp://", "rtsp://$username:$password@")
        } else {
            rtspUrl
        }
    }
}
