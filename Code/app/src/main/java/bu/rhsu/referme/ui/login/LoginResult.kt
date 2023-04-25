package bu.rhsu.referme.ui.login

import androidx.lifecycle.LiveData
import bu.rhsu.referme.datalayer.LoggedInUser

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LiveData<LoggedInUser>? = null,
    val error: Int? = null
)