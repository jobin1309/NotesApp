package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.model.Notes
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.roomdb.R
import com.example.roomdb.databinding.FragmentUpdateBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding;

    private val viewModel: NoteViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentUpdateBinding.inflate(inflater, container, false)


        binding.titleEdit.setText(args.notesData.mainNotes)
        binding.descriptionEdit.setText(args.notesData.description)


        binding.updateBtn.setOnClickListener {
            updateData()
        }

        return binding.root
    }

    private fun updateData() {

        val textEdit = binding.titleEdit.text.toString()
        val descriptionEdit = binding.descriptionEdit.text.toString()

        if (textEdit.isNotEmpty() && descriptionEdit.isNotEmpty()) {
            val notes = Notes(args.notesData.id, textEdit, descriptionEdit)
            viewModel.updateNote(notes)

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(context, "Notes updated", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Please fill out the details", Toast.LENGTH_LONG).show()
        }
    }


}