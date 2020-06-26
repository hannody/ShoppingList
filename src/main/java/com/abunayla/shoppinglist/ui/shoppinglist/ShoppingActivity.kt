package com.abunayla.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abunayla.shoppinglist.R
import com.abunayla.shoppinglist.data.db.ShoppingDatabase
import com.abunayla.shoppinglist.data.db.entities.ShoppingItem
import com.abunayla.shoppinglist.data.repositories.ShoppingRepository
import com.abunayla.shoppinglist.others.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.Observer

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        abtn_add.setOnClickListener {
            AddShoppingItemDialog(this, object:AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }

    }
}