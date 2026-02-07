package com.cameraviewer.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cameraviewer.app.adapter.CameraAdapter
import com.cameraviewer.app.database.DatabaseManager
import com.cameraviewer.app.model.Camera
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SiteDetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CameraAdapter
    private lateinit var dbManager: DatabaseManager
    private lateinit var emptyView: View
    private val cameras = mutableListOf<Camera>()
    private var siteId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_detail)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        siteId = intent.getLongExtra("SITE_ID", -1)
        if (siteId == -1L) {
            finish()
            return
        }

        dbManager = DatabaseManager.getInstance(this)
        val site = dbManager.getSiteById(siteId)
        supportActionBar?.title = site?.name ?: getString(R.string.site_detail)

        recyclerView = findViewById(R.id.camerasRecyclerView)
        emptyView = findViewById(R.id.emptyCameraView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CameraAdapter(
            cameras,
            onEditClick = { camera ->
                val intent = Intent(this, AddEditCameraActivity::class.java)
                intent.putExtra("SITE_ID", siteId)
                intent.putExtra("CAMERA_ID", camera.id)
                startActivity(intent)
            },
            onDeleteClick = { camera ->
                AlertDialog.Builder(this)
                    .setTitle(R.string.delete_camera_title)
                    .setMessage(getString(R.string.delete_camera_message, camera.name))
                    .setPositiveButton(R.string.delete) { _, _ ->
                        dbManager.deleteCamera(siteId, camera.id)
                        loadCameras()
                    }
                    .setNegativeButton(R.string.cancel, null)
                    .show()
            }
        )

        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fabAddCamera).setOnClickListener {
            val intent = Intent(this, AddEditCameraActivity::class.java)
            intent.putExtra("SITE_ID", siteId)
            startActivity(intent)
        }

        loadCameras()
    }

    override fun onResume() {
        super.onResume()
        loadCameras()
    }

    private fun loadCameras() {
        cameras.clear()
        cameras.addAll(dbManager.getCamerasBySite(siteId))
        adapter.notifyDataSetChanged()
        
        if (cameras.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
