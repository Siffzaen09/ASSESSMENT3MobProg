package org.d3if4081.ass1.ui.benda_jajar_genjang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if4081.ass1.databinding.FragmentBendaJajarGenjangBinding
import org.d3if4081.ass1.network.ApiStatus

class BendaJajarGenjangFragment: Fragment() {

    private val viewModel: BendaJajarGenjangViewModel by lazy {
        ViewModelProvider(this).get(BendaJajarGenjangViewModel::class.java)
    }
    private lateinit var binding: FragmentBendaJajarGenjangBinding
    private lateinit var myAdapter: BendaJajarGenjangAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBendaJajarGenjangBinding.inflate(layoutInflater, container, false)
        myAdapter = BendaJajarGenjangAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })
        viewModel.scheduleUpdater(requireActivity().application)
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }ApiStatus.SUCCESS -> {
            binding.progressBar.visibility = View.GONE
        }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}