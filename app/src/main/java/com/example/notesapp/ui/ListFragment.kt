package com.example.notesapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.adapter.NoteAdapter
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.roomdb.R
import com.example.roomdb.databinding.FragmentList2Binding
import dagger.hilt.android.AndroidEntryPoint
import java.io.LineNumberReader


@AndroidEntryPoint
class ListFragment : Fragment() {


    private val viewmodel: NoteViewModel by viewModels()
    private lateinit var mAdapter: NoteAdapter

    private lateinit var binding: FragmentList2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentList2Binding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        mAdapter = NoteAdapter(viewmodel)
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)


        viewmodel.readAllNotes.observe(viewLifecycleOwner) { user ->
            mAdapter.setData(user);
        }


        setHasOptionsMenu(true)


        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_all, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete) {
            deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        var builder = AlertDialog.Builder(context)

        builder.setTitle("Delete Everything")
        builder.setMessage("Are you sure you want to delete everything")

        builder.setPositiveButton("Yes") { _, _ ->
            viewmodel.deleteAll()
            viewmodel.resetId()
            Toast.makeText(context, "Removed Everything", Toast.LENGTH_LONG).show()

        }
        builder.setNegativeButton("No") { _, _ ->
            findNavController().navigate(R.id.listFragment)
        }
        builder.create().show();
    }


}