package com.cameraviewer.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cameraviewer.app.R
import com.cameraviewer.app.model.Camera

class CameraAdapter(
    private val cameras: List<Camera>,
    private val onEditClick: (Camera) -> Unit,
    private val onDeleteClick: (Camera) -> Unit
) : RecyclerView.Adapter<CameraAdapter.CameraViewHolder>() {

    class CameraViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cameraName: TextView = view.findViewById(R.id.cameraName)
        val cameraUrl: TextView = view.findViewById(R.id.cameraUrl)
        val btnEditCamera: ImageButton = view.findViewById(R.id.btnEditCamera)
        val btnDeleteCamera: ImageButton = view.findViewById(R.id.btnDeleteCamera)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_camera, parent, false)
        return CameraViewHolder(view)
    }

    override fun onBindViewHolder(holder: CameraViewHolder, position: Int) {
        val camera = cameras[position]
        
        holder.cameraName.text = camera.name
        holder.cameraUrl.text = camera.rtspUrl

        holder.btnEditCamera.setOnClickListener { onEditClick(camera) }
        holder.btnDeleteCamera.setOnClickListener { onDeleteClick(camera) }
    }

    override fun getItemCount() = cameras.size
}
