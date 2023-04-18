package bu.rhsu.referme.datalayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import java.util.concurrent.Executors

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

    fun getAllProviders(): MutableLiveData<List<Provider>> {
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

    fun getReviewForProvider(provId: Int): MutableLiveData<List<Review>> {
        return reviewDao.getReviewForProvider(provId)
    }

    fun getReviewForProviderDate(provId:String) {
        return reviewDao.getReviewForProviderDate(provId)
    }
}