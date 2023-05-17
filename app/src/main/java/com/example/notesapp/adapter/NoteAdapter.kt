package com.example.notesapp.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.model.Notes
import com.example.notesapp.ui.ListFragmentDirections
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.roomdb.databinding.RowLayoutBinding
import javax.inject.Inject


class NoteAdapter @Inject constructor(private val mViewModel: NoteViewModel) : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    var list = emptyList<Notes>()

    class MyViewHolder(var binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notes) {
            binding.mainNote.text = item.mainNotes
            binding.description.text = item.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val LayoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(RowLayoutBinding.inflate(LayoutInflater, parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = list[position]
        holder.bind(item)


            holder.binding.deleteBtn.setOnClickListener {
               deleteItem(item)
            }

            holder.binding.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            holder.itemView.findNavController().navigate(action)
        }
    }

    private fun deleteItem(item: Notes) {
        mViewModel.deleteNote(item)
        var position = list.indexOf(item)

        if(position != -1) {
           list =  list.toMutableList().also { it.removeAt(position) }
            notifyItemRemoved(position)
        }


    }

    fun setData(notes: List<Notes>?) {
        if (notes != null) {
            list = notes
        }
        notifyDataSetChanged()
    }
}