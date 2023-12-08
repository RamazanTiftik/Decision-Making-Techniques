package com.ramazantiftik.decisionmakingtechniques.repo

import androidx.lifecycle.LiveData
import androidx.room.Query
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

    fun getS1() : LiveData<List<Int>>

    fun getS2() : LiveData<List<Int>>

    fun getS3() : LiveData<List<Int>>

    fun getS4() : LiveData<List<Int>>

}