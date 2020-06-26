package com.abunayla.shoppinglist.ui.shoppinglist

import com.abunayla.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item:ShoppingItem)
}