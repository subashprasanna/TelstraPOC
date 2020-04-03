package com.cts.telstrapoc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cts.telstrapoc.model.repository.CountryRepository
import javax.inject.Inject


class CountryViewModelFactory @Inject constructor(
    private val repository: CountryRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CountryViewModel(repository) as T
    }
}