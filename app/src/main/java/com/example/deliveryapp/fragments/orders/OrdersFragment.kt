package com.example.deliveryapp.fragments.orders

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryapp.R
import com.example.deliveryapp.adapter.OrdersItemsAdapter
import com.example.deliveryapp.databinding.OredersfragmentBinding
import com.example.deliveryapp.model.orders.DeliveryBill
import com.example.deliveryapp.model.orders.DeliveryBillRequest
import com.example.deliveryapp.model.orders.DeliveryBillValue
import com.example.deliveryapp.utils.Resource
import com.example.deliveryapp.viewmodel.DeliveryViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private lateinit var binding:OredersfragmentBinding
    private lateinit var ordersItemsAdapter:OrdersItemsAdapter
    private val viewmodel:DeliveryViewModel by viewModels()
    private val args:OrdersFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= OredersfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvUsername.text=args.delivery.P_DLVRY_Name
        OrdersRecylerview()
        ordersCallBack()
        initButton()
    }


    private fun initButton(){

        binding.btnNew.setOnClickListener {
            binding.btnOthers.setBackgroundResource(R.drawable.bg_editext_login)
            binding.btnNew.setTextColor(Color.WHITE)
            binding.btnNew.setBackgroundResource(R.drawable.bg_button_login)
            binding.btnOthers.setTextColor(Color.parseColor("#004F62"))

        }
        binding.btnOthers.setOnClickListener {
            binding.btnOthers.setBackgroundResource(R.drawable.bg_button_login)
            binding.btnNew.setTextColor(Color.parseColor("#004F62"))
            binding.btnNew.setBackgroundResource(R.drawable.bg_editext_login)
            binding.btnOthers.setTextColor(Color.WHITE)
        }
    }


    private fun OrdersRecylerview() {
        ordersItemsAdapter = OrdersItemsAdapter()
        binding.rvOrders.apply {
            adapter = ordersItemsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun ordersCallBack(){

        viewmodel.deliveryItemsresponse.observe(viewLifecycleOwner, Observer {
            when(it){

                is Resource.Loading->{
                    binding.progressBar.visibility=View.VISIBLE
                }

                is Resource.sucess->{
                    binding.progressBar.visibility=View.GONE
                    it.data?.data?.deliveryBills?.let { it1 -> updateUi(it1) }
                    ordersItemsAdapter.submitList(it.data?.data?.deliveryBills)
                    ordersItemsAdapter.notifyDataSetChanged()
                }

                is Resource.Error->{
                    binding.progressBar.visibility=View.GONE
                    Snackbar.make(requireView(), "${it.data?.result?.errMsg}", Snackbar.LENGTH_SHORT).show()
                }
            }

        })

        viewmodel.getDeliveryBillsItems(DeliveryBillRequest(DeliveryBillValue(args.delivery.P_DLVRY_NO,args.delivery.P_LANG_NO)))
    }


    private fun updateUi(it: List<DeliveryBill>) {
        if (it.isNullOrEmpty()) {
            binding.layoutNoOrdersimage.visibility = View.VISIBLE
        } else {
            binding.layoutNoOrdersimage.visibility = View.GONE
        }
    }

}