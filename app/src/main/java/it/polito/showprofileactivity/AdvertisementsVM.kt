package it.polito.showprofileactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.polito.listapplication.Advertisement

import kotlin.concurrent.thread

class AdvertisementsVM(application: Application): AndroidViewModel(application) {

    val repo = AdvertisementRepository(application)

    val items: LiveData<List<Advertisement>> = repo.items()

}