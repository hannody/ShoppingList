package com.abunayla.shoppinglist.others

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abunayla.shoppinglist.R
import com.abunayla.shoppinglist.data.db.entities.ShoppingItem
import com.abunayla.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter (var items:List<ShoppingItem>, private val viewModel: ShoppingViewModel):
RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent,
        false)

        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        holder.itemView.apply {
            val current_shoppingItem = items[position]

            tvName.text = current_shoppingItem.name
            tvAmount.text = current_shoppingItem.amount.toString()

            holder.itemView.ivDelete.setOnClickListener{
                viewModel.delete(current_shoppingItem)
        }
            holder.itemView.ivPlus.setOnClickListener {
            current_shoppingItem.amount++
            viewModel.upsert(current_shoppingItem)
            }

            holder.itemView.ivMinus.setOnClickListener {
                if(current_shoppingItem.amount >1) {
                    current_shoppingItem.amount--
                    viewModel.upsert(current_shoppingItem)
                }
            }

    }
}

    inner class ShoppingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}