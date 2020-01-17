package com.example.androidretrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidretrofit.R
import com.example.androidretrofit.listeners.UserClickListener
import com.example.androidretrofit.models.UserDataResponse
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter() : RecyclerView.Adapter<UserAdapter.MyUserViewHolder>() {

    private val myUsers = ArrayList<UserDataResponse>()
    private lateinit var userItemClickCallback: UserClickListener

    // set data
    fun setMyUserList(listItem: ArrayList<UserDataResponse>) {
        myUsers.clear()
        myUsers.addAll(listItem)
        notifyDataSetChanged()
    }

    // item click callback
    fun setOnItemClickCallback(onItemClickCallback: UserClickListener){
        this.userItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)

        return MyUserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myUsers.size
    }

    override fun onBindViewHolder(holder: MyUserViewHolder, position: Int) {
        // call bind view function
        holder.bind(myUsers[position])

        // set item click callback
        holder.itemView.setOnClickListener {
            userItemClickCallback.onClick(myUsers[holder.adapterPosition])
        }
    }

    inner class MyUserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(myUser: UserDataResponse) {
            itemView.tvMyUserName.text = myUser.name
            itemView.tvMyUserAdress.text = myUser.address
            itemView.tvMyUserMobile.text = myUser.phone
        }
    }
}