package bu.rhsu.referme.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import bu.rhsu.referme.databinding.ActivityLoginBinding
import androidx.annotation.RequiresApi
import android.os.Build
import android.util.Log
import android.widget.Button
import bu.rhsu.referme.MainActivity
import bu.rhsu.referme.datalayer.LoggedInUser

import bu.rhsu.referme.R

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val register = binding.registerButton
        val loading = binding.loading

/*        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)*/
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })


        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                Log.d("loginresult",loginResult.error.toString())
                //showLoginFailed(loginResult.error)
                binding.registerButton?.visibility = View.VISIBLE
                binding.login.text = "Sign In"
            }
            if (loginResult.success != null) {
                //Complete and destroy login activity once successful
                finish()
            }
            //setResult(Activity.RESULT_OK)
        })

        loginViewModel.loggedInUser.observe(this, Observer { it ->
                it?.let {
                    updateUiWithUser(it)
                    finish()
                }
            //Complete and destroy login activity once successful
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }


            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }

            register?.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.signup(username.text.toString(), password.text.toString())
            }

        }
    }
    //
    //private fun updateUiWithUser(model: LoggedInUserView) {
    private fun updateUiWithUser(loggedInUser: LoggedInUser) {
        val welcome = getString(R.string.welcome)
        //val displayName = model.userName
        val displayName = loggedInUser.displayName

        val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)

        startActivity(mainIntent)

        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}