package com.apps.a7pl4y3r.grades.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apps.a7pl4y3r.grades.R
import com.apps.a7pl4y3r.grades.objects.Subject

class SubjectAdapter : ListAdapter<Subject, SubjectAdapter.ViewHolder>(DIFF_CALLBACK) {

    private var listener: OnItemClickListener? = null

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Subject>() {

            override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
                return oldItem.title == newItem.title && oldItem.count == newItem.count
            }
        }

    }

    fun getSubjectAt(position: Int): Subject = getItem(position)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = getItem(position).title
        holder.subtitle.text = getItem(position).count
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {

            view.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION)
                    listener?.onItemClick(getItem(adapterPosition))
            }

        }

        val title: TextView = view.findViewById(R.id.cardTitle)
        val subtitle: TextView = view.findViewById(R.id.cardSubtitle)

    }

    interface OnItemClickListener {
        fun onItemClick(subject: Subject)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}