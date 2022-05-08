package br.com.cotemig.socialcotemig.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.Post
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class   FeedAdapter(var context: Context, var list: List<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostHolder).bind(context, list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PostHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(context: Context, post: Post) {

            var username = view.findViewById<TextView>(R.id.username)
            username.text = post.user

            var locationName = view.findViewById<TextView>(R.id.locationName)
            locationName.text = post.local

            var date = view.findViewById<TextView>(R.id.date)
            date.text = post.date

            var description = view.findViewById<TextView>(R.id.description)
            description.text = post.description

            var likes = view.findViewById<TextView>(R.id.likes)
            likes.text = post.likes.size.toString()

            var avatar = view.findViewById<ImageView>(R.id.avatar)
            Glide.with(context).load(post.avatar)
                .apply(
                    RequestOptions().transform(RoundedCorners(100))
                ).into(avatar)

            var gallery = view.findViewById<RecyclerView>(R.id.gallery)
            gallery.adapter = GalleryAdapter(context, post.gallery)
            gallery.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            gallery.onFlingListener = null

            var snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(gallery)
//            var postImage = view.findViewById<ImageView>(R.id.postImage)
//            Glide.with(context).load(post.image).into(postImage)

        }
    }
}