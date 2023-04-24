package bu.rhsu.referme.viewmodel

import android.os.Build
import android.util.Patterns
import bu.rhsu.referme.datalayer.LoginRepository
import bu.rhsu.referme.data.Result
import androidx.annotation.RequiresApi
import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import bu.rhsu.referme.datalayer.LoggedInUser

import bu.rhsu.referme.datalayer.Provider


import bu.rhsu.referme.R
import bu.rhsu.referme.ReferMeApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class simpleSearchViewModel(application: Application) : AndroidViewModel(application) {

    val referMeRepository =
        (application as ReferMeApplication).referMeRepository

    private val _curProvider: MutableLiveData<Provider> = MutableLiveData()
    val curprovider: LiveData<Provider>
        get() = _curProvider

    private var _providerResultList: MutableLiveData<List<Provider>> = getProvider("Duck Goose")
    val providerResultList: MutableLiveData<List<Provider>>
        get() = _providerResultList

    fun setProviderList(list: MutableLiveData<List<Provider>>) {
        _providerResultList = list
    }

    fun setCurProvider(provider: Provider){
        _curProvider.value = provider
    }

    fun getProvider(name: String): MutableLiveData<List<Provider>> {
        //viewModelScope.launch(Dispatchers.IO) {
        return referMeRepository.searchProvider(name)
        //}
    }
}
