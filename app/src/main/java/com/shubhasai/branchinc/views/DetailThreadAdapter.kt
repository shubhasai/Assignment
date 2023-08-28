package com.shubhasai.branchinc.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.shubhasai.branchinc.R
import com.shubhasai.branchinc.data.messagesItem
import com.shubhasai.branchinc.utils.TimeFormater

class DetailThreadAdapter(private val context: Context?, val threads: ArrayList<messagesItem>) :
    RecyclerView.Adapter<DetailThreadAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val threadId: TextView = itemView.findViewById(R.id.tvThreadId)
        val id: TextView = itemView.findViewById(R.id.tvId)
        val time: TextView = itemView.findViewById(R.id.tvThreadDate)
        val body: TextView = itemView.findViewById(R.id.tvThreadBody)
        val senderName: TextView = itemView.findViewById(R.id.tvThreadSender)
        val reply: Button = itemView.findViewById(R.id.btnReply)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.thread_singleitem_view, parent, false))
        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thread = threads[position]
        holder.reply.visibility = View.GONE
        holder.threadId.visibility = View.GONE
        holder.senderName.visibility = View.GONE
        holder.id.text = "Message Id: "+thread.id.toString()
        holder.threadId.text = "Thread ID: "+thread.thread_id.toString()
        holder.time.text = "Created At: "+TimeFormater.convertToSocialFormat(thread.timestamp)
        holder.body.text = "Message: "+thread.body
        holder.senderName.text = "Client Id: "+thread.user_id
    }

    override fun getItemCount(): Int {
        return threads.size
    }
}