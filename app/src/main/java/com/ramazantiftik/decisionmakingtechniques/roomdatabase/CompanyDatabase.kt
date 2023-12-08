package com.ramazantiftik.decisionmakingtechniques.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.Company
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.CompanyData

@Database(
    entities = [
        Company::class,
        CompanyData::class
    ],
    version = 1
)
abstract class CompanyDatabase : RoomDatabase() {
    abstract fun companyDao() : CompanyDao
}