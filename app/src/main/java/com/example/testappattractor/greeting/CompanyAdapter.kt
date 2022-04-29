package com.example.testappattractor.greeting

import com.example.testappattractor.base.BaseAdapter
import com.example.testappattractor.R
import com.example.testappattractor.data.Company
import com.example.testappattractor.databinding.ItemCompanyBinding

class CompanyAdapter : BaseAdapter<Company, ItemCompanyBinding>(
    R.layout.item_company,
    listOf(), ItemCompanyBinding::inflate
) {
    override fun onBind(binding: ItemCompanyBinding, model: Company) {
        binding.nameTv.text = "Name ${model.name}"
        binding.positionTv.text = "Position ${model.position}"
    }
}