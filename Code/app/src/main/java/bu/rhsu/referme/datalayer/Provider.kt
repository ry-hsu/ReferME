package bu.rhsu.referme.datalayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="providers")
data class Provider(
    @PrimaryKey(autoGenerate = true)
    val provId: Int,
    var name: String,
    var zip: Int?,
    var specialty: String?,
    var phone:String?,
    var email: String?) {
}