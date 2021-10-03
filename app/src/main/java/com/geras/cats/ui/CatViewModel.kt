package com.geras.cats.ui

import androidx.lifecycle.*
import com.geras.cats.data.Cat
import com.geras.cats.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatViewModel(private val repository: Repository) : ViewModel() {

    val cats: LiveData<List<Cat>> = repository.cats.asLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateCatsFromRemote()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    fun addCat(cat: Cat) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(cat)
        }
    }

    fun delete(cat: Cat) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(cat)
        }
    }

}

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
