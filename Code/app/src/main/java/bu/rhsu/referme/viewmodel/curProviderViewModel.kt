package bu.rhsu.referme.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import bu.rhsu.referme.datalayer.Provider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import bu.rhsu.referme.ReferMeApplication
import java.util.concurrent.Executors

class curProviderViewModel(application: Application): AndroidViewModel(application) {

    private val _curProvider: MutableLiveData<Provider> = MutableLiveData()
    val curprovider: LiveData<Provider>
        get() = _curProvider

    val projectPortalRepository =
        (application as ReferMeApplication).referMeRepository


    fun initCurProvider(provider: Provider){
        if (_curProvider.value == null)
            _curProvider.value = provider
    }

    fun setCurprovider(provider: Provider){
        _curProvider.value = provider
    }

    fun isCurProvider(provider: Provider):Boolean{
        return _curProvider.value?.provId == provider.provId
    }

/*    fun updateCurprovider(name: String, specialty: String?, phone: String?,
                         email: String?){
        _curProvider.value = _curProvider.value?.apply{
            this.name = name
            this.specialty = specialty
            this.phone = phone
            this.email = email
        }
        viewModelScope.launch(Dispatchers.IO) {
            projectPortalRepository.editProvider(_curProvider.value!!)
        }
    }*/
}