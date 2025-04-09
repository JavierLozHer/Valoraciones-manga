package edu.iesam.valoracionesmanga.features.manga.presentation.staffAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.features.manga.domain.Staff

class StaffAdapter: ListAdapter<Staff, StaffViewHolder>(StaffDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_staff_item, parent, false)
        return StaffViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}