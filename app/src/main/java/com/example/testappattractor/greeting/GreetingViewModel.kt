package com.example.testappattractor.greeting

import androidx.lifecycle.ViewModel
import com.example.testappattractor.data.GreetingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreetingViewModel @Inject constructor(private val repository: GreetingRepository) :
    ViewModel() {

    fun getGreetingModel(): GreetingModel {
        return repository.getGreetingData()
    }

}