package com.example.shiftlab2022.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.shiftlab2022.R
import com.example.shiftlab2022.databinding.FragmentSignupBinding
import com.example.shiftlab2022.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
	lateinit var bindingClass : FragmentWelcomeBinding

	companion object {
		const val userNameKey = "USER_NAME"
		const val userFirstNameKey = "USER_FIRST_NAME"
	}
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		bindingClass = FragmentWelcomeBinding.inflate(inflater, container, false)
		return bindingClass.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bindingClass.btnWelcome.setOnClickListener {
			val name = arguments?.getString(userNameKey)
			val firstName = arguments?.getString(userFirstNameKey)
			createWelcomeDialog(name, firstName)
		}
	}

	private fun createWelcomeDialog(name: String?, firstName: String?){
		val builder = AlertDialog.Builder(requireContext())
		builder.setTitle("Приветствие")

		builder.setMessage("Привет, $name $firstName!!" +
							   "Поздравляем с успешной регистрацией!")
		builder.setNegativeButton("Спасибо!"){dialog, i-> }
		builder.show()
	}
}