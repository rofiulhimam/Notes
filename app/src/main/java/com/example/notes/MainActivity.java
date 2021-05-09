package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerviewAdapter.OnUserClickListener {
    private FloatingActionButton fab;
    private AppBarLayout appBarLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    int id;
    List<NotesModels> listNotes;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        appBarLayout.setStateListAnimator(null);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAdd = new Intent(MainActivity.this, FormActivity.class);
                toAdd.putExtra("kondisi", "submit");
                startActivity(toAdd);
            }
        });
    }

    private void initialize(){
        fab = findViewById(R.id.fab_add);
        recyclerView = findViewById(R.id.list_data);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        appBarLayout = findViewById(R.id.appbar);
    }

    private void setupRecyclerView() {
        DatabaseHelper db=new DatabaseHelper(this);
        listNotes=db.selectUserData();
        RecyclerviewAdapter adapter=new RecyclerviewAdapter(context,listNotes,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupRecyclerView();
    }

    @Override
    public void onUserClick(NotesModels currentNotes, String action) {
        if(action.equals("Edit")){
            Intent toForm = new Intent(MainActivity.this, FormActivity.class);
            toForm.putExtra("kondisi", "edit");
            toForm.putExtra("title", currentNotes.getTitle());
            toForm.putExtra("desc", currentNotes.getDesc());
            toForm.putExtra("id", currentNotes.getId());
            startActivity(toForm);
        }
        if(action.equals("Delete")){
            DatabaseHelper db=new DatabaseHelper(this);
            db.delete(currentNotes.getId());
            setupRecyclerView();
        }
    }
}