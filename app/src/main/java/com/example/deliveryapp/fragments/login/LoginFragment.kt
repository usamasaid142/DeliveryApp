package com.example.deliveryapp.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.deliveryapp.R
import com.example.deliveryapp.api.ResultEndPoint
import com.example.deliveryapp.databinding.LoginfragmentBinding
import com.example.deliveryapp.model.LoginDelivery
import com.example.deliveryapp.model.LoginDeliveryRequest
import com.example.deliveryapp.model.LoginResponse
import com.example.deliveryapp.utils.Resource
import com.example.deliveryapp.utils.displayToastText
import com.example.deliveryapp.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

   private lateinit var binding:LoginfragmentBinding
   private val viewmodel:RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=LoginfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callBcakLogin()
        initButton()
    }

    private fun initButton(){

        binding.btnLogin.setOnClickListener {

            if (!binding.etUserId.text.isNullOrEmpty() && !binding.etPassword.text.isNullOrEmpty()){
                viewmodel.login(LoginDeliveryRequest(LoginDelivery(binding.etUserId.text.toString(),"1",binding.etPassword.text.toString())))
            }else{
                requireContext().displayToastText("please enter userId and password")
            }
        }

        binding.ivLang.setOnClickListener {
            findNavController().navigate(R.id.dilaogFragment)
        }

    }

    private fun callBcakLogin(){
        viewmodel.loginResponse.observe(viewLifecycleOwner, Observer {loginresponse->
            when(loginresponse){

                is Resource.Loading->{
                    binding.progressBar.visibility=View.VISIBLE
                }

                is Resource.sucess->{
                    binding.progressBar.visibility=View.GONE
                    if (loginresponse.data?.data?.deliveryName==null){
                        Toast.makeText(requireContext(), "${loginresponse.data?.result?.errMsg}", Toast.LENGTH_SHORT).show()
                    }else {
                        val action = LoginFragmentDirections.actionLoginFragmentToOrdersFragment()
                        findNavController().navigate(action)
                        Toast.makeText(
                            requireContext(),
                            " your are login Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                is Resource.Error->{
                    binding.progressBar.visibility=View.GONE
                    Snackbar.make(requireView(), "${loginresponse.data?.result?.errMsg}", Snackbar.LENGTH_SHORT).show()
                }
            }

        })





    }


}