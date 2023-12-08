package com.ramazantiftik.decisionmakingtechniques.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.Company
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.CompanyData
import com.ramazantiftik.decisionmakingtechniques.repo.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewCompanyScreenViewModel @Inject constructor(
    private val repository: CompanyRepository,
    application: Application
) : BaseViewModel(application) {

    fun insertCompany(company: Company) = viewModelScope.launch {
        repository.insertCompany(company)
    }

    fun insertCompanyData(companyData: CompanyData) = viewModelScope.launch {
        repository.insertCompanyData(companyData)
    }

}