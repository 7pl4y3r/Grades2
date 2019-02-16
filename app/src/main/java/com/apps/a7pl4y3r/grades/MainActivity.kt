package com.apps.a7pl4y3r.grades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


    private lateinit var viewModel: SubjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()

    }

    private fun setRecyclerView() {

        val adapter = SubjectAdapter()

        recyclerViewMain.setHasFixedSize(true)
        recyclerViewMain.layoutManager = LinearLayoutManager(this)
        recyclerViewMain.adapter = adapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
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
