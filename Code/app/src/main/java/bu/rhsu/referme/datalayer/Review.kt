package bu.rhsu.referme.datalayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="reviews")
data class Review(
    @PrimaryKey(autoGenerate = true)
    val reviewId: Int = 0,
    @ColumnInfo(name="datetime")
    var date: String? = null,
    @ColumnInfo(name="providerId")
    val providerID: String? = null,
    @ColumnInfo(name="userId")
    var userID: String? = null,
    var bedManner: Float = 0f,
    var expertise: Float = 0f,
    var frontOffice: Float = 0f,
    var facility: Float = 0f,
    var text: String? = null) {
    var docId = ""
}