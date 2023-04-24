package bu.rhsu.referme.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bu.rhsu.referme.data.LoginDataSource
import bu.rhsu.referme.datalayer.LoginRepository
import bu.rhsu.referme.firebase.FirebaseLogin
import android.app.Application
import bu.rhsu.referme.ReferMeApplication


/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(application: Application) : ViewModelProvider.Factory {

    val referMeRepository =
        (application as ReferMeApplication).referMeRepository

/*    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = FirebaseLogin(application)
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }*/
}