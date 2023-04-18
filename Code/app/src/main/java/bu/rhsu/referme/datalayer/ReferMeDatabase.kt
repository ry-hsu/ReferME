package bu.rhsu.referme.datalayer

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Provider::class,Review::class],
    version = 1
)
abstract class ReferMeDatabase: RoomDatabase() {
    abstract fun providerDao(): ProviderDao
    abstract fun reviewDao(): ReviewDao
}