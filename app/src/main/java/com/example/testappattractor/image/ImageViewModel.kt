package com.example.testappattractor.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {

    private val _images = MutableSharedFlow<ArrayList<String>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val images: SharedFlow<ArrayList<String>> = _images.asSharedFlow()
    var listImages = arrayListOf<String>()

    fun addImages(image: String) {
        viewModelScope.launch {
            listImages.add(image)
            _images.emit(listImages)
        }
    }
}