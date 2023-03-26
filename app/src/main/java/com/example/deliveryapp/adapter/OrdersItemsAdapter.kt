package com.example.deliveryapp.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.DeliverApp
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.ItemLayoutOrdersBinding
import com.example.deliveryapp.model.orders.DeliveryBill


class OrdersItemsAdapter():ListAdapter<DeliveryBill,OrdersItemsAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=ItemLayoutOrdersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items=getItem(position)
        holder.binding.binditemorder=items

        holder.binding.price.text=items.bILLAMT.toDouble().toInt().toString()


        when(items.dLVRYSTATUSFLG){
            "1"->{
                holder.binding.layoutOrdersDetails.setBackgroundColor(Color.parseColor("#808080"))
                holder.binding.status.setTextColor(Color.parseColor("#808080"))
                holder.binding.status.text=DeliverApp.application.getString(R.string.Delivered)
            }
            "2"->{
                holder.binding.layoutOrdersDetails.setBackgroundColor(Color.parseColor("#004F62"))
                holder.binding.status.setTextColor(Color.parseColor("#004F62"))
                holder.binding.status.text=DeliverApp.application.getString(R.string.Delivering)
            }
            "3"->{
                holder.binding.layoutOrdersDetails.setBackgroundColor(Color.parseColor("#D42A0F"))
                holder.binding.status.setTextColor(Color.parseColor("#D42A0F"))
                holder.binding.status.text=DeliverApp.application.getString(R.string.Returned)
            }else->{
            holder.binding.layoutOrdersDetails.setBackgroundColor(Color.parseColor("#29D40F"))
            holder.binding.status.setTextColor(Color.parseColor("#29D40F"))
            holder.binding.status.text="New"
            }
        }
    }


    class ViewHolder(itemBinding: ItemLayoutOrdersBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal val binding: ItemLayoutOrdersBinding = itemBinding
    }


    private class DiffCallback : DiffUtil.ItemCallback<DeliveryBill>() {
        override fun areItemsTheSame(oldItem: DeliveryBill, newItem: DeliveryBill): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: DeliveryBill, newItem: DeliveryBill): Boolean {
            return true
        }
    }




}