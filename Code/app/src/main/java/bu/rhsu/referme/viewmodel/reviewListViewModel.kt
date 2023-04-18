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

class reviewListViewModel(application: Application): AndroidViewModel(application)   {

    private val _curReview: MutableLiveData<Review> = MutableLiveData()
    val curReview: MutableLiveData<Review>
        get() = _curReview

    val referMeRepository =
        (application as ReferMeApplication).referMeRepository

    private var _reviewList: MutableLiveData<List<Review>> = MutableLiveData()

    fun initReviews(provider: Provider){
        if (_reviewList.value == null)
            _reviewList = referMeRepository.getReviewForProvider(provider.provId)
    }

    fun getReviews(provider: Provider) {
        referMeRepository.getReviewForProvider(provider.provId)
    }

    fun addReview(review: Review) {
        viewModelScope.launch(Dispatchers.IO) {
            referMeRepository.addReview(review)
        }
    }

    fun delReview(review: Review) {
        viewModelScope.launch(Dispatchers.IO) {
            referMeRepository.delReview(review)
        }
    }

}