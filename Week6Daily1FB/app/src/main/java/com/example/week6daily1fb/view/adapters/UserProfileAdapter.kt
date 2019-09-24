package com.example.week6daily1fb.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week6daily1fb.R
import com.example.week6daily1fb.model.userprofile.UserProfile
import kotlinx.android.synthetic.main.item_user_profile.view.*

class UserProfileAdapter(var userList : ArrayList<UserProfile>) : RecyclerView.Adapter<UserProfileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user_profile, parent, false))

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(userList[position])

    fun onUserListUpdate(newList : ArrayList<UserProfile>){
        userList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(userProfile: UserProfile){
            itemView.tvName.text = userProfile.name
            itemView.tvEmail.text = userProfile.email
        }
    }
}