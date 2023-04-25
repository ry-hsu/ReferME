package bu.rhsu.referme.fragments

//import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import bu.rhsu.referme.R
import bu.rhsu.referme.databinding.FragmentSimpleSearchBinding
import bu.rhsu.referme.viewmodel.AddViewModel
import bu.rhsu.referme.viewmodel.simpleSearchViewModel

class SimpleSearchFragment : Fragment() {

    private var _binding: FragmentSimpleSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: simpleSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSimpleSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(requireActivity())[simpleSearchViewModel::class.java]

        binding.simpleSearchButton.setOnClickListener {
            if(isDataValid()) {
                //Do a simple provider search
/*                viewModel.setProviderList(viewModel
                    .getProvider(binding.editSearchProviderName.text.toString()))*/
                //Complex search
                viewModel.setProviderList(viewModel
                    .getProvidersAll(binding.editSearchProviderName.text.toString(),
                                binding.editSearchSpecialty.text.toString(),
                                binding.editSearchZip.text.toString().toInt()))
                Log.d("simple search",viewModel.providerResultList.value.toString())
                view.findNavController().navigate(R.id.action_simpleSearchFragment2_to_resultsFragment)
            }
            else {
                Toast.makeText(
                    activity,
                    "Name or specialty and zip are required to search",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun isDataValid():Boolean {
        return ((binding.editSearchProviderName.text != null
                || binding.editSearchSpecialty.text != null)
                || binding.editSearchZip.text != null)

    }

}