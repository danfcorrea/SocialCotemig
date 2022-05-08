package br.com.cotemig.socialcotemig.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.Post
import br.com.cotemig.socialcotemig.models.Story
import br.com.cotemig.socialcotemig.services.RetrofitInitializer
import br.com.cotemig.socialcotemig.ui.adapters.FeedAdapter
import br.com.cotemig.socialcotemig.ui.adapters.StoriesAdapter
import retrofit2.Call
import retrofit2.Response

class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_feed, container, false)
            getStories(view)
            getFeed(view)
        return view
    }

    fun getFeed(view : View){

        var s = RetrofitInitializer().serviceFeed()
        var call = s.getFeed()

        call.enqueue(object : retrofit2.Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if(response.code()==200){
                    response.body()?.let {
                        showFeed(view,it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(context, "Ops", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun showFeed(view: View,list: List<Post>){
        var recyclerViewFeed = view.findViewById<RecyclerView>(R.id.recyclerViewFeed)
        recyclerViewFeed.adapter = FeedAdapter(context!!, list)
        recyclerViewFeed.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun getStories(view: View){

        var s = RetrofitInitializer().serviceStories()
        var call = s.getStories()
        call.enqueue(object : retrofit2.Callback<List<Story>>{
            override fun onResponse(call: Call<List<Story>>, response: Response<List<Story>>) {
                if(response.code() == 200){
                    response.body()?.let{
                        showStories(view,it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Story>>, t: Throwable) {
                Toast.makeText(context, "Ops", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun showStories(view: View,stories : List<Story>){
        var recyclerview = view.findViewById<RecyclerView>(R.id.recyclerViewStory)
        recyclerview.adapter = StoriesAdapter(context!!, stories)
        recyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {

        fun newInstance(): FeedFragment{
            return FeedFragment()
        }
    }
}