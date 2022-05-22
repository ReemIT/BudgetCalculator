package com.admin.budgetcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.admin.budgetcalculator.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    var _binding: FragmentSignUpBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            if (validate()) SharedPreferencesServices.signup(
                requireActivity(),
                binding.emailEt.text.toString(),
                binding.passwordEt.text.toString()
            )
            findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
        }

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

    }

    fun validate(): Boolean {
        val emailValidation = !binding.emailEt.text.isNullOrEmpty()
        val passValidation = !binding.passwordEt.text.isNullOrEmpty()
        if (!emailValidation) binding.emailEt.error = "You must type your email !"
        if (!passValidation) binding.passwordEt.error = " you must type a password !"
        return emailValidation && passValidation
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}