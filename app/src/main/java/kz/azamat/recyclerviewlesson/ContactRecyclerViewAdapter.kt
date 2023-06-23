package kz.azamat.recyclerviewlesson

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.azamat.recyclerviewlesson.models.ContactModel

class ContactRecyclerViewAdapter : RecyclerView.Adapter<ContactRecyclerViewAdapter.ItemViewHolder>() {

//    private var listener: ContactClickListener ?= null
    private val dataList = mutableListOf<ContactModel>()

    private var clickListener: ((ContactModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.bind(dataList[position])

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

    fun onClickListener(clickListener: ((ContactModel) -> Unit)){
        this.clickListener = clickListener
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

}

interface ContactClickListener {
    fun clickedItem(contact: ContactModel)
}