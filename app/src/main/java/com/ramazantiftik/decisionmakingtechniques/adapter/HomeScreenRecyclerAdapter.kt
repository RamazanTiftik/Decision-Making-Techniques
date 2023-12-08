package com.ramazantiftik.decisionmakingtechniques.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ramazantiftik.decisionmakingtechniques.databinding.HomescreenRecyclerRowBinding
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.Company
import com.ramazantiftik.decisionmakingtechniques.view.HomeScreenDirections

class HomeScreenRecyclerAdapter(
    private val companyList: List<Company>
) : RecyclerView.Adapter<HomeScreenRecyclerAdapter.HomeScreenViewHolder>() {

    class HomeScreenViewHolder(val binding: HomescreenRecyclerRowBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenViewHolder {
        val binding=HomescreenRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeScreenViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    override fun onBindViewHolder(holder: HomeScreenViewHolder, position: Int) {

        holder.binding.companyNameTextRow.text=companyList[position].companyName

        holder.binding.companyNameTextRow.setOnClickListener {
            val action=HomeScreenDirections.actionHomeScreenToCompanyDetailScreen(holder.binding.companyNameTextRow.text.toString())
            Navigation.findNavController(it).navigate(action)
        }

    }


}