import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubhasai.branchinc.data.authresponse
import com.shubhasai.branchinc.networks.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import org.json.JSONObject
import retrofit2.HttpException

class AuthViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    fun authenticateUser(email: String, password: String, authCallback: AuthCallback) {
        if (email.isEmpty()) {
            authCallback.onAuthError("Please enter email")
            return
        }
        if (password.isEmpty()) {
            authCallback.onAuthError("Please enter password")
            return
        }

        viewModelScope.launch {
            try {
                val authResponse = withContext(Dispatchers.IO) {
                    authRepository.getAuth(email, password)
                }
                // Handle authResponse based on your requirements
                authCallback.onAuthSuccess(authResponse)
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is IOException -> "Network error occurred"
                    is HttpException -> {
                        val errorResponse = e.response()?.errorBody()?.string()
                        val errorJson = JSONObject(errorResponse)
                        errorJson.getString("error")
                    }
                    else -> "Unknown error occurred"
                }
                authCallback.onAuthError("Authentication failed with error: $errorMessage")
            }
        }
    }
}

interface AuthCallback {
    fun onAuthSuccess(response: authresponse)
    fun onAuthError(errorMessage: String)
}
