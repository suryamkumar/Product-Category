package com.example.task_inovantinovant.screens

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_inovantinovant.adapters.ColorsAdapter
import com.example.task_inovantinovant.api.ApiInstance
import com.example.task_inovantinovant.databinding.FragmentProductDetailsBinding
import com.example.task_inovantinovant.repository.ProductRepo
import com.example.task_inovantinovant.viewmodel.ProductViewModel
import com.example.task_inovantinovant.viewmodel.ProductViewModelFactory
import com.example.task_inovantinovant.R
import com.example.task_inovantinovant.adapter.ImagePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class ProductDetails : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProductViewModel
    private var isExpanded = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiInterface = ApiInstance.apiInterface
        val repo = ProductRepo(apiInterface)
        val factory = ProductViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[ProductViewModel::class.java]

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        binding.colorsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.headerLayout.setOnClickListener {
            isExpanded = !isExpanded
            binding.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
            binding.arrowImageView.setImageResource(
                if (isExpanded) R.drawable.outline_arrow_up else R.drawable.outline_arrow_down
            )
        }

        viewModel.productsDetails.observe(viewLifecycleOwner) { response ->
            val price = response.data?.price
            if (!price.isNullOrEmpty()) {
                binding.priceTest.text = price
            }
            val name = response.data?.name
            if (!name.isNullOrEmpty()) {
                binding.nameText.text = name
            }
            val sku = response.data?.sku
            if (!sku.isNullOrEmpty()) {
                binding.skuText.text = sku
            }

            val descriptionHtml = response.data?.description ?: ""
            binding.descriptionText.text =
                Html.fromHtml(descriptionHtml, Html.FROM_HTML_MODE_LEGACY)
            binding.descriptionText.movementMethod = LinkMovementMethod.getInstance()
        }

        viewModel.productsDetails.observe(viewLifecycleOwner) { response ->
            val images = response.data?.images?.filterNotNull() ?: emptyList()

            val imageAdapter = ImagePagerAdapter(images)
            binding.imageViewPager.adapter = imageAdapter

            TabLayoutMediator(binding.tabDots, binding.imageViewPager) { _, _ -> }.attach()
        }

        viewModel.productsDetails.observe(viewLifecycleOwner) { response ->
            val images = response.data?.images?.filterNotNull() ?: emptyList()
            val colorsAdapter = ColorsAdapter(images)
            binding.colorsRecyclerView.adapter = colorsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
