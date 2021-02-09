package com.example.evaintkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.evaintkt.databinding.ControlConsumoBinding
import com.example.evaintkt.model.Entity

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemVH>() {

    private var itemTask = listOf<Entity>()
    private val selectedTaskItem = MutableLiveData<Entity>()
    fun selectedItem(): LiveData<Entity> = selectedTaskItem


    fun update(list: List<Entity>){
        itemTask = list
        notifyDataSetChanged()
    }
    inner class  ItemVH (private val binding: ControlConsumoBinding):
            RecyclerView.ViewHolder(binding.root),View.OnClickListener{
                fun bind(task: Entity){
                    binding.tVItem.text = task.item
                    binding.tVCantidad.text = task.cantidad.toString()
                    binding.tVPrecio.text = task.unitPrice.toString()
                    binding.tVTotal.text = task.total.toString()
                    itemView.setOnClickListener(this)
                }
        override fun onClick(v: View?) {
            selectedTaskItem.value = itemTask[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return  ItemVH(ControlConsumoBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        val task = itemTask[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = itemTask.size


}