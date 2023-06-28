package kz.azamat.recyclerviewlesson

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.azamat.recyclerviewlesson.models.ContactModel
import kz.azamat.recyclerviewlesson.models.PHONE_NUMBER_TYPE
import kz.azamat.recyclerviewlesson.models.WEB_TYPE

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var count = 0
    private val list = mutableListOf<ContactModel>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ContactRecyclerViewAdapter()

        recyclerView = findViewById(R.id.mainRecyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val scrollListener = PageScrollListener(layoutManager)
        recyclerView.addOnScrollListener(scrollListener)
        scrollListener.loadMore {
            adapter.setItems(getItems())
        }

//        recyclerView.addItemDecoration(CustomItemDecoration())

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            ).apply {
                setDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.bottom_line_for_list
                    )!!
                )
            })

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
//            supportFragmentManager.beginTransaction()
//                .replace(
//                    R.id.container,
//                    ContactInfoFragment.newInstance(
//                        contact.name,
//                        contact.phoneNumber
//                    )
//                )
//                .addToBackStack(null)
//                .commit()
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.START)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
//                Toast.makeText(this@MainActivity, "onSwiped ", Toast.LENGTH_SHORT).show()
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    fun getItems(): MutableList<ContactModel> {
        for (i in count..count + 10) {
            list.add(ContactModel("Azamat $i", type = PHONE_NUMBER_TYPE, "+7701 555 00 11"))
        }
        for (i in count..count + 10) {
            list.add(
                ContactModel(
                    "Azamat $i",
                    type = WEB_TYPE,
                    email = "azamat87@mail.ru",
                    webSite = "www.azamat.kz"
                )
            )
        }
        return list
    }
}