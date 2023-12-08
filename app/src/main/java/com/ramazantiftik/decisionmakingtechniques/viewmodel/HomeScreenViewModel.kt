package com.ramazantiftik.decisionmakingtechniques.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.Company
import com.ramazantiftik.decisionmakingtechniques.repo.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: CompanyRepository,
    application: Application
) : BaseViewModel(application) {

    lateinit var companyList: LiveData<List<Company>>

    fun getCompaniesFromDB(): LiveData<List<Company>>{
        viewModelScope.launch {
            companyList=repository.getCompanies()
        }
        return companyList
    }

    fun deleteCompany(company: Company){
        viewModelScope.launch {
            repository.deleteCompany(company)
        }
    }

    fun  deleteCompanyData(companyName: String){
        viewModelScope.launch {
            repository.deleteCompanyData(companyName)
        }
    }

}