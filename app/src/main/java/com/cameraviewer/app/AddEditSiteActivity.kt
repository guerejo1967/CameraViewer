package com.cameraviewer.app

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cameraviewer.app.database.DatabaseManager
import com.cameraviewer.app.model.Site
import com.google.android.material.textfield.TextInputEditText

class AddEditSiteActivity : AppCompatActivity() {

    private lateinit var editSiteName: TextInputEditText
    private lateinit var editSiteDescription: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var dbManager: DatabaseManager
    
    private var siteId: Long = -1
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_site)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dbManager = DatabaseManager.getInstance(this)

        editSiteName = findViewById(R.id.editSiteName)
        editSiteDescription = findViewById(R.id.editSiteDescription)
        btnSave = findViewById(R.id.btnSaveSite)

        siteId = intent.getLongExtra("SITE_ID", -1)
        isEditMode = siteId != -1L

        if (isEditMode) {
            supportActionBar?.title = getString(R.string.edit_site)
            loadSite()
        } else {
            supportActionBar?.title = getString(R.string.add_site)
        }

        btnSave.setOnClickListener {
            saveSite()
        }
    }

    private fun loadSite() {
        val site = dbManager.getSiteById(siteId)
        site?.let {
            editSiteName.setText(it.name)
            editSiteDescription.setText(it.description)
        }
    }

    private fun saveSite() {
        val name = editSiteName.text.toString().trim()
        val description = editSiteDescription.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, R.string.site_name_required, Toast.LENGTH_SHORT).show()
            return
        }

        if (isEditMode) {
            val site = dbManager.getSiteById(siteId)
            site?.let {
                it.name = name
                it.description = description
                dbManager.updateSite(it)
                Toast.makeText(this, R.string.site_updated, Toast.LENGTH_SHORT).show()
            }
        } else {
            val site = Site(name = name, description = description)
            dbManager.addSite(site)
            Toast.makeText(this, R.string.site_added, Toast.LENGTH_SHORT).show()
        }

        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
