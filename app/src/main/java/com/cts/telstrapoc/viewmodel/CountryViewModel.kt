package com.cts.telstrapoc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cts.telstrapoc.model.CanadaAPIDetail
import com.cts.telstrapoc.model.CanadaAPIDetailInfo
import com.cts.telstrapoc.model.network.CustomThreadExecutor
import com.cts.telstrapoc.model.repository.CountryRepository
import com.cts.telstrapoc.util.INITIAL_PAGE_TITLE
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
                {
                    _countryDetail.value = removeEmptyCountryInformation(it)
                })
    }

    fun removeEmptyCountryInformation(result: CanadaAPIDetail?): CanadaAPIDetail {
        val finalList : MutableList<CanadaAPIDetailInfo> = mutableListOf()

        val title = result?.title ?: INITIAL_PAGE_TITLE
        val list = result?.rows ?: mutableListOf<CanadaAPIDetailInfo>()

        for (element in list) {
            if (!element?.title.isNullOrEmpty()) {
                finalList.add(element)
            }
        }
        return CanadaAPIDetail(finalList, title)
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
