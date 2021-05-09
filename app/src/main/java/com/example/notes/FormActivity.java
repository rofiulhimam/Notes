package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.Normalizer;

public class FormActivity extends AppCompatActivity{
    private String KEY_KONDISI = "kondisi";
    private String KEY_ID = "id";
    private String KEY_TITLE = "title";
    private String KEY_DESC = "desc";
    private Button btnSubmit;
    private EditText edtTitle, edtDesc;
    DatabaseHelper helper;
    int id;
    String title;
    String desc;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        btnSubmit = findViewById(R.id.btnsubmit);
        edtTitle = findViewById(R.id.edttitle);
        edtDesc = findViewById(R.id.edtdesc);
        mainActivity = new MainActivity();
        helper = new DatabaseHelper(this);
        DatabaseHelper db = new DatabaseHelper(this);
        NotesModels currentNotes = new NotesModels();
        String kondisi = getIntent().getStringExtra(KEY_KONDISI);
        if (kondisi.equals("edit")){
            id = getIntent().getIntExtra(KEY_ID, 0);
            edtTitle.setText(getIntent().getStringExtra(KEY_TITLE));
            edtDesc.setText(getIntent().getStringExtra(KEY_DESC));
            btnSubmit.setText("Update");
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNotes.setTitle(edtTitle.getText().toString());
                    currentNotes.setDesc(edtDesc.getText().toString());
                    db.update(currentNotes, id);
                    finish();
                }
            });
        }else {
            btnSubmit.setText("Submit");
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNotes.setTitle(edtTitle.getText().toString());
                    currentNotes.setDesc(edtDesc.getText().toString());
                    db.insert(currentNotes);
                    finish();
                }
            });
        }

    }
}
