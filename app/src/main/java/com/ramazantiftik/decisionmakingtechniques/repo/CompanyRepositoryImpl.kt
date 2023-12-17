package com.ramazantiftik.decisionmakingtechniques.repo

import androidx.lifecycle.LiveData
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.Company
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.CompanyData

interface CompanyRepositoryImpl {

    suspend fun insertCompany(company: Company)

    suspend fun insertCompanyData(companyData: CompanyData)

    suspend fun deleteCompany(company: Company)

    fun getCompanies(): LiveData<List<Company>>

    fun getCompanyDatas(): LiveData<List<CompanyData>>

    fun getDatasToCompany(companyName: String) : LiveData<List<CompanyData>>

    suspend fun deleteCompanyData(companyName: String)

}