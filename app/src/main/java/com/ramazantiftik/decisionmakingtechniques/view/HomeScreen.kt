package com.ramazantiftik.decisionmakingtechniques.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramazantiftik.decisionmakingtechniques.adapter.HomeScreenRecyclerAdapter
import com.ramazantiftik.decisionmakingtechniques.databinding.FragmentHomeScreenBinding
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.Company
import com.ramazantiftik.decisionmakingtechniques.viewmodel.HomeScreenViewModel


class HomeScreen : Fragment() {

    //viewBinding
    private var _binding: FragmentHomeScreenBinding?=null
    private val binding get()= _binding!!

    //recyclerView adapter
    private lateinit var homeScreenAdapter: HomeScreenRecyclerAdapter

    //viewModel
    lateinit var viewModel: HomeScreenViewModel

    //list of companies from db
    private lateinit var companyList: List<Company>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewBinding
        _binding= FragmentHomeScreenBinding.inflate(inflater,container,false)
        val view=binding.root

        //viewModel initialize
        viewModel=ViewModelProvider(requireActivity()).get(HomeScreenViewModel::class.java)

        //swipeCallBack for delete activision
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.companyList)

        //get list from db
        viewModel.getCompaniesFromDB().observe(viewLifecycleOwner, Observer {
            companyList=it

            //recyclerView adapter
            homeScreenAdapter= HomeScreenRecyclerAdapter(companyList)
            binding.companyList.adapter=homeScreenAdapter
            binding.companyList.layoutManager=LinearLayoutManager(requireContext())
        })

        //add new company btn func
        binding.addCompanyBtnHomeSc.setOnClickListener {
            val action=HomeScreenDirections.actionHomeScreenToNewCompanyScreen()
            Navigation.findNavController(it).navigate(action)
        }

        //go to calculate screen btn func
        binding.calculateBtnHomeSc.setOnClickListener {
            val action=HomeScreenDirections.actionHomeScreenToCalculateScreen()
            Navigation.findNavController(it).navigate(action)
        }


        // Inflate the layout for this fragment
        return view
    }


    //swipeCallBack for delete activision
    private val swipeCallBack=object: ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            //AlertMessage
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Seçtiğiniz şirketi silmek istediğinizden emin misiniz ?")
                .setTitle("UYARI")
                .setPositiveButton("Yes") { dialog, which ->
                    val layoutPosition=viewHolder.adapterPosition
                    val selectedCompany=companyList[layoutPosition]
                    viewModel.deleteCompanyData(selectedCompany.companyName)
                    viewModel.deleteCompany(selectedCompany)
                }
                .setNegativeButton("No") { dialog, which ->
                    //do nothing, refresh list
                    homeScreenAdapter.notifyDataSetChanged()
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()


        }

    }



}