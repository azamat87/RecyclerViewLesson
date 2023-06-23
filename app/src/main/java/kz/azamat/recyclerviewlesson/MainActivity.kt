package kz.azamat.recyclerviewlesson

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.azamat.recyclerviewlesson.models.ContactModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ContactRecyclerViewAdapter()

        recyclerView = findViewById(R.id.mainRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.setItems(getItems())
//        adapter.setClickListener(object : ContactClickListener {
//            override fun clickedItem(contact: ContactModel) {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, ContactInfoFragment.newInstance(contact.name, contact.phoneNumber))
//                    .addToBackStack(null)
//                    .commit()
//            }
//        })

        adapter.onClickListener { contact ->
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    ContactInfoFragment.newInstance(
                        contact.name,
                        contact.phoneNumber
                    )
                )
                .addToBackStack(null)
                .commit()
        }

    }

    fun getItems(): MutableList<ContactModel> {
        val list = mutableListOf<ContactModel>()
        for (i in 0..100) {
            list.add(ContactModel("Azamat $i", "+7701 555 00 11"))
        }
        return list
    }
}