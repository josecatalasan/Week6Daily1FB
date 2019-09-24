package com.example.week6daily1fb.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.CallbackManager
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.week6daily1fb.R
import com.example.week6daily1fb.model.userprofile.UserProfile
import com.facebook.AccessToken
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var callbackManager : CallbackManager
    private lateinit var auth : FirebaseAuth
    lateinit var database : FirebaseDatabase
    lateinit var reference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create()

        database = FirebaseDatabase.getInstance()
        reference = database.reference

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    // App code
                }
            })

        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email", "public_profile"))
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //val currentUser = auth.currentUser
        //updateUI(currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("TAG_HANDLE", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG_HANDLE", "signInWithCredential:success")
                    val user = auth.currentUser

                    val profile = UserProfile(user?.displayName, user?.email/*, user?.phoneNumber, user?.uid!!, user?.photoUrl*/)

                    reference.child(user!!.uid).setValue(profile)

                    val intent = Intent(applicationContext, UsersActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w("TAG_HANDLE", "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}
