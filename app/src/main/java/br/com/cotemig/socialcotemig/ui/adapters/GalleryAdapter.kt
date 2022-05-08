package br.com.cotemig.socialcotemig.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.PostImage
import com.bumptech.glide.Glide

class GalleryAdapter(private var context: Context, private var gallery: List<PostImage>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false)
        return GalleryHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GalleryHolder).bind(context, gallery[position])
    }

    override fun getItemCount(): Int {
        return gallery.size
    }

    class GalleryHolder(private var view: View) : RecyclerView.ViewHolder(view){
        fun bind(context: Context, postImage: PostImage){
            val image = view.findViewById<ImageView>(R.id.postImage)
            Glide.with(context).load(postImage.image).into(image)
        }
    }
}