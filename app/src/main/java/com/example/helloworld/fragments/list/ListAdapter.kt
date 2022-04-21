package com.example.helloworld.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R
import com.example.helloworld.data.models.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var user_ist = emptyList<User>()

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current_item: User = user_ist[position]
        holder.itemView.findViewById<TextView>(R.id.text_id).text = current_item.id.toString()
        holder.itemView.findViewById<TextView>(R.id.text_first_name).text = current_item.firstName.toString()
        holder.itemView.findViewById<TextView>(R.id.text_last_name).text = current_item.lastName.toString()
        holder.itemView.findViewById<TextView>(R.id.text_age).text = current_item.age.toString()
    }

    override fun getItemCount(): Int {
        return user_ist.size
    }

    fun setData(users: List<User>){
        this.user_ist = users
        notifyDataSetChanged()
    }
}