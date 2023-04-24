package bu.rhsu.referme.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import bu.rhsu.referme.datalayer.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import bu.rhsu.referme.ReferMeApplication
import java.util.*
import java.util.concurrent.Executors

class curReviewViewModel(application: Application): AndroidViewModel(application) {

    private val _curReview: MutableLiveData<Review> = MutableLiveData()
    val curReview: LiveData<Review>
        get() = _curReview

    val projectPortalRepository =
        (application as ReferMeApplication).referMeRepository


    fun initCurProvider(provider: Review){
        if (_curReview.value == null)
            _curReview.value = provider
    }

    fun setCurProvider(provider: Review){
        _curReview.value = provider
    }

    fun isCurReview(provider: Review):Boolean{
        return _curReview.value?.providerID == provider.providerID
    }

/*    fun updateCurprovider(date: String, bedManner: Float, expertise: Float,
                          frontOffice: Float, facility: Float, text: String?){
        _curReview.value = _curReview.value?.apply{
            this.date = date
            this.bedManner = bedManner
            this.expertise = expertise
            this.frontOffice = frontOffice
            this.facility = facility
            this.text = text
        }
        viewModelScope.launch(Dispatchers.IO) {
            projectPortalRepository.editReview(_curReview.value!!)
        }
    }*/

}