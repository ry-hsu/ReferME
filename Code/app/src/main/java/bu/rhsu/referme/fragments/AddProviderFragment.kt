package bu.rhsu.referme.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bu.rhsu.referme.R

import bu.rhsu.referme.datalayer.Provider
import androidx.navigation.findNavController
import bu.rhsu.referme.databinding.FragmentAddProvBinding
import bu.rhsu.referme.viewmodel.AddViewModel
import bu.rhsu.referme.viewmodel.providerListViewModel

class AddProviderFragment : Fragment(),View.OnClickListener {

    private var _binding: FragmentAddProvBinding? = null
    private val binding get() = _binding!!

    private lateinit var listViewModel: providerListViewModel
    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProvBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel =
            ViewModelProvider(requireActivity()).get(providerListViewModel::class.java)
        viewModel =
            ViewModelProvider(requireActivity()).get(AddViewModel::class.java)

        binding.submit.setOnClickListener (this)
        binding.cancel.setOnClickListener (this)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onClick(view: View) {
        if (view.id == R.id.submit) {
            val provider = Provider(
                0, binding.provNameEdit.text.toString(),
                binding.editTextProvZip.text.toString().toInt(),
                binding.editSpecialtyMultiLine.text.toString(),
                binding.editTextProvPhone.text.toString(),
                binding.editTextEmail.text.toString())
            //listViewModel.addProject(project)
            viewModel.addProvider(provider)
        }
        view.findNavController().navigate(R.id.action_addProviderFragment_to_SecondFragment)
        activity?.onBackPressed()

    }

}