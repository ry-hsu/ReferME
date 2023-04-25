package bu.rhsu.referme.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import bu.rhsu.referme.datalayer.Provider
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

    val referMeRepository =
        (application as ReferMeApplication).referMeRepository

    fun initCurReview(review: Review){
        if (_curReview.value == null)
            _curReview.value = review
    }

    fun addReview(review: Review) {
        referMeRepository.addReview(review)
    }

    fun setCurProvider(provider: Review){
        _curReview.value = provider
    }

    fun isCurReview(provider: Review):Boolean{
        return _curReview.value?.providerID == provider.providerID
    }

}