package bu.rhsu.referme.firebase

import android.app.Application
import android.content.Context
import android.os.Build
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast

import bu.rhsu.referme.ui.login.LoginActivity

import com.google.firebase.auth.FirebaseUser

import com.google.firebase.auth.AuthResult

import com.google.android.gms.tasks.OnCompleteListener

import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import bu.rhsu.referme.datalayer.LoggedInUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore


class FirebaseLogin (application: Application){
    private var application = application
    private  var mAuth: FirebaseAuth
    var loggedInUser: MutableLiveData<LoggedInUser>
    var running: Boolean = false

    init {
        FirebaseApp.initializeApp (application.applicationContext);
        mAuth = FirebaseAuth.getInstance();
        loggedInUser = MutableLiveData()
        mAuth.currentUser?.let{
            loggedInUser.setValue(
                LoggedInUser(it.uid,
                    it.email?.split("@")?.get(0)?: "",
                    it.email ?: ""))
        }
    }


    fun login(email: String, passwd: String?){
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(passwd)) {
            running = true
            mAuth.signInWithEmailAndPassword(email, passwd!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("signInExisitingUser", "Signed in exisiting user with email: $email")
                    mAuth.currentUser?.let{
                        loggedInUser.setValue(
                        LoggedInUser(it.uid,
                        it.email?.split("@")?.get(0)?: "",
                            it.email ?: ""))
                    }
                    running = false
                } else {
                    loggedInUser.value = null
                    var errorMsg = task.exception?.message?:""
                    Log.d("FirebaseLogin failed: ", errorMsg)
                    Toast.makeText(application.applicationContext,
                        "firebase login failed: $errorMsg", Toast.LENGTH_LONG).show()
                    running = false

                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun signUp(email: String, password: String?, displayName: String?) {
        mAuth.createUserWithEmailAndPassword(email, password!!).addOnCompleteListener(
            ContextCompat.getMainExecutor(application),
            OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    //Sign in was a success, create intent to go to the LinkRideShareAccountsActivity
                    Log.d("createUserAccount", "Created user with email: $email")
                    mAuth.currentUser?.let{user->
                        updateUserProfile(user, displayName)
                        addUserInfotoFirebase(displayName, email)
                        loggedInUser.setValue(
                            LoggedInUser(user.uid, user.email?.split("@")?.get(0)?: "",
                                user.email ?: ""))}

                } else {
                    var errorMsg = task.exception?.message?:""
                    Log.d("FirebaseSignup failed: ", errorMsg)
                    Toast.makeText(application.applicationContext,
                        "firebase signup failed: " + errorMsg, Toast.LENGTH_LONG).show()

                }
            })
    }

    fun logout(){
        mAuth.signOut()
        loggedInUser.value = null
    }

    fun addUserInfotoFirebase(userName: String?, email: String?) {
        val curUid = mAuth.uid
        val userData: MutableMap<String, String?> = HashMap()
        userData["name"] = userName
        userData["email"] = email
        FirebaseFirestore.getInstance().document("users/$curUid").set(userData)
    }


    fun updateUserProfile(user: FirebaseUser, displayName: String?) {
        //Update the user's profile with their display name entered in the form
        val userProfileChangeRequest =
            UserProfileChangeRequest.Builder().setDisplayName(displayName).build()
        user.updateProfile(userProfileChangeRequest)
    }

    fun getStatus(): Boolean {
        return running
    }

}