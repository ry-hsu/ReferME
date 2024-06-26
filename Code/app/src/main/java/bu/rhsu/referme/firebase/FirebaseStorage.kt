package bu.rhsu.referme.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Delete
//import androidx.room.Query
import androidx.room.Update
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query

import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.OnCompleteListener

import bu.rhsu.referme.datalayer.Provider
import bu.rhsu.referme.datalayer.Review
import com.google.firebase.firestore.ktx.firestore


class FirebaseStorage {

    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()
    private val TAG = javaClass.simpleName

    val reviewLiveData: MutableLiveData<List<Review>> = MutableLiveData()
    val providerLiveData: MutableLiveData<List<Provider>> = MutableLiveData()

    var count: MutableLiveData<Int> = MutableLiveData<Int>(0)

    private fun getProviderRef(): CollectionReference {
        return firebaseFirestore.collection("providers")
    }

    private fun getReviewRef() : CollectionReference {
        return firebaseFirestore.collection("reviews")
    }

    //Providers

    fun addProvider(provider: Provider){
        getProviderRef().add(provider).
            addOnSuccessListener ( OnSuccessListener<DocumentReference> () {
                    Log.d (TAG, "DocumentSnapshot added with ID: " + it.id);
            }).addOnFailureListener (OnFailureListener () {
                    Log.w (TAG, "Error adding document", it);

            })
    }

    fun delProvider(provider: Provider){
        getProviderRef().document(provider.docId)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }


    fun loadAllProviders(){
        getProviderRef()
            .addSnapshotListener {documents,e->
                var providers = ArrayList<Provider>()
                documents?.forEach{document ->
                        document.toObject<Provider>(Provider::class.java).let{
                                project->
                            project.docId = document.id
                            providers.add(project)
                        }
                }
                providerLiveData.setValue(providers)
                e?.let{
                    Log.d("Firebase storage","listen failed", e)
                }
            }

    }

    fun getProviderByName(name: String) {
        getProviderRef()
            .whereEqualTo("name",name)
            .get()
            .addOnSuccessListener {documents ->
                var providers = ArrayList<Provider>()
                documents?.forEach {document ->
                    document.toObject<Provider>(Provider::class.java).let {
                            provider ->
                        provider.docId = document.id
                        providers.add(provider)
                    }
                }
                providerLiveData.setValue(providers)
            }
    }


    fun getProviderByName(name: String, specialty: String, zip: Int) {

        var query = getProviderRef().whereGreaterThan("name","")
        if(name != "") {
            query = query.whereEqualTo("name",name)
            Log.d("FirebaseGet","Name")
        }
        if(specialty != "") {
            query = query.whereEqualTo("specialty",specialty)
            Log.d("FirebaseGet","Specialty")
        }
        if(zip != 0) {
            query = query.whereEqualTo("zip",zip)
            Log.d("FirebaseGet","Zip")
        }

        if(name != "" || specialty != "" || zip != 0) {
            query.get().addOnSuccessListener {documents ->
                var providers = ArrayList<Provider>()
                documents?.forEach {document ->
                    document.toObject<Provider>(Provider::class.java).let {
                            provider ->
                        provider.docId = document.id
                        providers.add(provider)
                    }
                }
                providerLiveData.setValue(providers)
            }
        }
    }
    fun getProviderByName(zip: Int) {
        getProviderRef()
            .whereEqualTo("zip",zip)
            .get()
            .addOnSuccessListener {documents ->
                var providers = ArrayList<Provider>()
                documents?.forEach {document ->
                    document.toObject<Provider>(Provider::class.java).let {
                            provider ->
                        provider.docId = document.id
                        providers.add(provider)
                    }
                }
                providerLiveData.setValue(providers)
            }
    }

    fun getProviderBySpecialty(specialty: String) {
        getProviderRef()
            .whereEqualTo("specialty",specialty)
            .get()
            .addOnSuccessListener {documents ->
                var providers = ArrayList<Provider>()
                documents?.forEach {document ->
                    document.toObject<Provider>(Provider::class.java).let {
                            provider ->
                        provider.docId = document.id
                        providers.add(provider)
                    }
                }
                providerLiveData.setValue(providers)
            }
    }



    //Reviews

    fun addReview(review: Review){
        getReviewRef().add(review).
        addOnSuccessListener ( OnSuccessListener<DocumentReference> () {
            Log.d (TAG, "DocumentSnapshot added with ID: " + it.id);
        }).addOnFailureListener (OnFailureListener () {
            Log.w (TAG, "Error adding document", it);

        })
    }


    fun delReview(review: Review){
        getReviewRef().document(review.docId)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    fun loadReviewsForProvider(providerId: String){
        getReviewRef()
            .whereEqualTo("providerID",providerId)
            .get()
            .addOnSuccessListener {documents ->
                var reviews = ArrayList<Review>()
                documents?.forEach {document ->
                    document.toObject<Review>(Review::class.java).let {
                            review ->
                        review.docId = document.id
                        reviews.add(review)
                    }
                }
                reviewLiveData.setValue(reviews)
            }
    }

    fun loadAllReviews(){
        getReviewRef()
            .get()
            .addOnSuccessListener {documents ->
                var reviews = ArrayList<Review>()
                documents?.forEach {document ->
                    document.toObject<Review>(Review::class.java).let {
                            review ->
                        review.docId = document.id
                        reviews.add(review)
                    }
                }
                reviewLiveData.setValue(reviews)
            }
    }


}