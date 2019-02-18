package com.apps.a7pl4y3r.grades.daos


import android.database.Cursor
import androidx.room.*
import com.apps.a7pl4y3r.grades.objects.Grade


@Dao
interface GradeDao {

    @Insert
    fun insert(grade: Grade)

    @Update
    fun update(grade: Grade)

    @Delete
    fun delete(grade: Grade)

    @Query("SELECT * FROM `grades.db` ORDER BY id ASC")
    fun getGrades(): Cursor

}