package com.example.ventazapas.ui.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ventazapas.R
import com.example.ventazapas.data.fireStore.FireStoreImp
import com.example.ventazapas.databinding.FragmentAddShoesBinding
import com.example.ventazapas.utils.Globals.OBJECT_USER

class AddShoesFragment : Fragment() {


    val prueba = FireStoreImp()

    private lateinit var binding: FragmentAddShoesBinding
    private val list = listOf<String>("Masculino", "Femenino", "Unisex")
    private var temp = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddShoesBinding.inflate(inflater, container, false)

        binding.gender.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            list
        )
        binding.gender.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    temp = p0?.getItemAtPosition(p2).toString()!!
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

        binding.btRegister.setOnClickListener {
            prueba.addShoes(
                createCode(),
                binding.color.text.toString(),
                binding.description.text.toString(),
                "",
                temp,
                binding.group.text.toString(),
                createId().toString(),
                listOf(),
                binding.etName.text.toString(),
                0,
                binding.price.text.toString().toInt(),
                false,
                binding.waist.text.toString()
            )
            clear()
            message()
        }
        binding.btCancel.setOnClickListener {
            clear()
        }



        return binding.root
    }

    fun createCode(): String {
        val subName = binding.etName.text.substring(0..2)
        val waist = binding.waist.text.toString()
        val subColor = binding.color.text.substring(0..2)
        val subGroup = binding.group.text.substring(0..2)
        val code = subName + waist + subColor + subGroup
        return code
    }
    fun createId(): Int {
        prueba.addUser(OBJECT_USER.email,
            OBJECT_USER.debt,
            OBJECT_USER.direction,
            OBJECT_USER.dni,
            OBJECT_USER.email,
            OBJECT_USER.favorite,
            OBJECT_USER.id_edit +1,
            OBJECT_USER.name,
            OBJECT_USER.number,
            OBJECT_USER.orders,
            OBJECT_USER.shopping,
            OBJECT_USER.state_account,
            OBJECT_USER.type)

        prueba.getUser(OBJECT_USER.email).observe(this,{
            OBJECT_USER.id_edit = it.id_edit
        })
        return OBJECT_USER.id_edit
    }

    fun clear(){
        binding.etName.text.clear()
        binding.color.text.clear()
        binding.description.text.clear()
        binding.group.text.clear()
        binding.price.text.clear()
        binding.waist.text.clear()
    }
    fun message(){
        Toast.makeText(context,"Calzado agregado exitosamente",Toast.LENGTH_LONG).show()
    }
}