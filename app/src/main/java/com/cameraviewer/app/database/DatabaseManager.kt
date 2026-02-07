package com.cameraviewer.app.database

import android.content.Context
import android.content.SharedPreferences
import com.cameraviewer.app.model.Camera
import com.cameraviewer.app.model.Site
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseManager private constructor(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()
    
    companion object {
        private const val PREFS_NAME = "CameraViewerDB"
        private const val KEY_SITES = "sites"
        private var INSTANCE: DatabaseManager? = null
        
        fun getInstance(context: Context): DatabaseManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DatabaseManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
    
    // Ottiene tutti i siti
    fun getAllSites(): MutableList<Site> {
        val json = prefs.getString(KEY_SITES, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Site>>() {}.type
        return gson.fromJson(json, type)
    }
    
    // Salva tutti i siti
    private fun saveSites(sites: MutableList<Site>) {
        val json = gson.toJson(sites)
        prefs.edit().putString(KEY_SITES, json).apply()
    }
    
    // Aggiunge un nuovo sito
    fun addSite(site: Site): Long {
        val sites = getAllSites()
        val newId = (sites.maxOfOrNull { it.id } ?: 0) + 1
        site.id = newId
        sites.add(site)
        saveSites(sites)
        return newId
    }
    
    // Aggiorna un sito esistente
    fun updateSite(site: Site) {
        val sites = getAllSites()
        val index = sites.indexOfFirst { it.id == site.id }
        if (index != -1) {
            sites[index] = site
            saveSites(sites)
        }
    }
    
    // Elimina un sito
    fun deleteSite(siteId: Long) {
        val sites = getAllSites()
        sites.removeAll { it.id == siteId }
        saveSites(sites)
    }
    
    // Ottiene un sito per ID
    fun getSiteById(siteId: Long): Site? {
        return getAllSites().find { it.id == siteId }
    }
    
    // Aggiunge una camera a un sito
    fun addCamera(camera: Camera) {
        val sites = getAllSites()
        val site = sites.find { it.id == camera.siteId } ?: return
        val newId = (site.cameras.maxOfOrNull { it.id } ?: 0) + 1
        camera.id = newId
        site.cameras.add(camera)
        saveSites(sites)
    }
    
    // Aggiorna una camera
    fun updateCamera(camera: Camera) {
        val sites = getAllSites()
        val site = sites.find { it.id == camera.siteId } ?: return
        val index = site.cameras.indexOfFirst { it.id == camera.id }
        if (index != -1) {
            site.cameras[index] = camera
            saveSites(sites)
        }
    }
    
    // Elimina una camera
    fun deleteCamera(siteId: Long, cameraId: Long) {
        val sites = getAllSites()
        val site = sites.find { it.id == siteId } ?: return
        site.cameras.removeAll { it.id == cameraId }
        saveSites(sites)
    }
    
    // Ottiene tutte le camere di un sito
    fun getCamerasBySite(siteId: Long): List<Camera> {
        return getSiteById(siteId)?.cameras ?: emptyList()
    }
}
