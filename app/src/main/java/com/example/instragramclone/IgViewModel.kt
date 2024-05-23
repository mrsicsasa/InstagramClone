package com.example.instragramclone

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.instragramclone.auth.data.Event
import com.example.instragramclone.auth.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

const val USERS = "users"

@HiltViewModel
class IgViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val db: FirebaseFirestore,
    val storage: FirebaseStorage
) : ViewModel() {
    val signedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    val popupNotification= mutableStateOf<Event<String>?>(null)
    fun onSignUp(username: String, email: String, password: String) {
        println("radi zadata u dugmetu")
        inProgress.value = true
        db.collection(USERS).whereEqualTo("username", username).get()
            .addOnSuccessListener { documents ->
                if (documents.size() > 0) {
                    println("radi zadata nekako zavrsilo vamo")
                    handleException(customMessage = "Username already exists")
                    inProgress.value = false
                } else {
                    println("radi zadata proslo da nije isto")
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                println("radi zadata ne bi trebalo biti vamo")
                                signedIn.value = true
                                //Create profile
                            } else {
                                handleException(task.exception, "SignUp failed")
                            }
                            inProgress.value = false
                        }
                }

            }
            .addOnFailureListener { println("radi zadata ne")}
    }

    fun handleException(exception: Exception? = null, customMessage: String = "") {
        println("radi zadata u gresci")
        exception?.printStackTrace()
        val errorMsg=exception?.localizedMessage?:""
        val message=if (customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"
        popupNotification.value=Event(message)
    }
}