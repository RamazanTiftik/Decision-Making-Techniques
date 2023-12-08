package com.ramazantiftik.decisionmakingtechniques.roomdatabase.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.Company
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities.CompanyData

@Entity
data class CompanyWithDatas(
    @Embedded val company: Company,
    @Relation(
        parentColumn = "companyName",
        entityColumn = "companyName"
    )
    val companyDatas: List<CompanyData>
)