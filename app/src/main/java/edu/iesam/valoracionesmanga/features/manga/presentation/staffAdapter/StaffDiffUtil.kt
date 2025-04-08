package edu.iesam.valoracionesmanga.features.manga.presentation.staffAdapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.valoracionesmanga.features.manga.domain.Staff

class StaffDiffUtil: DiffUtil.ItemCallback<Staff>() {
    override fun areContentsTheSame(oldItem: Staff, newItem: Staff): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areItemsTheSame(oldItem: Staff, newItem: Staff): Boolean {
        return oldItem == newItem
    }
}