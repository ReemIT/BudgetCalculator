package com.admin.budgetcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.admin.budgetcalculator.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LoginFragment : Fragment() {

    var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.loginButton.setOnClickListener {
            if (validate()) {
                val validationPair = SharedPreferencesServices.login(
                    requireActivity(),
                    binding.emailEt.text.toString(),
                    binding.passwordEt.text.toString()
                )
                if (!validationPair.first) binding.emailEt.error = "wrong Email !"
                if (!validationPair.second) binding.passwordEt.error = "wrong Password !"
                if (validationPair.first && validationPair.second) findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
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