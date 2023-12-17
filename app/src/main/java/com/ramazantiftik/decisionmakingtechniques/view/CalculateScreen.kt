package com.ramazantiftik.decisionmakingtechniques.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ramazantiftik.decisionmakingtechniques.databinding.FragmentCalculateScreenBinding
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.CompanyData
import com.ramazantiftik.decisionmakingtechniques.viewmodel.CalculateScreenViewModel


class CalculateScreen : Fragment() {

    //for company's all data
    private lateinit var companyDataList: LiveData<List<CompanyData>>

    //alfa for hurwicz
    private var alfa=0.0
    private var alfa2=1-alfa


    //temp values for positive btn -> getiri btn
    //iyimserlik
    private var maxValueForIyimserlikPositive=0
    private var maxValueCompanyNameForIyimserlikPositive: String = ""
    //kotumserlik
    private var maxValueForKotumserlikPositive=0
    private var maxValueCompanyNameForKotumserlikPositive: String = ""
    //es olasilik
    private var maxValueForEsOlasilikPositive=0.0
    private var maxValueCompanyNameForEsOlasilikPositive: String = ""
    //Hurwicz
    private var maxValueForHurwiczPositive=0.0
    private var maxValueCompanyNameForHurwiczPositive: String = ""



    //temp values for negative btn -> maliyet btn
    //iyimserlik
    private var minValueForIyimserlikNegative=0
    private var minValueCompanyNameForIyimserlikNegative: String = ""
    //kotumserlik
    private var minValueForKotumserlikNegative=0
    private var minValueCompanyNameForKotumserlikNegative: String = ""
    //es olasilik
    private var minValueForEsOlasilikNegative=0.0
    private var minValueCompanyNameForEsOlasilikNegative: String = ""
    //Hurwicz
    private var minValueForHurwiczNegative=0.0
    private var minValueCompanyNameForHurwiczNegative: String = ""


    //viewBinding
    private lateinit var _binding: FragmentCalculateScreenBinding
    private val binding get()= _binding!!

    //viewModel
    private lateinit var viewModel: CalculateScreenViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewBinding
        _binding= FragmentCalculateScreenBinding.inflate(layoutInflater,container,false)
        val view=binding.root

        //viewModel initialize
        viewModel=ViewModelProvider(requireActivity()).get(CalculateScreenViewModel::class.java)


        //********** GETIRI BTN FUNC **********
        binding.positiveBtnCalSc.setOnClickListener {

            //get companyData from viewModel and observe
            companyDataList=viewModel.getCompanyDataFromDB()
            companyDataList.observe(viewLifecycleOwner, Observer {

                //first company is always start with
                //iyimserlik
                maxValueForIyimserlikPositive=it[0].max
                maxValueCompanyNameForIyimserlikPositive=it[0].companyName

                //kotumserlik
                maxValueForKotumserlikPositive=it[0].min
                maxValueCompanyNameForKotumserlikPositive=it[0].companyName

                //es-olasilik
                maxValueForEsOlasilikPositive=it[0].total/4.0
                maxValueCompanyNameForEsOlasilikPositive=it[0].companyName

                //*****HURWICZ*****
                try {
                    alfa=binding.alfaValueEdtTxtCalculateSc.text.toString().toDouble()
                } catch (e: Exception){
                    Toast.makeText(requireContext(),"Alfa değerini doğru formatta girin !",Toast.LENGTH_LONG).show()
                }

                if (alfa==0.0){
                    //do nothing
                } else {
                    alfa2=1-alfa
                    maxValueForHurwiczPositive=(it[0].max*alfa)+(it[0].min*alfa2)
                    maxValueCompanyNameForHurwiczPositive=it[0].companyName
                }


                it.forEach {

                    //*****IYIMSERLIK*****

                    if (maxValueForIyimserlikPositive<it.max){
                        maxValueForIyimserlikPositive=it.max
                        maxValueCompanyNameForIyimserlikPositive=it.companyName
                    }


                    //*****KOTUMSERLIK*****

                    if (maxValueForKotumserlikPositive<it.min){
                        maxValueForKotumserlikPositive=it.min
                        maxValueCompanyNameForKotumserlikPositive=it.companyName
                    }


                    //*****ES OLASILIK*****

                    if (maxValueForEsOlasilikPositive<it.total/4.0){
                        maxValueForEsOlasilikPositive=it.total/4.0
                        maxValueCompanyNameForEsOlasilikPositive=it.companyName
                    }


                    //*****HURWICZ*****

                    if (alfa==0.0){
                        //do nothing
                    } else{
                        if (maxValueForHurwiczPositive<(it.max*alfa)+(it.min*alfa2)){
                            maxValueForHurwiczPositive=(it.max*alfa)+(it.min*alfa2)
                            maxValueCompanyNameForHurwiczPositive=it.companyName
                        }

                    }


                }


                //*****IYIMSERLIK*****
                binding.iyimserlikValueTxtCalculateSc2.text="$maxValueCompanyNameForIyimserlikPositive"+" ($maxValueForIyimserlikPositive)"

                //*****KOTUMSERLIK*****
                binding.kotumserlikValueTxtCalculateSc2.text="$maxValueCompanyNameForKotumserlikPositive"+" ($maxValueForKotumserlikPositive)"

                //*****ES-OLASILIK*****
                binding.esOlasilikValueTxtCalculateSc2.text="$maxValueCompanyNameForEsOlasilikPositive"+" ($maxValueForEsOlasilikPositive)"

                //*****HURWICZ*****
                binding.hurwiczValueTxtCalculateSc2.text="$maxValueCompanyNameForHurwiczPositive"+" ($maxValueForHurwiczPositive)"

            })

        }


        //********** MALIYET BTN FUNC **********
        binding.negativeBtnCalSc.setOnClickListener {

            //get companyData from viewModel and observe
            companyDataList=viewModel.getCompanyDataFromDB()
            companyDataList.observe(viewLifecycleOwner, Observer {

                //first company is always start with
                //iyimserlik
                minValueForIyimserlikNegative=it[0].min
                minValueCompanyNameForIyimserlikNegative=it[0].companyName

                //kotumserlik
                minValueForKotumserlikNegative=it[0].max
                minValueCompanyNameForKotumserlikNegative=it[0].companyName

                //es-olasilik
                minValueForEsOlasilikNegative=it[0].total/4.0
                minValueCompanyNameForEsOlasilikNegative=it[0].companyName

                //*****HURWICZ*****
                try {
                    alfa=binding.alfaValueEdtTxtCalculateSc.text.toString().toDouble()
                } catch (e: Exception){
                    Toast.makeText(requireContext(),"Alfa değerini doğru formatta girin !",Toast.LENGTH_LONG).show()
                }

                if (alfa==0.0){

                } else {
                    alfa2 = 1 - alfa
                    minValueForHurwiczNegative = (it[0].min * alfa) + (it[0].max * alfa2)
                    minValueCompanyNameForHurwiczNegative = it[0].companyName
                }



                it.forEach {

                    //*****IYIMSERLIK*****

                    if (minValueForIyimserlikNegative>it.min){
                        minValueForIyimserlikNegative=it.min
                        minValueCompanyNameForIyimserlikNegative=it.companyName
                    }


                    //*****KOTUMSERLIK*****

                    if (minValueForKotumserlikNegative>it.max){
                        minValueForKotumserlikNegative=it.max
                        minValueCompanyNameForKotumserlikNegative=it.companyName
                    }


                    //*****ES OLASILIK*****

                    if (minValueForEsOlasilikNegative>it.total/4.0){
                        minValueForEsOlasilikNegative=it.total/4.0
                        minValueCompanyNameForEsOlasilikNegative=it.companyName
                    }


                    //*****HURWICZ*****

                    if (alfa==0.0){

                    } else{

                        if (minValueForHurwiczNegative>(it.min*alfa)+(it.max*alfa2)){
                            minValueForHurwiczNegative=(it.min*alfa)+(it.max*alfa2)
                            minValueCompanyNameForHurwiczNegative=it.companyName
                        }

                    }


                }

                //*****IYIMSERLIK*****
                binding.iyimserlikValueTxtCalculateSc2.text="$minValueCompanyNameForIyimserlikNegative"+" ($minValueForIyimserlikNegative)"

                //*****KOTUMSERLIK*****
                binding.kotumserlikValueTxtCalculateSc2.text="$minValueCompanyNameForKotumserlikNegative"+" ($minValueForKotumserlikNegative)"

                //*****ES-OLASILIK*****
                binding.esOlasilikValueTxtCalculateSc2.text="$minValueCompanyNameForEsOlasilikNegative"+" ($minValueForEsOlasilikNegative)"

                //*****HURWICZ*****
                binding.hurwiczValueTxtCalculateSc2.text="$minValueCompanyNameForHurwiczNegative"+" ($minValueForHurwiczNegative)"

            })


        }


        // Inflate the layout for this fragment
        return view
    }


}