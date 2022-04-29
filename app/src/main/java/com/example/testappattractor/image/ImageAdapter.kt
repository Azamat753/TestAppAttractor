package com.example.testappattractor.image

import coil.load
import com.example.testappattractor.base.BaseAdapter
import com.example.testappattractor.R
import com.example.testappattractor.databinding.ItemImageBinding

class ImageAdapter : BaseAdapter<String, ItemImageBinding>(
    R.layout.item_image,
    listOf(),
    ItemImageBinding::inflate
) {
    override fun onBind(binding: ItemImageBinding, image: String) {
        binding.photoImg.load(image)
    }
}