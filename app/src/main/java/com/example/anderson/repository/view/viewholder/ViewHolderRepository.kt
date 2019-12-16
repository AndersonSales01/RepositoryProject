package com.example.anderson.repository.view.viewholder

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.anderson.repository.constants.Constants
import com.example.anderson.repository.model.entity.Repository
import com.example.anderson.repository.view.activity.PullRequestActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_repository.view.*

/**
 * Created by Anderson on 16/12/2018.
 */
class ViewHolderRepository(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {


    fun bindView(repository: Repository) {

        val txtRepositoryName = itemView.repo_name
        val txtLogin = itemView.login
       // val txtUserName = itemView.user_name
        val txtNumberStarts = itemView.number_stars
        val txtNumberForks = itemView.number_forks
        val txtDescription = itemView.description


        txtRepositoryName.text = repository.name
        txtLogin.text = repository.author.name
       // txtUserName.text = repository.author.name
        txtNumberStarts.text = repository.numberStarts.toString()
        txtNumberForks.text = repository.numberForks.toString()
        txtDescription.text = repository.description

        getImagem(repository.author.urlAvatar)

        itemView.setOnClickListener {
            val intent = Intent(context, PullRequestActivity::class.java)
            intent.putExtra(Constants.NAME_OWNER, repository.author.name)
            intent.putExtra(Constants.NAME_REPOSITORY, repository.name)
            context.startActivity(intent)
        }
    }

    private fun getImagem(urlImg: String) {
        Picasso
                .with(context)
                .load(urlImg)
                .into(itemView.ic_user)
    }


}