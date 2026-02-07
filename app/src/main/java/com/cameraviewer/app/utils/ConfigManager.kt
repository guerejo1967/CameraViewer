package com.cameraviewer.app.utils

import android.content.Context
import android.net.Uri
import com.cameraviewer.app.model.Site
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

object ConfigManager {

    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    data class ConfigData(
        val version: Int = 1,
        val exportDate: String,
        val sites: List<Site>
    )

    /**
     * Esporta la configurazione in formato JSON
     */
    fun exportConfig(context: Context, sites: List<Site>, uri: Uri): Result<String> {
        return try {
            val configData = ConfigData(
                exportDate = getCurrentDateTime(),
                sites = sites
            )
            
            val json = gson.toJson(configData)
            
            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write(json)
                }
            }
            
            Result.success("Configurazione esportata con successo")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Importa la configurazione da un file JSON
     */
    fun importConfig(context: Context, uri: Uri): Result<List<Site>> {
        return try {
            val json = context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    reader.readText()
                }
            } ?: return Result.failure(Exception("Impossibile leggere il file"))

            val configData = gson.fromJson(json, ConfigData::class.java)
            
            if (configData.sites.isEmpty()) {
                return Result.failure(Exception("Il file non contiene siti validi"))
            }
            
            Result.success(configData.sites)
        } catch (e: Exception) {
            Result.failure(Exception("Errore durante l'importazione: ${e.message}"))
        }
    }

    /**
     * Esporta come testo semplice per backup manuale
     */
    fun exportConfigAsText(sites: List<Site>): String {
        val sb = StringBuilder()
        sb.appendLine("=== CONFIGURAZIONE TELECAMERE ===")
        sb.appendLine("Data esportazione: ${getCurrentDateTime()}")
        sb.appendLine()
        
        sites.forEachIndexed { index, site ->
            sb.appendLine("SITO ${index + 1}: ${site.name}")
            sb.appendLine("Descrizione: ${site.description}")
            sb.appendLine("Numero telecamere: ${site.cameras.size}")
            sb.appendLine()
            
            site.cameras.forEachIndexed { camIndex, camera ->
                sb.appendLine("  Camera ${camIndex + 1}: ${camera.name}")
                sb.appendLine("  URL RTSP: ${camera.rtspUrl}")
                if (camera.username.isNotEmpty()) {
                    sb.appendLine("  Username: ${camera.username}")
                }
                if (camera.password.isNotEmpty()) {
                    sb.appendLine("  Password: ${camera.password}")
                }
                sb.appendLine()
            }
            sb.appendLine("---")
            sb.appendLine()
        }
        
        return sb.toString()
    }

    private fun getCurrentDateTime(): String {
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
        return sdf.format(java.util.Date())
    }
}
