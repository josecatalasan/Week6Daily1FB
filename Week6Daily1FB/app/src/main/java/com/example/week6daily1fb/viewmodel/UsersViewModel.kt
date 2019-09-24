package com.example.week6daily1fb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week6daily1fb.model.userprofile.UserProfile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UsersViewModel : ViewModel(){
    private val database = FirebaseDatabase.getInstance()
    private val reference = database.reference

    var users : MutableLiveData<ArrayList<UserProfile>> = MutableLiveData()

    init{
        reference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var temp = users.value
                if(temp == null){
                    temp = ArrayList()
                }

                val iter = dataSnapshot.children.iterator()
                while(iter.hasNext()) {
                    val shot = iter.next()
                    val message = shot.getValue(UserProfile::class.java) //problem here
                    temp.add(message!!)
                }
                users.postValue(temp)
            }

        })
    }
}
