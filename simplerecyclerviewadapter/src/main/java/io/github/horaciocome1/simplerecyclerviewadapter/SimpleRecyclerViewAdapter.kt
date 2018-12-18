/*
 * Copyright 2018 Horácio Flávio Comé Júnior
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.github.horaciocome1.simplerecyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

const val NO_ITEM_LAYOUT = -1

class SimpleRecyclerViewAdapter<DataType> : Adapter<SimpleRecyclerViewAdapter.ViewHolder>() {

    var itemLayout: Int = NO_ITEM_LAYOUT
    var list = ArrayList<DataType>()
    private var onBind = { _: ViewHolder, _: DataType -> Unit}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int { return list.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { onBind(holder, list[position]) }

    fun setOnBindViewHolder(onBind: (SimpleRecyclerViewAdapter.ViewHolder, DataType) -> Unit) { this.onBind = onBind }

    fun addItem(item: DataType) {
        list.add(item)
        notifyItemInserted(list.size)
    }

    fun setItem(item: DataType, position: Int) {
        list[position] = item
        notifyItemChanged(position)
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: DataType, position: Int) {
        list.add(position, item)
        notifyItemInserted(position)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}