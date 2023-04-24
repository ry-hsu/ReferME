package bu.rhsu.referme.datalayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="reviews")
data class Review(
    @PrimaryKey(autoGenerate = true)
    val reviewId: Int,
    @ColumnInfo(name="datetime")
    var date: String,
    @ColumnInfo(name="providerId")
    val providerID: Int,
    @ColumnInfo(name="userId")
    var userID: String,
    var bedManner: Float,
    var expertise: Float,
    var frontOffice: Float,
    var facility: Float,
    var text: String?) {
    var docId = ""
}