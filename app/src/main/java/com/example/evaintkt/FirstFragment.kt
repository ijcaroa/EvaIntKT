package com.example.evaintkt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evaintkt.databinding.FragmentFirstBinding
import com.example.evaintkt.viewModel.ItemViewModel


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = ItemAdapter()
        binding.rVConsumo.adapter = adapter
        binding.rVConsumo.layoutManager = LinearLayoutManager(context)
        binding.rVConsumo.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))

        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })
        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                //val bundle = Bundle()
                //bundle.putInt("id",it.id)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment) //,bundle)
            }
        })
        binding.fab3.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings -> {
                Toast.makeText(context, "SE ELIMINÓ TODO", Toast.LENGTH_LONG).show()
                viewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }



}