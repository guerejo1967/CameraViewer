package com.cameraviewer.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cameraviewer.app.adapter.SiteAdapter
import com.cameraviewer.app.database.DatabaseManager
import com.cameraviewer.app.model.Site
import com.cameraviewer.app.utils.ConfigManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SiteAdapter
    private lateinit var dbManager: DatabaseManager
    private lateinit var emptyView: View
    private val sites = mutableListOf<Site>()

    // Launcher per export
    private val exportLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                ConfigManager.exportConfig(this, sites, uri).fold(
                    onSuccess = { message ->
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    },
                    onFailure = { error ->
                        Toast.makeText(this, "Errore: ${error.message}", Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }

    // Launcher per import
    private val importLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                showImportConfirmDialog(uri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        dbManager = DatabaseManager.getInstance(this)

        recyclerView = findViewById(R.id.sitesRecyclerView)
        emptyView = findViewById(R.id.emptyView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = SiteAdapter(
            sites,
            onSiteClick = { site ->
                val intent = Intent(this, SiteDetailActivity::class.java)
                intent.putExtra("SITE_ID", site.id)
                startActivity(intent)
            },
            onViewCamerasClick = { site ->
                if (site.cameras.isEmpty()) {
                    AlertDialog.Builder(this)
                        .setTitle(R.string.no_cameras_title)
                        .setMessage(R.string.no_cameras_message)
                        .setPositiveButton(R.string.ok, null)
                        .show()
                } else {
                    val intent = Intent(this, CameraGridActivity::class.java)
                    intent.putExtra("SITE_ID", site.id)
                    startActivity(intent)
                }
            },
            onEditClick = { site ->
                val intent = Intent(this, AddEditSiteActivity::class.java)
                intent.putExtra("SITE_ID", site.id)
                startActivity(intent)
            },
            onDeleteClick = { site ->
                AlertDialog.Builder(this)
                    .setTitle(R.string.delete_site_title)
                    .setMessage(getString(R.string.delete_site_message, site.name))
                    .setPositiveButton(R.string.delete) { _, _ ->
                        dbManager.deleteSite(site.id)
                        loadSites()
                    }
                    .setNegativeButton(R.string.cancel, null)
                    .show()
            }
        )

        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fabAddSite).setOnClickListener {
            val intent = Intent(this, AddEditSiteActivity::class.java)
            startActivity(intent)
        }

        loadSites()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_export -> {
                exportConfiguration()
                true
            }
            R.id.action_import -> {
                importConfiguration()
                true
            }
            R.id.action_export_text -> {
                exportAsText()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun exportConfiguration() {
        if (sites.isEmpty()) {
            Toast.makeText(this, R.string.no_data_to_export, Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/json"
            putExtra(Intent.EXTRA_TITLE, "camera_config_${System.currentTimeMillis()}.json")
        }
        exportLauncher.launch(intent)
    }

    private fun importConfiguration() {
        AlertDialog.Builder(this)
            .setTitle(R.string.import_config_title)
            .setMessage(R.string.import_config_warning)
            .setPositiveButton(R.string.proceed) { _, _ ->
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "application/json"
                }
                importLauncher.launch(intent)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun showImportConfirmDialog(uri: android.net.Uri) {
        ConfigManager.importConfig(this, uri).fold(
            onSuccess = { importedSites ->
                val message = getString(
                    R.string.import_preview,
                    importedSites.size,
                    importedSites.sumOf { it.cameras.size }
                )
                
                AlertDialog.Builder(this)
                    .setTitle(R.string.confirm_import)
                    .setMessage(message)
                    .setPositiveButton(R.string.replace_all) { _, _ ->
                        sites.forEach { dbManager.deleteSite(it.id) }
                        importedSites.forEach { site ->
                            site.id = 0
                            site.cameras.forEach { it.id = 0 }
                            dbManager.addSite(site)
                        }
                        loadSites()
                        Toast.makeText(this, R.string.import_success, Toast.LENGTH_LONG).show()
                    }
                    .setNeutralButton(R.string.merge) { _, _ ->
                        importedSites.forEach { site ->
                            site.id = 0
                            site.cameras.forEach { it.id = 0 }
                            dbManager.addSite(site)
                        }
                        loadSites()
                        Toast.makeText(this, R.string.import_success, Toast.LENGTH_LONG).show()
                    }
                    .setNegativeButton(R.string.cancel, null)
                    .show()
            },
            onFailure = { error ->
                Toast.makeText(this, "Errore: ${error.message}", Toast.LENGTH_LONG).show()
            }
        )
    }

    private fun exportAsText() {
        if (sites.isEmpty()) {
            Toast.makeText(this, R.string.no_data_to_export, Toast.LENGTH_SHORT).show()
            return
        }

        val text = ConfigManager.exportConfigAsText(sites)
        
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
            putExtra(Intent.EXTRA_SUBJECT, "Configurazione Telecamere")
        }
        startActivity(Intent.createChooser(intent, getString(R.string.share_config)))
    }

    override fun onResume() {
        super.onResume()
        loadSites()
    }

    private fun loadSites() {
        sites.clear()
        sites.addAll(dbManager.getAllSites())
        adapter.notifyDataSetChanged()
        
        if (sites.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }
}
