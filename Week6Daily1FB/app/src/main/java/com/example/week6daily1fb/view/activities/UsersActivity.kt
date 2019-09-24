package com.example.week6daily1fb.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week6daily1fb.R
import com.example.week6daily1fb.model.userprofile.UserProfile
import com.example.week6daily1fb.view.adapters.UserProfileAdapter
import com.example.week6daily1fb.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {
    lateinit var viewModel : UsersViewModel
    var adapter = UserProfileAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        rvUsersList.layoutManager = LinearLayoutManager(this)
        rvUsersList.adapter = adapter

        viewModel = UsersViewModel()
        viewModel.users.observe(this, object : Observer<ArrayList<UserProfile>>{
            override fun onChanged(t: ArrayList<UserProfile>?) {
                adapter.onUserListUpdate(t!!)
            }

        })
    }
}
