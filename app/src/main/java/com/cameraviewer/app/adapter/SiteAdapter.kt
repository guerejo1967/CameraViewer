package com.cameraviewer.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cameraviewer.app.R
import com.cameraviewer.app.model.Site

class SiteAdapter(
    private val sites: List<Site>,
    private val onSiteClick: (Site) -> Unit,
    private val onViewCamerasClick: (Site) -> Unit,
    private val onEditClick: (Site) -> Unit,
    private val onDeleteClick: (Site) -> Unit
) : RecyclerView.Adapter<SiteAdapter.SiteViewHolder>() {

    class SiteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val siteName: TextView = view.findViewById(R.id.siteName)
        val siteDescription: TextView = view.findViewById(R.id.siteDescription)
        val cameraCount: TextView = view.findViewById(R.id.cameraCount)
        val btnViewCameras: Button = view.findViewById(R.id.btnViewCameras)
        val btnEditSite: ImageButton = view.findViewById(R.id.btnEditSite)
        val btnDeleteSite: ImageButton = view.findViewById(R.id.btnDeleteSite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_site, parent, false)
        return SiteViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        val site = sites[position]
        
        holder.siteName.text = site.name
        holder.siteDescription.text = site.description
        
        val cameraCountText = holder.itemView.context.getString(
            R.string.camera_count,
            site.cameras.size
        )
        holder.cameraCount.text = cameraCountText

        holder.itemView.setOnClickListener { onSiteClick(site) }
        holder.btnViewCameras.setOnClickListener { onViewCamerasClick(site) }
        holder.btnEditSite.setOnClickListener { onEditClick(site) }
        holder.btnDeleteSite.setOnClickListener { onDeleteClick(site) }
    }

    override fun getItemCount() = sites.size
}
