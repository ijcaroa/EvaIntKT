package com.example.evaintkt

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.evaintkt.databinding.FragmentSecondBinding
import com.example.evaintkt.model.Entity
import com.example.evaintkt.viewModel.ItemViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private val viewModel: ItemViewModel by activityViewModels()

    private var idTask: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idTask = it.getInt("id",-1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.NPicker.maxValue = 40
        binding.NPicker.minValue = 1
        binding.NPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            Log.d("PICKER", "${picker.value}")

            viewModel.calculateTotal(picker.value, binding.eTPrecio.text.toString().toInt())

        }

        viewModel.mutableTotal.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.tvTotal.text = it.toString()
            }
        })

       binding.buttonSecond.setOnClickListener {
            saveItem()





            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        viewModel.getTaskById(idTask).observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.eTItem.setText(it.item)
            }
        })

    }
    private fun saveItem(){
        val item = binding.eTItem.text.toString()
        val numeroConsumo = binding.NPicker.value
        var precio = binding.eTPrecio
        val totalConsumido = binding.tvTotal.text.toString().toInt()

       if (item.isEmpty()){
            Toast.makeText(context, "Debe ingresar datos", Toast.LENGTH_LONG).show()
        } else {
            if (idTask == -1){
                val newItem = Entity(item = item, cantidad = numeroConsumo, total = totalConsumido, unitPrice = 0)
                viewModel.insertTask(newItem)
            } else{
                val updateTask = Entity(id = idTask, item = item,cantidad = numeroConsumo, total = totalConsumido,unitPrice = 0)
                viewModel.updateTask(updateTask)
            }
        }
    }
    /*fun calculo (){
        var precio = binding.eTPrecio
        var numero = binding.NPicker.value
        return precio
    }*/





}

