package edu.iesam.valoracionesmanga.features.manga.presentation.staffAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.valoracionesmanga.databinding.ViewStaffItemBinding
import edu.iesam.valoracionesmanga.features.manga.domain.Staff

class StaffViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewStaffItemBinding

    fun bind(model: Staff) {
        binding = ViewStaffItemBinding.bind(view)

        binding.staff.text = model.name
        binding.function.text = model.function
    }

}