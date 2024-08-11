package com.colleze.android.Controllers

import android.util.Log
import com.colleze.android.Data.StudentAuth
import com.google.firebase.Firebase
import com.google.firebase.database.database

object CZFireBaseController {
    val fbDataBase = Firebase.database("https://colleze-d0de5-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val studentReference = fbDataBase.reference.child("StudentAuth")

    fun persistStudentAuthenticationDetails(studentAuth: StudentAuth) {
        //get the refId
        val refId = studentAuth.studentId;
        studentReference.child(refId).setValue(studentAuth.studentEmail).addOnCompleteListener {
            if (!it.isSuccessful) {
                Log.e("CZ", "Cannot Insert into DataBase")
            }
            println("Inserted SuccessFully");
        }
    }

    fun retrieveAuthenticationDetailsFromStudentId(studentId: String, callback: (Any?) -> Unit) {
        studentReference.child(studentId).get().addOnSuccessListener {
            callback(it.value)
        }.addOnFailureListener {
            callback(null) // or handle the error accordingly
        }
    }
}