package com.example.testappattractor.greeting

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.testappattractor.R
import com.example.testappattractor.data.Company
import com.example.testappattractor.data.GreetingData
import com.example.testappattractor.databinding.FragmentGreetingBinding
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GreetingFragment : Fragment(R.layout.fragment_greeting) {

    private val binding: FragmentGreetingBinding by viewBinding()

    private val viewModel: GreetingViewModel by viewModels()

    @Inject
    lateinit var greetingData: GreetingData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        initClickers()
    }

    private fun initClickers() {
        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.imageFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        val model = viewModel.getGreetingModel().user
        with(binding) {
            firstNameTv.text = "First Name : ${model.first_name}"
            secondNameTv.text = "Second Name : ${model.second_name}"
            educationTv.text = "Education : ${getEducation(model.education)}"
            photoImg.load(model.photo.toUri()) {
                transformations(CircleCropTransformation())
            }
        }

        setupAdapter(model.company)
    }

    private fun setupAdapter(list: List<Company>) {
        val companyAdapter = CompanyAdapter()
        companyAdapter.data = list

        binding.companyRecycler.apply {
            adapter = companyAdapter
            addItemDecoration(
                    DividerItemDecoration(
                        requireContext(), LinearLayoutManager.VERTICAL
                    )
            )
        }
    }

    private fun getEducation(value: Int): String {
        var education = ""
        when (value) {
            1 -> {
                education = "high_school"
            }
            2 -> {
                education = "bachelor"
            }
            3 -> {
                education = "master"
            }
            4 -> {
                education = "doctoral"
            }
        }
        return education
    }

}