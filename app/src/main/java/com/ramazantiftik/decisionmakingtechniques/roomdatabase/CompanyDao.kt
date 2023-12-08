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

    @Query("SELECT s1 FROM CompanyData")
    fun getS1() : LiveData<List<Int>>

    @Query("SELECT s2 FROM CompanyData")
    fun getS2() : LiveData<List<Int>>

    @Query("SELECT s3 FROM CompanyData")
    fun getS3() : LiveData<List<Int>>

    @Query("SELECT s4 FROM CompanyData")
    fun getS4() : LiveData<List<Int>>

}