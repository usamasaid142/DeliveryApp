package com.example.deliveryapp.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.DilaogfragmentBinding
import com.example.deliveryapp.model.OrderBYDate
import com.example.deliveryapp.utils.DateUtils.convertLongToDate
import com.example.deliveryapp.utils.DateUtils.toTimeDateString
import com.example.deliveryapp.viewmodel.SharedDataViewmodel
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Date


class DilaogFragment : DialogFragment() {

    private lateinit var binding: DilaogfragmentBinding
    private val args:DilaogFragmentArgs by navArgs()
    val sharedDataViewmodel: SharedDataViewmodel by activityViewModels()


    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= DilaogfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeViews(args.choose)
        initButton()
    }

    private fun initButton(){

        binding.layoutBtnFrom.setOnClickListener {
            showDatepicker(it)
        }
        binding.layoutBtnTo.setOnClickListener {
            showDatepicker(it)
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnApply.setOnClickListener {
         sharedDataViewmodel.getDate(OrderBYDate(binding.tvFrom.text.toString(),binding.tvTO.text.toString()))
            dismiss()
        }

    }


    private fun changeViews(selectviews: String) {

        when (selectviews) {
            "lang" -> {
                binding.layoutLang.visibility = View.VISIBLE
            }
            "date" -> {
                binding.layoutFilter.visibility = View.VISIBLE
            }
        }
    }

    private fun showDatepicker(view: View) {

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()

        datePicker.show(requireActivity().supportFragmentManager.beginTransaction(), "date range")

        datePicker.addOnPositiveButtonClickListener { date ->

            Toast.makeText(
                requireContext(),
                "${convertLongToDate(date)}",
                Toast.LENGTH_LONG
            ).show()
            if (view.id==R.id.layout_from){
                binding.tvFrom.text=convertLongToDate(date)
            }else{
                binding.tvTO.text=convertLongToDate(date)
            }

        }



    }

}