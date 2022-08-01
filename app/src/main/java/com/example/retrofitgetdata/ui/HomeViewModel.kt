package com.example.retrofitgetdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.retrofitgetdata.database.CardDatabaseDao
import com.example.retrofitgetdata.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(private val databaseDao: CardDatabaseDao): ViewModel() {
    private val repository = Repository(databaseDao)

    val universityContent = repository.universityContent
    val blogContent = repository.blogContent
    val countryContent = repository.countryContent
    val departmentContent = repository.departmentContent

    init {
        repository.refreshContent()
    }
}

class HomeViewModelFactory(
    private val databaseDao: CardDatabaseDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(databaseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}