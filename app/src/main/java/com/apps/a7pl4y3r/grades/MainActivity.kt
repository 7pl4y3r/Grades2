package com.apps.a7pl4y3r.grades

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.a7pl4y3r.grades.adapters.SubjectAdapter
import com.apps.a7pl4y3r.grades.objects.Subject
import com.apps.a7pl4y3r.grades.view_models.SubjectViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tag = "Main"

    private lateinit var viewModel: SubjectViewModel

    private var wantsToUpdate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()


        fabMain.setOnClickListener {
            startActivityForResult(Intent(this, AddSubject::class.java), REQUEST_ADD_SUBJECT)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {

                REQUEST_ADD_SUBJECT -> {

                    val title = data?.getStringExtra(EXTRA_SUBJECT_TITLE)
                    val subject = Subject(title!!, "0")

                    viewModel.insert(subject)
                    Toast.makeText(this, "Subject inserted!", Toast.LENGTH_SHORT).show()

                }

                REQUEST_EDIT_SUBJECT -> {

                    val title = data?.getStringExtra(EXTRA_SUBJECT_TITLE)
                    val subject = Subject(title!!, "0")

                    subject.id = data.getIntExtra(EXTRA_SUBJECT_ID, -1)

                    Log.d(tag, "EXTRA_ID = ${subject.id}")

                    viewModel.update(subject)
                    Toast.makeText(this, "Subject updated!", Toast.LENGTH_SHORT).show()

                }

            }

        }

    }

    private fun setRecyclerView() {

        val adapter = SubjectAdapter()

        recyclerViewMain.setHasFixedSize(true)
        recyclerViewMain.layoutManager = LinearLayoutManager(this)
        recyclerViewMain.adapter = adapter


        adapter.setOnItemClickListener(object : SubjectAdapter.OnItemClickListener {

            override fun onItemClick(subject: Subject) {

                val intent = Intent(this@MainActivity, UpdateSubject::class.java)
                intent.putExtra(EXTRA_SUBJECT_ID, subject.id)
                intent.putExtra(EXTRA_SUBJECT_TITLE, subject.title)

                startActivityForResult(intent, REQUEST_EDIT_SUBJECT)

            }
        })


        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Toast.makeText(this@MainActivity, "Deleted", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(recyclerViewMain)

        viewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)
        viewModel.getSubjects().observe(this, Observer<List<Subject>> {
            adapter.submitList(it)
        })

    }

}
