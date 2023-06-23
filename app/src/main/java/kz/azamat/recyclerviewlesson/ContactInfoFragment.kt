package kz.azamat.recyclerviewlesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView


class ContactInfoFragment : Fragment(R.layout.fragment_contact_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            view.findViewById<TextView>(R.id.nameTV).text = it.getString(CONTACT_NAME, "")
            view.findViewById<TextView>(R.id.numberTV).text = it.getString(CONTACT_NUMBER, "")
        }

    }

    companion object {

        const val CONTACT_NAME = "CONTACT_NAME"
        const val CONTACT_NUMBER = "CONTACT_NUMBER"

        @JvmStatic
        fun newInstance(nameArg: String, numberArg: String) =
            ContactInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(CONTACT_NAME, nameArg)
                    putString(CONTACT_NUMBER, numberArg)
                }
            }
    }
}