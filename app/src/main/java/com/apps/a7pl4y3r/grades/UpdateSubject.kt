package com.apps.a7pl4y3r.grades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.edit_subject.*

class UpdateSubject : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_subject)

        editorCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        editorDone.setOnClickListener {

            if (!editorET.text.trim().isEmpty()) {

                val title = editorET.text.toString()
                val data = Intent()

                data.putExtra(EXTRA_SUBJECT_ID, intent.getStringExtra(EXTRA_SUBJECT_ID))
                data.putExtra(EXTRA_SUBJECT_TITLE, title)

                Toast.makeText(this, "got here", Toast.LENGTH_SHORT).show()

                setResult(Activity.RESULT_OK, data)
                finish()

            } else {

                Toast.makeText(this, "Update rejected!", Toast.LENGTH_SHORT).show()

            }

        }

    }
}