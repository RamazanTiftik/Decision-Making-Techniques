package com.ramazantiftik.decisionmakingtechniques.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.Company
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.CompanyData

@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(company: Company)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyData(companyData: CompanyData)

    @Delete
    suspend fun deleteCompany(company: Company)

    @Query("DELETE FROM CompanyData WHERE companyName= :companyName")
    suspend fun deleteCompanyData(companyName: String)

    @Query("SELECT * FROM Company")
    fun getCompanies(): LiveData<List<Company>>

    @Query("SELECT * FROM CompanyData")
    fun getCompanyDatas(): LiveData<List<CompanyData>>

    @Query("SELECT * FROM CompanyData WHERE companyName= :companyName")
    fun getDatasToCompany(companyName: String) : LiveData<List<CompanyData>>


}