package bu.rhsu.referme.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import bu.rhsu.referme.adapter.ProviderResultListRecyclerViewAdapter
import bu.rhsu.referme.datalayer.Provider
import bu.rhsu.referme.R
import bu.rhsu.referme.databinding.FragmentResultsBinding
import bu.rhsu.referme.viewmodel.curProviderViewModel
import bu.rhsu.referme.viewmodel.providerListViewModel
import bu.rhsu.referme.viewmodel.simpleSearchViewModel

/**
 * A fragment representing a list of Items.
 */
class ResultsFragment : Fragment() {
    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    private var columnCount = 1
    private var largeScreen = false

    private lateinit var myAdapter: ProviderResultListRecyclerViewAdapter
    //private lateinit var viewModel: curProviderViewModel
    private lateinit var listViewModel: providerListViewModel
    private lateinit var onProviderClickListener: OnProviderClickListener

    private lateinit var viewModel: simpleSearchViewModel

    interface OnProviderClickListener{
        fun onProviderClick(provider:Provider)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        arguments?.let {
            largeScreen = it.getBoolean(ARG_LARGE_SCREEN)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentResultsBinding.inflate(inflater,
            container, false)
        return binding.root
    }

/*    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnProviderClickListener) {
            onProviderClickListener = context
        } else {
            throw RuntimeException("Must implement EditProjectListener")
        }
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel =
            ViewModelProvider(requireActivity())[simpleSearchViewModel::class.java]
        listViewModel =
            ViewModelProvider(this)[providerListViewModel::class.java]

        binding.resultList.apply {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

            myAdapter = ProviderResultListRecyclerViewAdapter { provider ->
                viewModel.setCurProvider(provider)
                onProviderClickListener?.onProviderClick(provider)

                view.findNavController()?.navigate(R.id.action_resultsFragment_to_reviewFragment)
            }

            this.adapter = myAdapter
        }

        viewModel.providerResultList.observe(viewLifecycleOwner, Observer {
            myAdapter.replaceItems(it)
        })
/*
        listViewModel.curProviders.observe(viewLifecycleOwner, Observer {
            myAdapter.replaceItems(it)
            viewModel.initCurProvider(myAdapter.getProject(0))

        })*/

/*        viewModel.curProject.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })*/


    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_LARGE_SCREEN = "large-screen"


        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ResultsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}