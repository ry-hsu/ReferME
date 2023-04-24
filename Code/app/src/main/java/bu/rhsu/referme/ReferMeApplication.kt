package bu.rhsu.referme

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import bu.rhsu.referme.datalayer.LoginRepository
import bu.rhsu.referme.datalayer.Provider
import bu.rhsu.referme.datalayer.ReferMeDatabase
import bu.rhsu.referme.datalayer.ReferMeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

//Login
import bu.rhsu.referme.firebase.FirebaseLogin
import bu.rhsu.referme.firebase.FirebaseStorage

class ReferMeApplication: Application() {
    lateinit var referMeDatabase: ReferMeDatabase
    lateinit var referMeRepository: ReferMeRepository
    lateinit var loginRepository: LoginRepository


    override fun onCreate() {
        super.onCreate()
        referMeDatabase =
            Room.databaseBuilder(
                applicationContext, ReferMeDatabase::class.java,
                "referMe-db"
            )
                // add a callback to modify onCreate() to
                // add some initial projects.
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //addInitProjects()
                    }
                })
                .build()

        referMeRepository = ReferMeRepository(FirebaseStorage())
/*        referMeRepository =
            ReferMeRepository(referMeDatabase.providerDao(),referMeDatabase.reviewDao())*/

        loginRepository =
            LoginRepository(dataSource = FirebaseLogin(this))
    }
}