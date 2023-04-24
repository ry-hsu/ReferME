package bu.rhsu.referme.datalayer

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import bu.rhsu.referme.firebase.FirebaseLogin

class LoginRepository (val dataSource: FirebaseLogin) {
    var loggedInUser: MutableLiveData<LoggedInUser> = dataSource.loggedInUser

    fun logout() {
        dataSource.logout()
    }

    fun login(username: String, password: String) {
        // handle login
        dataSource.login(username, password)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun signUp(username: String, password: String) {
        // handle login
        Log.d("signup","made it!!")
        dataSource.signUp(username, password, username.split("@")[0])
    }
}