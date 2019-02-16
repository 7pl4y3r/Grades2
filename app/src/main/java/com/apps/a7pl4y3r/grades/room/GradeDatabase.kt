package com.apps.a7pl4y3r.grades.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apps.a7pl4y3r.grades.daos.GradeDao
import com.apps.a7pl4y3r.grades.objects.Grade

@Database(entities = [Grade::class], version = 1)
abstract class GradeDatabase : RoomDatabase() {

    abstract fun gradeDao(): GradeDao

    companion object {

        private var instance: GradeDatabase? = null

        fun getInstance(context: Context): GradeDatabase {

            if (instance == null) {

                synchronized(GradeDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GradeDatabase::class.java,
                        gradeDbName

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