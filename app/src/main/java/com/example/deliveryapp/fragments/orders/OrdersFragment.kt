package com.example.deliveryapp.fragments.orders

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
import com.example.deliveryapp.viewmodel.SharedDataViewmodel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private lateinit var binding: OredersfragmentBinding
    private lateinit var ordersItemsAdapter: OrdersItemsAdapter
    private val viewmodel: DeliveryViewModel by viewModels()
    private val args: OrdersFragmentArgs by navArgs()
    val sharedDataViewmodel: SharedDataViewmodel by activityViewModels()
    private var choose = ""
    private var dateFrom=""
    private var dateTo=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = OredersfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvUsername.text = args.delivery.P_DLVRY_Name
        OrdersRecylerview()
        getOrders()
        initButton()
        ordersCallBack()
    }


    private fun initButton() {

        binding.layoutFilter.setOnClickListener {
            val action=OrdersFragmentDirections.actionOrdersFragmentToDilaogFragment("date")
            findNavController().navigate(action)
        }

        binding.btnNew.setOnClickListener {
            binding.btnOthers.setBackgroundResource(R.drawable.bg_editext_login)
            binding.btnNew.setTextColor(Color.WHITE)
            binding.btnNew.setBackgroundResource(R.drawable.bg_button_login)
            binding.btnOthers.setTextColor(Color.parseColor("#004F62"))
            choose = "New"
            ordersCallBack()

        }
        binding.btnOthers.setOnClickListener {
            binding.btnOthers.setBackgroundResource(R.drawable.bg_button_login)
            binding.btnNew.setTextColor(Color.parseColor("#004F62"))
            binding.btnNew.setBackgroundResource(R.drawable.bg_editext_login)
            binding.btnOthers.setTextColor(Color.WHITE)
            choose = "Others"
            ordersCallBack()
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

    private fun ordersCallBack() {

        viewmodel.deliveryItemsresponse.observe(viewLifecycleOwner, Observer { it ->
            when (it) {

                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.sucess -> {
                    binding.progressBar.visibility = View.GONE
                    when (choose) {
                        "Others" -> {
                            val orders = it.data?.data?.deliveryBills?.filter {
                                it.dLVRYSTATUSFLG == "1" || it.dLVRYSTATUSFLG == "2" || it.dLVRYSTATUSFLG == "3"
                            }
                            orders?.let { it1 -> updateUi(it1) }
                            ordersItemsAdapter.submitList(orders)
                            ordersItemsAdapter.notifyDataSetChanged()
                        }
                        "New" -> {
                            val orders = it.data?.data?.deliveryBills?.filter {
                                it.dLVRYSTATUSFLG != "1" && it.dLVRYSTATUSFLG != "2" && it.dLVRYSTATUSFLG != "3"

                            }
                            orders?.let { it1 -> updateUi(it1) }
                            ordersItemsAdapter.submitList(orders)
                            ordersItemsAdapter.notifyDataSetChanged()

                        }
                        "date" -> {
                            val orders = it.data?.data?.deliveryBills?.filter {
                                it.bILLDATE in dateFrom..dateTo

                            }
                            orders?.let { it1 -> updateUi(it1) }
                            ordersItemsAdapter.submitList(orders)
                            ordersItemsAdapter.notifyDataSetChanged()

                        }

                        else -> {
                            it.data?.data?.let { it1 -> updateUi(it1.deliveryBills) }
                            ordersItemsAdapter.submitList(it.data?.data?.deliveryBills)
                            ordersItemsAdapter.notifyDataSetChanged()
                        }

                    }

                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        requireView(),
                        "${it.data?.result?.errMsg}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

        })

        viewmodel.getDeliveryBillsItems(
            DeliveryBillRequest(
                DeliveryBillValue(
                    args.delivery.P_DLVRY_NO,
                    args.delivery.P_LANG_NO
                )
            )
        )
    }


    private fun updateUi(it: List<DeliveryBill>) {
        if (it.isNullOrEmpty()) {
            binding.layoutNoOrdersimage.visibility = View.VISIBLE
        } else {
            binding.layoutNoOrdersimage.visibility = View.GONE
        }
    }

    private fun getOrders(){

        sharedDataViewmodel.date.observe(viewLifecycleOwner, Observer {
            if (!it.from.isNullOrEmpty() && !it.to.isNullOrEmpty()){
                dateFrom=it.from
                dateTo=it.to
                choose="date"
                ordersCallBack()
            }
        })

    }

}