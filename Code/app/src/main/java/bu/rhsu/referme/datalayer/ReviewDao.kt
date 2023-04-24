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

    @Query("SELECT * FROM reviews WHERE providerId = :provId")
    fun getReviewForProvider(provId: Int): LiveData<List<Review>>

    @Query("SELECT * FROM reviews WHERE providerId = :provId ORDER BY datetime ASC")
    fun getReviewForProviderDate(provId: Int): LiveData<List<Review>>
}