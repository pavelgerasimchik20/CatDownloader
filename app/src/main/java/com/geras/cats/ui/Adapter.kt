package com.geras.cats.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geras.cats.R
import com.geras.cats.data.Cat

class Adapter(private val onClickAction: () -> Unit) : RecyclerView.Adapter<CatViewHolder>() {

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
    }

    override fun getItemCount() = catsImages.size
}

class CatViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    private val ivIcon: ImageView = item.findViewById(R.id.imageView)

    fun bind(cat: Cat) {
        ivIcon.load(cat.url) {
            crossfade(true)
            placeholder(R.drawable.place_holder)
        }
    }
}
