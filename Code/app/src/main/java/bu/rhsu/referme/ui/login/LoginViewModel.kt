package bu.rhsu.referme.ui.login

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import bu.rhsu.referme.datalayer.LoginRepository
import bu.rhsu.referme.data.Result
import androidx.lifecycle.AndroidViewModel
import androidx.annotation.RequiresApi
import android.app.Application
import android.util.Log
import android.view.View
import bu.rhsu.referme.datalayer.LoggedInUser


import bu.rhsu.referme.R
import bu.rhsu.referme.ReferMeApplication

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    //class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val loginRepository: LoginRepository =
        (application as ReferMeApplication).loginRepository

    val loggedInUser: LiveData<LoggedInUser>
        get() {
            return loginRepository.loggedInUser
        }

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        loginRepository.login(username, password)
        Log.d("login",loggedInUser.value.toString())
        if(loggedInUser.value == null) {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
        else {
            _loginResult.value = LoginResult(success = LoggedInUserView(username))
        }
    }

    fun logout() {
        loginRepository.logout()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun signup(username: String, password: String) {
        loginRepository.signUp(username,password)
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}