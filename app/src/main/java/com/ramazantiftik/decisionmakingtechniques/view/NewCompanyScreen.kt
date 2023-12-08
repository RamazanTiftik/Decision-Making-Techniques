
package com.ramazantiftik.decisionmakingtechniques.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ramazantiftik.decisionmakingtechniques.databinding.FragmentNewCompanyScreenBinding
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.Company
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.CompanyData
import com.ramazantiftik.decisionmakingtechniques.viewmodel.NewCompanyScreenViewModel


class NewCompanyScreen : Fragment() {

    //viewModel
    lateinit var viewModel: NewCompanyScreenViewModel

    //temp value
    private var maxState=0
    private var minState=0
    private var totalState=0

    //viewBinding
    private lateinit var _binding: FragmentNewCompanyScreenBinding
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewBinding
        _binding= FragmentNewCompanyScreenBinding.inflate(inflater,container,false)
        val view=binding.root

        //viewModel initialize
        viewModel= ViewModelProvider(requireActivity()).get(NewCompanyScreenViewModel::class.java)


        //save btn
        binding.saveBtnNewSc.setOnClickListener {

            //try-catch -> for save code -> number exception -> string/int
            try {

                //company name and data variables -> type conversion
                val companyName=binding.companyTextNewSc.text.toString()
                val s1=binding.stateValue1NewSc.text.toString().toInt()
                val s2=binding.stateValue2NewSc.text.toString().toInt()
                val s3=binding.stateValue3NewSc.text.toString().toInt()
                val s4=binding.stateValue4NewSc.text.toString().toInt()

                //maxState check-in
                maxState=s1
                if(maxState<s2){
                    maxState=s2
                }
                if(maxState<s3){
                    maxState=s3
                }
                if(maxState<s4){
                    maxState=s4
                }

                //minState check-in
                minState=s1
                if (minState>s2){
                    minState=s2
                }
                if (minState>s3){
                    minState=s3
                }
                if (minState>s4){
                    minState=s4
                }

                //totalState for es-olasilik
                totalState=s1+s2+s3+s4


                if(companyName.equals("")){
                    Toast.makeText(requireContext(),"Firma adı boş bırakılamaz !",Toast.LENGTH_LONG).show()
                } else {
                    //insert company func
                    viewModel.insertCompany(Company(companyName))

                    //insert company data func
                    viewModel.insertCompanyData(CompanyData(companyName = companyName, s1 =s1, s2 = s2, s3 = s3, s4 = s4, max = maxState, min = minState, total = totalState ))

                    //pop-up massage for saved
                    Toast.makeText(requireContext(),"Başarılı bir şekilde kaydedildi",Toast.LENGTH_LONG).show()

                    //save after turn to main menu
                    findNavController().popBackStack()
                }


            } catch (e: Exception){
                //pop-up for user mistakes
                Toast.makeText(requireContext(),"Değerleri doğru formatta girin !",Toast.LENGTH_LONG).show()
            }

        }


        // Inflate the layout for this fragment
        return view
    }


}