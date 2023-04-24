package bu.rhsu.referme.datalayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import bu.rhsu.referme.firebase.FirebaseStorage
import java.util.concurrent.Executors

import bu.rhsu.referme.datalayer.Provider
import bu.rhsu.referme.datalayer.Review

class ReferMeRepository(private val firebaseStorage: FirebaseStorage) {
     fun addProvider(provider: Provider){
        firebaseStorage.addProvider(provider)
    }

    fun delProject(provider: Provider) {
        firebaseStorage.delProvider(provider)
    }


    fun getAllProjects(): LiveData<List<Provider>> {
        firebaseStorage.loadAllProviders()
        return firebaseStorage.providerLiveData
    }

    fun searchProvider(name: String): MutableLiveData<List<Provider>> {
        firebaseStorage.getProviderByName(name)
        return firebaseStorage.providerLiveData
    }

/*   fun searchProjectsbyTitle(projTitle:String): LiveData<List<Project>> {
        return firebaseStorage.searchProjectsbyTitle(projTitle)
    }*/

   fun count(): LiveData<Int>{
        return firebaseStorage.count
    }


}
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
