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

class ThreadsAdapter(private val context: Context?, val threads: ArrayList<messagesItem>, val listener:ThreadClicked) :
    RecyclerView.Adapter<ThreadsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.tvThreadId)
        val time: TextView = itemView.findViewById(R.id.tvThreadDate)
        val body: TextView = itemView.findViewById(R.id.tvThreadBody)
        val senderName: TextView = itemView.findViewById(R.id.tvThreadSender)
        val reply: Button = itemView.findViewById(R.id.btnReply)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.thread_singleitem_view, parent, false))
        viewholder.reply.setOnClickListener {
            listener.onThreadClicked(threads[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thread = threads[position]
        holder.id.text = "ID: "+thread.id.toString()
        holder.time.text = thread.timestamp
        holder.body.text = "Msg: "+thread.body
        holder.senderName.text = "Sender Id: "+thread.user_id
    }

    override fun getItemCount(): Int {
        return threads.size
    }
    interface ThreadClicked {
        fun onThreadClicked(thread: messagesItem){

        }
    }
}