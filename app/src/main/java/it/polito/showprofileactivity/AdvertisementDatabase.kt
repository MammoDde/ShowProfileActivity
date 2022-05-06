import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import it.polito.listapplication.Advertisement
import it.polito.showprofileactivity.AdvertisementDao

@Database(entities = [Advertisement::class], version = 1)
abstract class AdvertisementDatabase: RoomDatabase() {
    abstract fun advertisementDao(): AdvertisementDao

    companion object {
        @Volatile
        private var INSTANCE: AdvertisementDatabase? = null

        fun getDatabase(context:Context): AdvertisementDatabase =
            (
                    INSTANCE?:
                    synchronized(this) {
                        val i = INSTANCE ?: Room.databaseBuilder(
                            context.applicationContext,
                            AdvertisementDatabase::class.java,
                            "items"
                        ).build()
                        INSTANCE = i
                        INSTANCE
                    }
                    )!!


    }

}