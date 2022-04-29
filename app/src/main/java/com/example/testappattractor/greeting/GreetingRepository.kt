package com.example.testappattractor.greeting

import com.example.testappattractor.data.GreetingData
import com.example.testappattractor.data.GreetingModel
import com.google.gson.Gson
import javax.inject.Inject

class GreetingRepository @Inject constructor(private val greetingData: GreetingData) {

    fun getGreetingData(): GreetingModel {
        val gson = Gson()
        return gson.fromJson(greetingData.getGreetingJson(), GreetingModel::class.java)
    }

}