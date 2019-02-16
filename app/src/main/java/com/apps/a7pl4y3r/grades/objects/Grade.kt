package com.apps.a7pl4y3r.grades.objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apps.a7pl4y3r.grades.room.gradeDbName

@Entity(tableName = gradeDbName)
data class Grade(var grade: String) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

}