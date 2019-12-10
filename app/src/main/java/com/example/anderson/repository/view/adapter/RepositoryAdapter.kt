package com.example.anderson.repository.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.anderson.repository.R
import com.example.anderson.repository.model.entity.Repository
import com.example.anderson.repository.view.viewholder.ViewHolderRepository

/**
 * Created by Anderson on 16/12/2018.
 */
class RepositoryAdapter (val context: Context) : RecyclerView.Adapter<ViewHolderRepository>() {

    private var listRepository: List<Repository> = mutableListOf()

    fun loadRepository(listRepository: List<Repository>) {
        this.listRepository = listRepository
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolderRepository?, position: Int) {
        val repository = listRepository[position]

        holder?.bindView(repository)
    }

    override fun getItemCount(): Int {
        return listRepository.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderRepository {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_repository, parent, false)
        return ViewHolderRepository(view,context)
    }
}