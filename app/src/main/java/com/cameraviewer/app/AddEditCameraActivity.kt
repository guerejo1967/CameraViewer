package com.cameraviewer.app

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cameraviewer.app.database.DatabaseManager
import com.cameraviewer.app.model.Camera
import com.google.android.material.textfield.TextInputEditText

class AddEditCameraActivity : AppCompatActivity() {

    private lateinit var editCameraName: TextInputEditText
    private lateinit var editRtspUrl: TextInputEditText
    private lateinit var editUsername: TextInputEditText
    private lateinit var editPassword: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var dbManager: DatabaseManager
    
    private var siteId: Long = -1
    private var cameraId: Long = -1
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_camera)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dbManager = DatabaseManager.getInstance(this)

        editCameraName = findViewById(R.id.editCameraName)
        editRtspUrl = findViewById(R.id.editRtspUrl)
        editUsername = findViewById(R.id.editUsername)
        editPassword = findViewById(R.id.editPassword)
        btnSave = findViewById(R.id.btnSaveCamera)

        siteId = intent.getLongExtra("SITE_ID", -1)
        cameraId = intent.getLongExtra("CAMERA_ID", -1)
        isEditMode = cameraId != -1L

        if (siteId == -1L) {
            finish()
            return
        }

        if (isEditMode) {
            supportActionBar?.title = getString(R.string.edit_camera)
            loadCamera()
        } else {
            supportActionBar?.title = getString(R.string.add_camera)
        }

        btnSave.setOnClickListener {
            saveCamera()
        }
    }

    private fun loadCamera() {
        val cameras = dbManager.getCamerasBySite(siteId)
        val camera = cameras.find { it.id == cameraId }
        camera?.let {
            editCameraName.setText(it.name)
            editRtspUrl.setText(it.rtspUrl)
            editUsername.setText(it.username)
            editPassword.setText(it.password)
        }
    }

    private fun saveCamera() {
        val name = editCameraName.text.toString().trim()
        val rtspUrl = editRtspUrl.text.toString().trim()
        val username = editUsername.text.toString().trim()
        val password = editPassword.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, R.string.camera_name_required, Toast.LENGTH_SHORT).show()
            return
        }

        if (rtspUrl.isEmpty()) {
            Toast.makeText(this, R.string.rtsp_url_required, Toast.LENGTH_SHORT).show()
            return
        }

        if (!rtspUrl.startsWith("rtsp://")) {
            Toast.makeText(this, R.string.invalid_rtsp_url, Toast.LENGTH_SHORT).show()
            return
        }

        if (isEditMode) {
            val cameras = dbManager.getCamerasBySite(siteId)
            val camera = cameras.find { it.id == cameraId }
            camera?.let {
                it.name = name
                it.rtspUrl = rtspUrl
                it.username = username
                it.password = password
                dbManager.updateCamera(it)
                Toast.makeText(this, R.string.camera_updated, Toast.LENGTH_SHORT).show()
            }
        } else {
            val camera = Camera(
                siteId = siteId,
                name = name,
                rtspUrl = rtspUrl,
                username = username,
                password = password
            )
            dbManager.addCamera(camera)
            Toast.makeText(this, R.string.camera_added, Toast.LENGTH_SHORT).show()
        }

        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
