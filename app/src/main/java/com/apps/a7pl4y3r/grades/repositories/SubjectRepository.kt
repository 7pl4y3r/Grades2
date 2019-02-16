package com.apps.a7pl4y3r.grades.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.apps.a7pl4y3r.grades.daos.SubjectDao
import com.apps.a7pl4y3r.grades.objects.Subject
import com.apps.a7pl4y3r.grades.room.SubjectDatabase

private const val insertOperation = 1
private const val updateOperation = 2
private const val deleteOperation = 3

class SubjectRepository(application: Application) {


    private var subjectDao: SubjectDao
    private var subjects: LiveData<List<Subject>>

    init {
        val db = SubjectDatabase.getInstance(application)
        subjectDao = db.subjectDao()
        subjects = subjectDao.getSubjects()
    }

    fun insert(subject: Subject) {
        DbOperation(subjectDao, insertOperation).execute(subject)
    }

    fun update(subject: Subject) {
        DbOperation(subjectDao, updateOperation).execute(subject)
    }

    fun delete(subject: Subject) {
        DbOperation(subjectDao, deleteOperation).execute(subject)
    }

    fun getSubjects(): LiveData<List<Subject>> {
        return subjects
    }


    companion object {

        private class DbOperation(private val dao: SubjectDao, private val operationId: Int) :
            AsyncTask<Subject, Void, Void>() {

            override fun doInBackground(vararg params: Subject): Void? {

                when (operationId) {

                    insertOperation -> dao.insert(params[0])
                    updateOperation -> dao.update(params[0])
                    deleteOperation -> dao.delete(params[0])

                }

                return null
            }
        }

    }

}