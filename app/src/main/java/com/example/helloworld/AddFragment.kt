package com.example.helloworld

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.helloworld.R
import com.example.helloworld.data.models.User
import com.example.helloworld.data.viewmodels.UserViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var addFirstName_et: EditText
    private lateinit var addLastName_et: EditText
    private lateinit var age_et: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val add_btn = view.findViewById<Button>(R.id.add_btn)
        addFirstName_et = view.findViewById(R.id.editTextTextPersonName4)
        addLastName_et = view.findViewById(R.id.editTextTextPersonName5)
        age_et = view.findViewById(R.id.editTextTextPersonName6)

        add_btn.setOnClickListener{
            addDataToDatabase()
        }

        return view
    }

    private fun addDataToDatabase() {
        val first_name = addFirstName_et.text.toString()
        val last_name = addLastName_et.text.toString()
        val age = age_et.text.toString().toInt()

        val user = User(0, firstName = first_name, lastName = last_name, age = age)
        mUserViewModel.addUser(user)

        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}