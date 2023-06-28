package kz.azamat.recyclerviewlesson.models

const val PHONE_NUMBER_TYPE = 1;
const val WEB_TYPE = 2

data class ContactModel(val name: String, val type: Int, val phoneNumber: String? = null, val email: String? = null, val webSite: String? = null)
