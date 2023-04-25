package bu.rhsu.referme.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import bu.rhsu.referme.R
import bu.rhsu.referme.databinding.FragmentReviewBinding
import bu.rhsu.referme.viewmodel.curProviderViewModel
import bu.rhsu.referme.viewmodel.simpleSearchViewModel


class ReviewFragment : Fragment() {
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchViewModel: simpleSearchViewModel
    private lateinit var reviewViewModel: curProviderViewModel

    private var bedReviews: Int = 0
    private var expertReviews: Int = 0
    private var officeReviews: Int = 0
    private var facilityReviews: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReviewBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel =
            ViewModelProvider(requireActivity())[simpleSearchViewModel::class.java]

        reviewViewModel =
            ViewModelProvider(requireActivity())[curProviderViewModel::class.java]

        searchViewModel.curprovider.observe(viewLifecycleOwner, Observer {
            reviewViewModel.setCurprovider(it)
            Log.d("searchviewmodle","set here")
        })

        reviewViewModel.providerReviewList.observe(viewLifecycleOwner, Observer {

        })

        reviewViewModel.curprovider.observe(viewLifecycleOwner, Observer{
            reviewViewModel.getReviews(it)

            binding.name.text = it.name
            binding.specialty.text = it.specialty

            var contactInfo: String = ""

            if(it.email != null) contactInfo += "Email: " + it.email + "\n"
            if(it.phone != null) contactInfo += "Phone: " + it.phone + "\n"
            if(it.zip != null) contactInfo += "Zipcode: " + it.zip + "\n"

            binding.contact.text = contactInfo

            var count: Int = if(reviewViewModel.reviewCount == null) 0 else reviewViewModel.reviewCount!!

            Log.d("observer",reviewViewModel.providerReviewList.value.toString())
            binding.statisticsTextView.text = "Reviews($count)"

            setupStars()
        })

        binding.addReviewButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_reviewFragment_to_FirstFragment)
        }


    }

    private fun setupStars() {
        var averages = reviewViewModel.getAvgReviews()
        if(averages.component1() != null) {
            binding.bedRating.rating = averages.component1()!!
            binding.bedTextRating.text = averages.component1().toString() + "/5"
        } else {
            binding.bedRating.rating = 0f
            binding.bedTextRating.text = "No Reviews"
        }
        if(averages.component2() != null) {
            binding.expertRating.rating = averages.component2()!!
            binding.expertTextRating.text = averages.component2().toString() + "/5"
        } else {
            binding.expertRating.rating = 0f
            binding.expertTextRating.text = "No Reviews"
        }
        if(averages.component3() != null) {
            binding.officeRating.rating = averages.component3()!!
            binding.officeTextRating.text = averages.component3().toString() + "/5"
        } else {
            binding.officeRating.rating = 0f
            binding.officeTextRating.text = "No Reviews"
        }
        if(averages.component4() != null) {
            binding.facilityRating.rating = averages.component4()!!
            binding.facilityTextRating.text = averages.component2().toString() + "/5"
        } else {
            binding.facilityRating.rating = 0f
            binding.facilityTextRating.text = "No Reviews"
        }
    }

    private fun refreshFragment() {
        // This method refreshes the fragment
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_reviewFragment_self)
    }

}