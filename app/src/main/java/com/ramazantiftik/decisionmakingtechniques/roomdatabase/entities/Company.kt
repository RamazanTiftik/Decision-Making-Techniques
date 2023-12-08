package com.ramazantiftik.decisionmakingtechniques.roomdatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Company(
    @PrimaryKey(autoGenerate = false) val companyName: String
)