package com.example.testappattractor.image

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testappattractor.R
import com.example.testappattractor.databinding.FragmentImageBinding
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
@AndroidEntryPoint
class ImageFragment : Fragment(R.layout.fragment_image) {

    private val binding: FragmentImageBinding by viewBinding()
    private val viewModel: ImageViewModel by viewModels()
    private var PICK_IMAGE_MULTIPLE = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
        initObserve()
    }

    private fun initObserve() {
        viewModel.images.onEach {
            initAdapter(it)
        }.launchIn(lifecycleScope)
    }

    private fun initAdapter(list:ArrayList<String>) {
        if (list.isNotEmpty()) {
            val imageAdapter = ImageAdapter()
            imageAdapter.data = list
            binding.imageRecycler.apply {
                adapter = imageAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(), LinearLayoutManager.VERTICAL
                    )
                )
            }
        }
    }

    private fun initClickers() {
        binding.chooseImageBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT < 19) {
                var intent = Intent()
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE
                )
            } else {
                var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(intent, PICK_IMAGE_MULTIPLE);
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == Activity.RESULT_OK
            && null != data
        ) {
            if (data.clipData != null) {
                val count = data.clipData!!.itemCount
                for (i in 0 until count) {
                    val imageUri: String = data.clipData?.getItemAt(i)!!.uri.toString()
                    viewModel.addImages(imageUri)
                }
            }
        }
    }
}