package com.example.deliveryapp.fragments.dialog

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
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
import com.example.deliveryapp.viewmodel.SharedDataViewmodel
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


class DilaogFragment : DialogFragment() {

    private lateinit var binding: DilaogfragmentBinding
    private val args: DilaogFragmentArgs by navArgs()
    val sharedDataViewmodel: SharedDataViewmodel by activityViewModels()
    private var lang = "EN"


    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DilaogfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeViews(args.choose)
        initButton()
        chooseLanuage()
    }

    private fun initButton() {

        binding.layoutBtnFrom.setOnClickListener {
            showDatepicker(it)
        }
        binding.layoutBtnTo.setOnClickListener {
            showDatepicker(it)
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnApplyDate.setOnClickListener {
            sharedDataViewmodel.getDate(
                OrderBYDate(
                    binding.tvFrom.text.toString(),
                    binding.tvTO.text.toString()
                )
            )
            dismiss()
        }

        binding.btnApply.setOnClickListener {
            setLocale(lang)
            requireActivity().finish()
            startActivity(requireActivity().intent)
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
            if (view.id == R.id.layout_btn_from) {
                binding.tvFrom.text = convertLongToDate(date)
            } else {
                binding.tvTO.text = convertLongToDate(date)
            }

        }


    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    @Suppress("deprecation")
    private fun updateResourcesLegacy(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

    fun setLocale(lang: String): Context? {
        return if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            updateResources(
                requireContext(),
                lang
            )
        } else updateResourcesLegacy(
            requireContext(),
            lang
        )
    }

    private fun chooseLanuage() {

        binding.layoutBtnArabic.setOnClickListener {
            sharedDataViewmodel.getLanguageNo("1")
            lang = "Ar"

        }
        binding.layoutBtnEnglish.setOnClickListener {
            sharedDataViewmodel.getLanguageNo("2")
            lang = "En"

        }


    }

}