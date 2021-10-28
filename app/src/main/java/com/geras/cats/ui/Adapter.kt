package com.geras.cats.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geras.cats.R
import com.geras.cats.data.Cat

class Adapter(private val onItemClickAction: (cat: Cat) -> Unit) :
    RecyclerView.Adapter<CatViewHolder>() {

    var catsImages = mutableListOf<Cat>()

    fun updateCats(cats: List<Cat>) {
        catsImages.clear()
        catsImages.addAll(cats)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.cat_item, parent, false)
        val holder = CatViewHolder(item, onItemClickAction)
        /* item.setOnClickListener { onItemClickAction.invoke(catsImages[holder.adapterPosition]) }*/
        return holder
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        // Позиция из параметра не финальная. Это может привести к проблемам.
        // Лучше использовать holder.adapterPosition или holder.bindingAdapterPosition в recycler v1.2
        val item = catsImages[position]
        holder.bind(item)
    }

    override fun getItemCount() = catsImages.size
}

class CatViewHolder(item: View, private val onItemClickAction: (cat: Cat) -> Unit) :
    RecyclerView.ViewHolder(item) {

    // Для ViewHolder тоже можно использовать ViewBinding
    private val ivIcon: ImageView = item.findViewById(R.id.imageView)

    fun bind(cat: Cat) {
        ivIcon.load(cat.url) {
            crossfade(true)
            placeholder(R.drawable.place_holder)
        }
        // Нажатие нужно обрабатывать в onCreateViewHolder. Если обрабатывать сдесь, при каждом
        // onBindViewHolder будут создаваться объекты лямбды листенера.
        ivIcon.setOnClickListener {
            onItemClickAction.invoke(cat)
        }
    }
}
