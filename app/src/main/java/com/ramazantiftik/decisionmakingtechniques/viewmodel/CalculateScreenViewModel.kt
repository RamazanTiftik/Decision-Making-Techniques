package com.ramazantiftik.decisionmakingtechniques.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ramazantiftik.decisionmakingtechniques.repo.CompanyRepository
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.CompanyData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculateScreenViewModel @Inject constructor(
    private val repository: CompanyRepository,
    application: Application
) : BaseViewModel(application){

    lateinit var companyDataList: LiveData<List<CompanyData>>

    lateinit var s1List: LiveData<List<Int>>
    lateinit var s2List: LiveData<List<Int>>
    lateinit var s3List: LiveData<List<Int>>
    lateinit var s4List: LiveData<List<Int>>


    fun getCompanyDataFromDB(): LiveData<List<CompanyData>> {
        viewModelScope.launch {
            companyDataList=repository.getCompanyDatas()
        }
        return companyDataList
    }



    // funcs for pismanlik
    fun getS1() : LiveData<List<Int>>{
        viewModelScope.launch {
            s1List=repository.getS1()
        }
        return s1List
    }

    fun getS2() : LiveData<List<Int>>{
        viewModelScope.launch {
            s2List=repository.getS2()
        }
        return s2List
    }

    fun getS3() : LiveData<List<Int>>{
        viewModelScope.launch {
            s3List=repository.getS3()
        }
        return s3List
    }

    fun getS4() : LiveData<List<Int>>{
        viewModelScope.launch {
            s4List=repository.getS4()
        }
        return s4List
    }

}