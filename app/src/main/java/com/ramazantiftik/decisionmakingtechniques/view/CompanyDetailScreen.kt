package com.ramazantiftik.decisionmakingtechniques.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ramazantiftik.decisionmakingtechniques.databinding.FragmentCompanyDetailScreenBinding
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.CompanyData
import com.ramazantiftik.decisionmakingtechniques.viewmodel.CompanyDetailScreenViewModel


class CompanyDetailScreen : Fragment() {

    //viewBinding
    private lateinit var _binding: FragmentCompanyDetailScreenBinding
    private val binding get() = _binding!!

    //viewModel
    private lateinit var viewModel: CompanyDetailScreenViewModel

    private lateinit var companyDataList: LiveData<List<CompanyData>>

    //get companyName for search to data
    private var companyName=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentCompanyDetailScreenBinding.inflate(inflater,container,false)
        val view=binding.root

        //viewModel initialize
        viewModel= ViewModelProvider(requireActivity()).get(CompanyDetailScreenViewModel::class.java)

        //argument from nav_graph -> navigation
        arguments?.let {
            companyName=CompanyDetailScreenArgs.fromBundle(it).companyName
        }

        companyDataList=viewModel.getDataToCompany(companyName)

        companyDataList.observe(viewLifecycleOwner, Observer {

            binding.state1TxtDetailScreeb.text="${it[0].s1}"
            binding.state2TxtDetailScreeb.text="${it[0].s2}"
            binding.state3TxtDetailScreeb.text="${it[0].s3}"
            binding.state4TxtDetailScreeb.text="${it[0].s4}"

        })

        // Inflate the layout for this fragment
        return view
    }


}