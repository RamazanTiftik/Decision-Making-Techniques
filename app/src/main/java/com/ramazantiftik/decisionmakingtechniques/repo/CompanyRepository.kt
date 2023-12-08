package com.ramazantiftik.decisionmakingtechniques.repo

import androidx.lifecycle.LiveData
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.CompanyDao
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.Company
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.CompanyData
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val dao: CompanyDao,
) : CompanyRepositoryImpl {


    override suspend fun insertCompany(company: Company) {
        dao.insertCompany(company)
    }

    override suspend fun insertCompanyData(companyData: CompanyData) {
        dao.insertCompanyData(companyData)
    }

    override suspend fun deleteCompany(company: Company) {
        dao.deleteCompany(company)
    }

    override fun getCompanies(): LiveData<List<Company>> {
        return dao.getCompanies()
    }

    override fun getCompanyDatas(): LiveData<List<CompanyData>> {
        return dao.getCompanyDatas()
    }

    override fun getDatasToCompany(companyName: String): LiveData<List<CompanyData>> {
        return dao.getDatasToCompany(companyName)
    }

    override suspend fun deleteCompanyData(companyName: String) {
        dao.deleteCompanyData(companyName)
    }

    override fun getS1(): LiveData<List<Int>> {
        return dao.getS1()
    }

    override fun getS2(): LiveData<List<Int>> {
        return dao.getS2()
    }

    override fun getS3(): LiveData<List<Int>> {
        return dao.getS3()
    }

    override fun getS4(): LiveData<List<Int>> {
        return dao.getS4()
    }

}