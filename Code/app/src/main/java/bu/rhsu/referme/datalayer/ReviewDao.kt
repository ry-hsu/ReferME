package bu.rhsu.referme.datalayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import androidx.room.TypeConverters

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReview(review: Review):Long

    @Delete
    suspend fun delReview(review: Review)

    @Update
    suspend fun editReview(review: Review)

    @Query("SELECT count(*) From reviews")
    fun count(): LiveData<Int>

    @Query("SELECT * FROM reviews WHERE providerID = :provId")
    fun getReviewForProvider(provId: Int): MutableLiveData<List<Review>>

    @Query("SELECT * FROM reviews WHERE providerID = :provId ORDER BY date ASC")
    fun getReviewForProviderDate(provId:String)
}