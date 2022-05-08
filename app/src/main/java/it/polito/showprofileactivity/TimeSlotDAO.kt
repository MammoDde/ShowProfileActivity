package it.polito.showprofileactivity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TimeSlotDAO {

    @Query("SELECT count() from items")
    fun count(): LiveData<Int>

}