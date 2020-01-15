package com.example.androidretrofit.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidretrofit.listeners.MyUserListener

class UserAdapter(private val clickListener: MyUserListener) : RecyclerView.Adapter<UserAdapter.MyUserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUserViewHolder {

    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: MyUserViewHolder, position: Int) {
        
    }

    inner class MyUserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}