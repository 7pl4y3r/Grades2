package com.apps.a7pl4y3r.grades.objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apps.a7pl4y3r.grades.room.subjectDbName

@Entity(tableName = subjectDbName)
data class Subject(var title: String, var count: String) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

}