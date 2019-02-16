package com.apps.a7pl4y3r.grades.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apps.a7pl4y3r.grades.daos.SubjectDao
import com.apps.a7pl4y3r.grades.objects.Subject

@Database(entities = [Subject::class], version = 1)
abstract class SubjectDatabase : RoomDatabase(){

    abstract fun subjectDao(): SubjectDao

    companion object {

        private var instance: SubjectDatabase? = null

        fun getInstance(context: Context): SubjectDatabase {

            if (instance == null) {

                synchronized(SubjectDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubjectDatabase::class.java,
                        subjectDbName

                    ).fallbackToDestructiveMigration()
                        .build()
                }

            }

            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }

    }

}