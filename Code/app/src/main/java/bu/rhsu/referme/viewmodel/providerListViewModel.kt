package bu.rhsu.referme.viewmodel

import android.app.Application
import androidx.lifecycle.*
import bu.rhsu.referme.datalayer.Review
import bu.rhsu.referme.datalayer.Provider
import bu.rhsu.referme.ReferMeApplication
import bu.rhsu.referme.datalayer.ReviewDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class providerListViewModel(application: Application): AndroidViewModel(application)  {
    private val _curProviders: MutableLiveData<Review> = MutableLiveData()

    val referMeRepository =
        (application as ReferMeApplication).referMeRepository

    fun getProviderByName(name: String) {
        referMeRepository.getProviderByName(name)
    }

    fun addProvider(provider: Provider) {
        viewModelScope.launch(Dispatchers.IO) {
            referMeRepository.addProvider(provider)
        }
    }

    fun delProvider(provider: Provider) {
        viewModelScope.launch(Dispatchers.IO) {
            referMeRepository.delProvider(provider)
        }
    }
}