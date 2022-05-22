package com.admin.budgetcalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.admin.budgetcalculator.databinding.FragmentSplashBinding

class SplashFragment :Fragment() {

    var _binding: FragmentSplashBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.loginButton.setOnClickListener {
           findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
       }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_signUpFragment)
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}