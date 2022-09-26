package com.example.shiftlab2022.screens

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shiftlab2022.MainActivity
import com.example.shiftlab2022.R
import com.example.shiftlab2022.databinding.FragmentSignupBinding
import java.util.Calendar
import javax.xml.datatype.DatatypeConstants.MONTHS

class SignUpFragment : Fragment() {
	lateinit var bindingClass : FragmentSignupBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		bindingClass = FragmentSignupBinding.inflate(inflater, container, false)
		return bindingClass.root
	}

	@RequiresApi(Build.VERSION_CODES.N)
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bindingClass.btnRegister.setOnClickListener {
			validateCheck()
		}

		bindingClass.data.setOnClickListener {
			val calendar = Calendar.getInstance()
			val day = calendar.get(Calendar.DAY_OF_MONTH)
			val month = calendar.get(Calendar.MONTH)
			val year = calendar.get(Calendar.YEAR)
			val datePickerDialog = DatePickerDialog(
				requireContext(),
				DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
					bindingClass.data.setText("$day.${month+1}.$year")
				},year, month,day)
			datePickerDialog.show()
		}

	}

	private fun validateCheck(){

		when {
			bindingClass.name.text.toString().length <= 2 || bindingClass.name.text.toString().length > 20 -> {
				bindingClass.name.error = "Имя должно содержать от 3 до 20 букв"
				return
			}
			bindingClass.name.text.toString().contains(".[А-Я]".toRegex()) -> {
				bindingClass.name.error = "Только первая буква имени должна быть заглавной"
				return
			}
			bindingClass.name.text.toString().contains(" ".toRegex()) -> {
				bindingClass.name.error = "Имя не должно содержать пробелов"
				return
			}
			!bindingClass.name.text.toString().matches(".[а-я]{1,20}".toRegex()) -> {
				bindingClass.name.error= "Имя должно содержать только кириллицу"
				return
			}
			bindingClass.name.text.toString().matches("[^А-Я]+".toRegex())-> {
				bindingClass.name.error= "Имя должно начаться с заглавной буквы"
				return
			}

			bindingClass.firstname.text.toString().length <= 2 || bindingClass.name.text.toString().length > 30 -> {
				bindingClass.firstname.error = "Фамилия должна содержать от 3 до 30 букв"
				return
			}
			bindingClass.firstname.text.toString().contains(".[А-Я]".toRegex()) -> {
				bindingClass.firstname.error = "Только первая буква фамилии должна быть заглавной"
				return
			}
			bindingClass.firstname.text.toString().contains(" ".toRegex()) -> {
				bindingClass.firstname.error = "Фамилия не должна содержать пробелов"
				return
			}
			!bindingClass.firstname.text.toString().matches(".[а-я]{1,30}".toRegex()) -> {
				bindingClass.firstname.error = "Фамилия должна содержать только кириллицу"
				return
			}
			bindingClass.firstname.text.toString().matches("[^А-Я]+".toRegex())-> {
				bindingClass.firstname.error = "Фамилия должна начаться с заглавной буквы"
				return
			}

			bindingClass.data.text.toString() == "" -> {
				bindingClass.data.error = "Выберите дату рождения"
				return
			}

			bindingClass.password.text.toString().length < 8 || bindingClass.name.text.toString().length > 20 -> {
				bindingClass.password.error = "Пароль должен содержать от 8 до 20 символов"
				return
			}
			!bindingClass.password.text.toString().contains("[0-9]".toRegex()) -> {
				bindingClass.password.error = "Пароль должен содержать хотя бы одну цифру"
				return
			}
			!bindingClass.password.text.toString().contains("[А-ЯA-Z]".toRegex()) -> {
				bindingClass.password.error = "Пароль должен содержать заглавную букву"
				return
			}
			bindingClass.password.text.toString() != bindingClass.repassword.text.toString() -> {
				bindingClass.repassword.error = "Пароли не совпадают!"
				return
			}
		}
		findNavController().navigate(
			R.id.action_signUpFragment_to_welcomeFragment,
			bundleOf(WelcomeFragment.userNameKey to bindingClass.name.text.toString(), WelcomeFragment.userFirstNameKey to bindingClass.firstname.text.toString() )
		)
	}
}