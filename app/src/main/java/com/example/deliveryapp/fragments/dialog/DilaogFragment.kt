package com.example.deliveryapp.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.DilaogfragmentBinding


class DilaogFragment : DialogFragment() {

    private lateinit var binding: DilaogfragmentBinding


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


}