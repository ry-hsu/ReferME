package bu.rhsu.referme.datalayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import androidx.room.TypeConverters


@Dao
interface ProviderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProvider(provider: Provider):Long

    @Delete
    suspend fun delProvider(provider: Provider)

    @Update
    suspend fun editProvider(provider: Provider)

    @Query("SELECT count(*) From providers")
    fun count(): LiveData<Int>

    @Query("SELECT * FROM providers")
    fun getAllProviders(): LiveData<List<Provider>>

    @Query("SELECT * FROM providers WHERE name = :name")
    fun getProviderByName(name: String): Provider

    @Query("SELECT * FROM providers WHERE name = :specialty")
    fun getProviderBySpecialty(specialty: String): Provider


}