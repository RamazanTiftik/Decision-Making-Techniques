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
class CompanyDetailScreenViewModel @Inject constructor(
    private val repository: CompanyRepository,
    application: Application
) : BaseViewModel(application) {

    private lateinit var companyDataList: LiveData<List<CompanyData>>

    fun getDataToCompany(companyName: String): LiveData<List<CompanyData>> {
        viewModelScope.launch {
            companyDataList=repository.getDatasToCompany(companyName)
        }
        return companyDataList
    }

}