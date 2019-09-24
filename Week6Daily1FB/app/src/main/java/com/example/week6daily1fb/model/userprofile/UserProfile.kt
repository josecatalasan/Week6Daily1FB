package com.example.week6daily1fb.model.userprofile

import android.net.Uri
import com.google.gson.annotations.SerializedName

class UserProfile(@SerializedName("name")var name : String? = "",@SerializedName("email") var email : String? = ""/*, var phoneNumber : String?, var uid : String, image : Uri? */) {
    //display name, email, phone number, uid, imageUrl
}