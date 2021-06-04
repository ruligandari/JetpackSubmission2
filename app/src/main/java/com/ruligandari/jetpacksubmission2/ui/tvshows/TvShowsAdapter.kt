package com.ruligandari.jetpacksubmission2.ui.tvshows

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ruligandari.jetpacksubmission2.R
import com.ruligandari.jetpacksubmission2.data.source.local.TvShowsEntity
import com.ruligandari.jetpacksubmission2.databinding.ItemTvShowsBinding
import com.ruligandari.jetpacksubmission2.ui.detail.DetailTvShowsActivity

class TvShowsAdapter: RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {
    private var listTvShows = ArrayList<TvShowsEntity>()
    companion object{
        const val BASE_URL = "https://image.tmdb.org/t/p/original"
    }

    fun setTvShows(tvShows: List<TvShowsEntity>?){
        if (tvShows != null){
            this.listTvShows.clear()
            this.listTvShows.addAll(tvShows)
            notifyDataSetChanged()
        }
    }

    class TvShowsViewHolder(private val binding: ItemTvShowsBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("WrongConstant")
        fun bind(data: TvShowsEntity){
            with(binding){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    tvItemDescription.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
                }
                tvItemTitle.text = data.name
                tvItemRelease.text = data.first_air_date
                tvItemDescription.text = data.overview
                Glide.with(itemView.context)
                    .load(BASE_URL +data.poster_path)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster)

                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailTvShowsActivity:: class.java)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
       val itemTvShowsBinding = ItemTvShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemTvShowsBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
       val tvShows = listTvShows[position]
    }

    override fun getItemCount(): Int = listTvShows.size
}