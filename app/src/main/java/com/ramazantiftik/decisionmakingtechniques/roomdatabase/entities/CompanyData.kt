package com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyData(

    @PrimaryKey(autoGenerate = true)
    val id: Int ?= null,

    val companyName: String,

    //states
    val s1: Int,
    val s2: Int,
    val s3: Int,
    val s4: Int,

    //for calculates
    val min: Int,
    val max: Int,
    val total: Int,

)