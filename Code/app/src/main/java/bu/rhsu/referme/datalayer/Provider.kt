package bu.rhsu.referme.datalayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="providers")
data class Provider(
    @PrimaryKey(autoGenerate = true)
    val provId: Int = 0,
    @ColumnInfo(name="name")
    var name: String = "",
    var zip: Int? = 0,
    @ColumnInfo(name="specialty")
    var specialty: String? = null,
    var phone:String? = null,
    var email: String? = null) {
    var docId:String = ""
}