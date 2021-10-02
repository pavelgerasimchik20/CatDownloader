package com.geras.cats.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.geras.cats.R
import com.geras.cats.model.Cat
import com.squareup.picasso.Picasso

class Adapter(private val onClickAction: () -> Unit) :
    RecyclerView.Adapter<Adapter.CatViewHolder>() {

    var catsImages = mutableListOf<Cat>()

    fun updateCats(cats: List<Cat>) {
        catsImages.clear()
        catsImages.addAll(cats)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.cat_item, parent, false)
        item.setOnClickListener { onClickAction.invoke() }
        return CatViewHolder(item)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = catsImages[position]
        holder.bind(item)

        Picasso.get().load(catsImages[position].url).into(holder.ivIcon)
    }

    override fun getItemCount() = catsImages.size

    class CatViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val ivIcon: ImageView = item.findViewById(R.id.imageView)

        fun bind(cat: Cat) {
            ivIcon.setOnClickListener {}
        }
    }
}


