package bu.rhsu.referme.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import bu.rhsu.referme.databinding.FragmentSingleReviewBinding
import bu.rhsu.referme.viewmodel.curReviewViewModel
import androidx.lifecycle.Observer

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SingleReviewFragment : Fragment() {

    private var _binding: FragmentSingleReviewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSingleReviewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel =
            ViewModelProvider(requireActivity()).get(curReviewViewModel::class.java)

        viewModel.curReview.observe(viewLifecycleOwner, Observer {

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}