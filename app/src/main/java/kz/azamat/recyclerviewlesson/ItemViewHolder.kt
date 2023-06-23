package kz.azamat.recyclerviewlesson

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kz.azamat.recyclerviewlesson.models.ContactModel

//class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
//
//    fun bind(contact: ContactModel, listener: ContactClickListener?) {
//        val nameTv = itemView.findViewById<TextView>(R.id.nameTextView)
//        val numberTv = itemView.findViewById<TextView>(R.id.numberTextView)
//
//        nameTv.text = contact.name
//        numberTv.text = contact.phoneNumber
//
//        itemView.setOnClickListener {
//            listener?.let {
//                it.clickedItem(contact)
//            }
//        }
//
//    }
//
//}