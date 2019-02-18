package com.apps.a7pl4y3r.grades

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.edit_subject.*

class AddSubject : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_subject)

        editorCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        editorDone.setOnClickListener {

            if (!editorET.text.trim().isEmpty()) {

                val data = Intent()
                val title = editorET.text.toString()

                data.putExtra(EXTRA_SUBJECT_TITLE, title)
                setResult(Activity.RESULT_OK, data)
                finish()

            } else {

                //setResult(Activity.RESULT_CANCELED)
                Toast.makeText(this, "Subject name rejected!", Toast.LENGTH_SHORT).show()
            }

        }

    }
}
