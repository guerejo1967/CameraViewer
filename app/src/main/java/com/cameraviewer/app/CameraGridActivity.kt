package com.cameraviewer.app

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cameraviewer.app.adapter.CameraGridAdapter
import com.cameraviewer.app.database.DatabaseManager
import com.cameraviewer.app.model.Camera

class CameraGridActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CameraGridAdapter
    private lateinit var dbManager: DatabaseManager
    private lateinit var tvSiteName: TextView
    private val cameras = mutableListOf<Camera>()
    private var siteId: Long = -1
    private var gridColumns = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_grid)

        siteId = intent.getLongExtra("SITE_ID", -1)
        if (siteId == -1L) {
            finish()
            return
        }

        dbManager = DatabaseManager.getInstance(this)
        val site = dbManager.getSiteById(siteId)

        tvSiteName = findViewById(R.id.tvSiteName)
        tvSiteName.text = site?.name ?: getString(R.string.camera_grid)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<ImageButton>(R.id.btnGridSize).setOnClickListener {
            showGridSizeDialog()
        }

        recyclerView = findViewById(R.id.cameraGridRecyclerView)
        setupRecyclerView()

        loadCameras()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, gridColumns)
        adapter = CameraGridAdapter(this, cameras)
        recyclerView.adapter = adapter
    }

    private fun loadCameras() {
        cameras.clear()
        cameras.addAll(dbManager.getCamerasBySite(siteId))
        adapter.notifyDataSetChanged()
    }

    private fun showGridSizeDialog() {
        val options = arrayOf("1x1", "2x2", "3x3", "4x4")
        val currentSelection = when (gridColumns) {
            1 -> 0
            2 -> 1
            3 -> 2
            4 -> 3
            else -> 1
        }

        AlertDialog.Builder(this)
            .setTitle(R.string.select_grid_size)
            .setSingleChoiceItems(options, currentSelection) { dialog, which ->
                gridColumns = which + 1
                (recyclerView.layoutManager as GridLayoutManager).spanCount = gridColumns
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    override fun onDestroy() {
        adapter.releaseAllPlayers()
        super.onDestroy()
    }
}
