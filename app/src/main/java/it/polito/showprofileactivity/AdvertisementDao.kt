package it.polito.showprofileactivity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import it.polito.listapplication.Advertisement

@Dao
interface AdvertisementDao {

    @Query("SELECT * FROM advertisement")
    fun findAll() : LiveData<List<Advertisement>>

}