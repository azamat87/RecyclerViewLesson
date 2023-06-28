package kz.azamat.recyclerviewlesson

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kz.azamat.recyclerviewlesson.models.ContactModel
import kz.azamat.recyclerviewlesson.models.PHONE_NUMBER_TYPE
import kz.azamat.recyclerviewlesson.models.WEB_TYPE

class ContactRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {

    //    private var listener: ContactClickListener ?= null
    private val dataList = mutableListOf<ContactModel>()

    private var clickListener: ((ContactModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            PHONE_NUMBER_TYPE -> {
                ItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
                )
            }
            WEB_TYPE -> {
                WebItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.web_item_view, parent, false)
                )
            }
            else -> {
                ItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is WebItemViewHolder) {
            (holder as WebItemViewHolder).bind(dataList[position])
        } else {
            (holder as ItemViewHolder).bind(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(data: List<ContactModel>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

//    override fun getItemViewType(position: Int): Int {
//        return if (dataList[position].type == WEB_TYPE) {
//            R.layout.web_item_view
//        } else {
//            R.layout.item_view
//        }
//    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }

    fun onClickListener(clickListener: ((ContactModel) -> Unit)) {
        this.clickListener = clickListener
    }

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

/*    fun setClickListener(listener: ContactClickListener) {
        this.listener = listener
    }*/


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contact: ContactModel) {
            val nameTv = itemView.findViewById<TextView>(R.id.nameTextView)
            val numberTv = itemView.findViewById<TextView>(R.id.numberTextView)

            nameTv.text = contact.name
            numberTv.text = contact.phoneNumber

//            itemView.setOnClickListener {
//                listener?.let {
//                    it.clickedItem(contact)
//                }
//            }

            itemView.setOnClickListener {
                clickListener?.invoke(contact)
            }
        }
    }

    inner class WebItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contact: ContactModel) {
            val nameTv = itemView.findViewById<TextView>(R.id.nameTextView)
            val emailTv = itemView.findViewById<TextView>(R.id.emailTextView)
            val webTv = itemView.findViewById<TextView>(R.id.webTextView)

            nameTv.text = contact.name
            emailTv.text = contact.email
            webTv.text = contact.webSite

//            itemView.setOnClickListener {
//                listener?.let {
//                    it.clickedItem(contact)
//                }
//            }

            itemView.setOnClickListener {
                clickListener?.invoke(contact)
            }
        }
    }

}

interface ContactClickListener {
    fun clickedItem(contact: ContactModel)
}