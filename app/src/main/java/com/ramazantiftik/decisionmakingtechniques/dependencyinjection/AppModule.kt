package com.ramazantiftik.decisionmakingtechniques.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.ramazantiftik.decisionmakingtechniques.roomdatabase.CompanyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, CompanyDatabase::class.java,"CompanyDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database: CompanyDatabase) = database.companyDao()

}