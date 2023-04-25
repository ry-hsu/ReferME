package bu.rhsu.referme.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import bu.rhsu.referme.databinding.FragmentSingleReviewBinding
import bu.rhsu.referme.viewmodel.curReviewViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import bu.rhsu.referme.adapter.ProviderResultListRecyclerViewAdapter
import bu.rhsu.referme.datalayer.Provider
import bu.rhsu.referme.datalayer.Review
import bu.rhsu.referme.R
import bu.rhsu.referme.ui.login.LoginViewModel
import bu.rhsu.referme.viewmodel.curProviderViewModel
import bu.rhsu.referme.viewmodel.providerListViewModel
import bu.rhsu.referme.viewmodel.simpleSearchViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


class SingleReviewFragment : Fragment() {

    private var _binding: FragmentSingleReviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: curReviewViewModel
    private lateinit var providerViewModel: curProviderViewModel
    private lateinit var logViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSingleReviewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(requireActivity())[curReviewViewModel::class.java]
        providerViewModel =
            ViewModelProvider(requireActivity())[curProviderViewModel::class.java]
        logViewModel =
            ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val current = formatter.format(date)

        providerViewModel.curprovider.observe(viewLifecycleOwner,Observer {
            binding.name.text = it.name
            binding.specialty.text = it.specialty

            var contactInfo: String = ""

            if(it.email != null) contactInfo += "Email: " + it.email + "\n"
            if(it.phone != null) contactInfo += "Phone: " + it.phone + "\n"
            if(it.zip != null) contactInfo += "Zipcode: " + it.zip + "\n"

            binding.contact.text = contactInfo
        })

        binding.submitReviewButton.setOnClickListener {
            val review = Review(
                0,
                current.toString(),
                providerViewModel.curprovider.value?.docId,
                logViewModel.loggedInUser.value?.userId,
                binding.bedSlider.value,
                binding.expertSlider.value,
                binding.foSlider.value,
                binding.facilitySlider.value,
                binding.editTextTextMultiLine.text.toString()
            )
            viewModel.addReview(review)

            findNavController().navigate(R.id.action_FirstFragment_pop)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}