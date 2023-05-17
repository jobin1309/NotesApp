package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.model.Notes
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.roomdb.R
import com.example.roomdb.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddFragment : Fragment() {

    private val MyviewModel: NoteViewModel by viewModels()

    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAddBinding.inflate(inflater, container, false)



        binding.saveBtn.setOnClickListener {
            insertData()
        }


        return binding.root
    }


    private fun insertData() {

        val titleEdit = binding.titleEdit.text.toString()
        val descriptionEdit = binding.descriptionEdit.text.toString()


        if (titleEdit.isNotEmpty() && descriptionEdit.isNotEmpty()) {

            val notes = Notes(0, titleEdit, descriptionEdit)
            MyviewModel.addNote(notes)


            Toast.makeText(context, "Successfully added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment);
        } else {
            Toast.makeText(context, "Please fill out the fields", Toast.LENGTH_LONG).show()
        }


    }


}