package com.github.pagr0m.androidApp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.pagr0m.androidApp.R


class UserRecyclerViewAdapter(
    private val users: List<User>
) : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.user_item, parent, false)

        return UserViewHolder(userView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = users[position]

        holder.nameView.text = currentUser.name
        holder.emailView.text = currentUser.email
        holder.phoneView.text = currentUser.phone
    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(userView: View) : RecyclerView.ViewHolder(userView) {
        val nameView: TextView = itemView.findViewById(R.id.text_view_name)
        val emailView: TextView = itemView.findViewById(R.id.text_view_email)
        val phoneView: TextView = itemView.findViewById(R.id.text_view_phone)
    }
}
