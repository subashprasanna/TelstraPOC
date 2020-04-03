package com.cts.telstrapoc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cts.telstrapoc.model.repository.CountryRepository
import com.cts.telstrapoc.model.CanadaAPIDetail
import com.cts.telstrapoc.model.network.CustomThreadExecutor
import kotlinx.coroutines.Job
import javax.inject.Inject

class CountryViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    private lateinit var job: Job
    private val _countryDetail = MutableLiveData<CanadaAPIDetail>()

    val countryDetail : LiveData<CanadaAPIDetail>
        get() = _countryDetail

    fun  getCanadaInfo() {
        job = CustomThreadExecutor.runApiOnIOThreadthenMainThread(
                { repository.getCountryDetail()},
                { _countryDetail.value = it })
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
