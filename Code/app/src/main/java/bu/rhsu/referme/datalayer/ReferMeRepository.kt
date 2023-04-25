package bu.rhsu.referme.datalayer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import bu.rhsu.referme.firebase.FirebaseStorage
import java.util.concurrent.Executors

import bu.rhsu.referme.datalayer.Provider
import bu.rhsu.referme.datalayer.Review

class ReferMeRepository(private val firebaseStorage: FirebaseStorage) {
    // Functions to get provider from repository
     fun addProvider(provider: Provider){
        firebaseStorage.addProvider(provider)
    }

    fun delProject(provider: Provider) {
        firebaseStorage.delProvider(provider)
    }


    fun getAllProviders(): LiveData<List<Provider>> {
        firebaseStorage.loadAllProviders()
        return firebaseStorage.providerLiveData
    }

    fun searchProvider(name: String): MutableLiveData<List<Provider>> {
        firebaseStorage.getProviderByName(name)
        return firebaseStorage.providerLiveData
    }
    fun searchProviderAll(name:String, spec: String, zip: Int): MutableLiveData<List<Provider>> {
        firebaseStorage.getProviderByName(name,spec,zip)
        Log.d("getProvider",firebaseStorage.providerLiveData.value.toString())
        return firebaseStorage.providerLiveData
    }

   fun count(): LiveData<Int>{
        return firebaseStorage.count
    }

    // Functions to get reviews for a provider from repository
    fun addReview(review: Review){
        firebaseStorage.addReview(review)
    }

    fun delReview(review: Review) {
        firebaseStorage.delReview(review)
    }

    fun getReviewsForProvider(provider: Provider): MutableLiveData<List<Review>> {
        firebaseStorage.loadReviewsForProvider(provider.docId)
        Log.d("getReviews",firebaseStorage.reviewLiveData.value.toString())
        Log.d("getReviews",provider.docId)
        return firebaseStorage.reviewLiveData
    }

    fun getAllReviews(): MutableLiveData<List<Review>> {
        firebaseStorage.loadAllReviews()
        Log.d("getReviews",firebaseStorage.reviewLiveData.value.toString())
        return firebaseStorage.reviewLiveData
    }
}

// FOLLOWING CODE IS FOR USE WITH LOCAL DATABASE
/*

class ReferMeRepository(private val providerDao: ProviderDao,
    private val reviewDao: ReviewDao) {

    //Functions for providerDao
    suspend fun addProvider(provider: Provider) {
        providerDao.addProvider(provider)
    }

    suspend fun delProvider(provider: Provider) {
        providerDao.delProvider(provider)
    }

    suspend fun editProvider(provider: Provider) {
        providerDao.editProvider(provider)
    }

    fun providerCount(): LiveData<Int> {
        return providerDao.count()
    }

    fun getAllProviders(): LiveData<List<Provider>> {
        return providerDao.getAllProviders()
    }

    fun getProviderByName(name: String): Provider {
        return getProviderByName(name)
    }

    fun getProviderBySpecialty(specialty: String): Provider {
        return getProviderBySpecialty(specialty)
    }

    //Functions for ReviewDao
    suspend fun addReview(review: Review) {
        reviewDao.addReview(review)
    }

    suspend fun delReview(review: Review) {
        reviewDao.delReview(review)
    }

    suspend fun editReview(review: Review) {
        reviewDao.editReview(review)
    }

    fun reviewCount(): LiveData<Int> {
        return reviewDao.count()
    }

    fun getReviewForProvider(provId: Int): LiveData<List<Review>> {
        return reviewDao.getReviewForProvider(provId)
    }

    fun getReviewForProviderDate(provId:Int): LiveData<List<Review>> {
        return reviewDao.getReviewForProviderDate(provId)
    }
}*/
