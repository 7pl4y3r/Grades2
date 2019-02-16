package com.apps.a7pl4y3r.grades.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.apps.a7pl4y3r.grades.objects.Subject
import com.apps.a7pl4y3r.grades.repositories.SubjectRepository

class SubjectViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SubjectRepository(application)
    private val subjects = repository.getSubjects()

    fun insert(subject: Subject) {
        repository.insert(subject)
    }

    fun update(subject: Subject) {
        repository.update(subject)
    }

    fun delete(subject: Subject) {
        repository.delete(subject)
    }

    fun getSubjects(): LiveData<List<Subject>> {
        return subjects
    }

}