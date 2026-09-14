package com.phuy.superff.di

import android.content.Context
import androidx.room.Room
import com.phuy.superff.core.shizuku.ShizukuManager
import com.phuy.superff.data.AppDatabase
import com.phuy.superff.data.LogRepository
import com.phuy.superff.data.ProfileRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShizuku(@ApplicationContext c: Context): ShizukuManager = ShizukuManager(c)

    @Provides
    @Singleton
    fun provideProfile(@ApplicationContext c: Context): ProfileRepo = ProfileRepo(c)

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext c: Context): AppDatabase =
        Room.databaseBuilder(c, AppDatabase::class.java, "phuy_log.db").build()

    @Provides
    fun provideDao(db: AppDatabase) = db.logDao()

    @Provides
    @Singleton
    fun provideLogRepo(dao: com.phuy.superff.data.LogDao): LogRepository = LogRepository(dao)
}
