package it.polito.showprofileactivity

import android.app.Application
import androidx.lifecycle.LiveData
import it.polito.listapplication.Advertisement

class AdvertisementRepository(application: Application) {

    private val advertisementDao = AdvertisementDatabase.getDatabase(application).advertisementDao()

    fun items(): LiveData<List<Advertisement>> = advertisementDao.findAll()
}