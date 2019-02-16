package com.apps.a7pl4y3r.grades.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.apps.a7pl4y3r.grades.objects.Subject


@Dao
interface SubjectDao {

    @Insert
    fun insert(subject: Subject)

    @Update
    fun update(subject: Subject)

    @Delete
    fun delete(subject: Subject)

    @Query("SELECT * FROM `subjects.db` ORDER BY title ASC")
    fun getSubjects(): LiveData<List<Subject>>

}