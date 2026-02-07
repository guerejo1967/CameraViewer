package com.cameraviewer.app.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cameraviewer.app.R
import com.cameraviewer.app.model.Camera
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout

class CameraGridAdapter(
    private val context: Context,
    private val cameras: List<Camera>
) : RecyclerView.Adapter<CameraGridAdapter.CameraStreamViewHolder>() {

    private val libVLC: LibVLC = LibVLC(context, arrayListOf("--rtsp-tcp"))
    private val mediaPlayers = mutableMapOf<Int, MediaPlayer>()

    class CameraStreamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val videoLayout: VLCVideoLayout = view.findViewById(R.id.videoLayout)
        val cameraName: TextView = view.findViewById(R.id.cameraNameOverlay)
        val loadingProgress: ProgressBar = view.findViewById(R.id.loadingProgress)
        val errorText: TextView = view.findViewById(R.id.errorText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraStreamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_camera_stream, parent, false)
        return CameraStreamViewHolder(view)
    }

    override fun onBindViewHolder(holder: CameraStreamViewHolder, position: Int) {
        val camera = cameras[position]
        holder.cameraName.text = camera.name

        // Release previous player if exists
        mediaPlayers[position]?.let {
            it.stop()
            it.release()
        }

        // Create new media player
        val mediaPlayer = MediaPlayer(libVLC)
        mediaPlayers[position] = mediaPlayer

        mediaPlayer.attachViews(holder.videoLayout, null, false, false)

        // Create media with RTSP URL
        val media = Media(libVLC, Uri.parse(camera.getRtspUrlWithAuth()))
        media.setHWDecoderEnabled(true, false)
        media.addOption(":network-caching=300")
        media.addOption(":clock-jitter=0")
        media.addOption(":clock-synchro=0")

        mediaPlayer.media = media
        media.release()

        // Set event listener
        mediaPlayer.setEventListener { event ->
            when (event.type) {
                MediaPlayer.Event.Playing -> {
                    holder.loadingProgress.visibility = View.GONE
                    holder.errorText.visibility = View.GONE
                }
                MediaPlayer.Event.EncounteredError -> {
                    holder.loadingProgress.visibility = View.GONE
                    holder.errorText.visibility = View.VISIBLE
                }
                MediaPlayer.Event.Buffering -> {
                    if (event.buffering < 100f) {
                        holder.loadingProgress.visibility = View.VISIBLE
                    } else {
                        holder.loadingProgress.visibility = View.GONE
                    }
                }
                else -> {}
            }
        }

        // Start playing
        mediaPlayer.play()
    }

    override fun getItemCount() = cameras.size

    override fun onViewRecycled(holder: CameraStreamViewHolder) {
        super.onViewRecycled(holder)
        val position = holder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            mediaPlayers[position]?.let {
                it.stop()
                it.detachViews()
            }
        }
    }

    fun releaseAllPlayers() {
        mediaPlayers.values.forEach {
            it.stop()
            it.release()
        }
        mediaPlayers.clear()
        libVLC.release()
    }
}
