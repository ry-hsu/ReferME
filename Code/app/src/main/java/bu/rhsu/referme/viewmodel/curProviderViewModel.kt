package bu.rhsu.referme.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import bu.rhsu.referme.datalayer.Provider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import bu.rhsu.referme.ReferMeApplication
import java.util.concurrent.Executors
import bu.rhsu.referme.datalayer.Review

class curProviderViewModel(application: Application): AndroidViewModel(application) {

    private val _curProvider: MutableLiveData<Provider> = MutableLiveData()
    val curprovider: LiveData<Provider>
        get() = _curProvider


    private var _providerReviewsList: MutableLiveData<List<Review>> = MutableLiveData()

    val providerReviewList: MutableLiveData<List<Review>>
        get() = _providerReviewsList

    private val referMeRepository =
        (application as ReferMeApplication).referMeRepository

    fun setReviewsList(list: MutableLiveData<List<Review>>) {
        _providerReviewsList = list
    }

    // Average Ratings
    private var avgBedReviews: Float? = 0f
    private var avgExpertReviews: Float? = 0f
    private var avgOfficeReviews: Float? = 0f
    private var avgFacilityReviews: Float? = 0f

    var reviewCount: Int? = 0

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

    fun getReviews(provider: Provider): MutableLiveData<List<Review>> {
        _providerReviewsList = referMeRepository.getReviewsForProvider(provider)
        calculateRatings(_providerReviewsList)
        return _providerReviewsList
    }

    private fun getAllReviews(): MutableLiveData<List<Review>> {
        var list = referMeRepository.getAllReviews()
        calculateRatings(list)
        return list
    }

    fun calculateRatings(reviewList: MutableLiveData<List<Review>>) {
        reviewCount = _providerReviewsList.value?.size
        var iterator = _providerReviewsList.value?.listIterator()

        var bedReviews: Int = 0
        var expertReviews: Int = 0
        var officeReviews: Int = 0
        var facilityReviews: Int = 0

        _providerReviewsList?.value?.forEach {
            bedReviews += it.bedManner.toInt()
            expertReviews += it.expertise.toInt()
            officeReviews += it.frontOffice.toInt()
            facilityReviews += it.facility.toInt()
        }

        if(reviewCount != null) {
            avgBedReviews = (bedReviews/20f) / reviewCount!!
            avgExpertReviews = (expertReviews/20f) / reviewCount!!
            avgOfficeReviews = (officeReviews/20f) / reviewCount!!
            avgFacilityReviews = (facilityReviews/20f) / reviewCount!!
        }

        Log.d("reviews",bedReviews.toString() + " " + expertReviews.toString())
    }

    fun getAvgReviews(): List<Float?> {
        return listOf(avgBedReviews,avgExpertReviews,avgOfficeReviews,avgFacilityReviews)
    }

}