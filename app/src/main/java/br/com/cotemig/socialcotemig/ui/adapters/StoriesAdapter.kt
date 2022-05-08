package br.com.cotemig.socialcotemig.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.Story
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class StoriesAdapter(private var context: Context, private var stories: List<Story>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_newstory, parent, false)
            NewStoriesViewHolder(view)
        }else{
            val view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false)
            StoryHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            0
        } else {
            1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as NewStoriesViewHolder).bind()
        } else {
            (holder as StoryHolder).bind(context, stories[position -1])
        }
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    class NewStoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {}
    }

    class StoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(context: Context, stories: Story) {

            val avatar = itemView.findViewById<ImageView>(R.id.avatar)
            Glide.with(context).load(stories.avatar)
                .apply(
                    RequestOptions().transform(RoundedCorners(100))
                ).into(avatar)

            val live = itemView.findViewById<ImageView>(R.id.live)
            if (!stories.live) {
                live.visibility = View.GONE
            }

            val image = itemView.findViewById<ImageView>(R.id.storyImage)
            Glide.with(context).load(stories.avatar).apply(
                RequestOptions().transform(RoundedCorners(48))
            ).into(image)
        }
    }
}