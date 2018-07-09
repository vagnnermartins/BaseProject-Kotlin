package com.vagnnermartins.baseproject.projects

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.vagnnermartins.baseproject.R
import com.vagnnermartins.baseproject.inflate
import com.vagnnermartins.baseproject.load
import com.vagnnermartins.baseproject.model.ProjectItem
import kotlinx.android.synthetic.main.item_project.view.*
import java.util.*

class ProjectListAdapter constructor(private val context: Context,
                                     private val itemClick: (ProjectItem, View) -> Unit)
    : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    private val items = ArrayList<ProjectItem>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_project)) {

        fun bind(item: ProjectItem) {
            itemView.itemProjectAvatar.load(item.ownerAvatar)
            itemView.itemProjectName.text = item.name
            itemView.itemProjectDescription.text = item.description ?: context.getString(R.string.no_description)
            itemView.itemProjectLanguage.text = item.language
            itemView.setOnClickListener { itemClick.invoke(item, itemView) }
        }
    }

    fun addItems(list: List<ProjectItem>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }
}
