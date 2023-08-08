package com.myapplicationdev.android.mymovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowMovies extends AppCompatActivity {

    Button btnShowAll;
    Spinner sp;
    ListView lv;
    ArrayList<Movies> alMovies1;
    ArrayList<Movies> alMovies2;
    CustomAdapter customAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper db = new DBHelper(ShowMovies.this);
        alMovies1.clear();
        alMovies1.addAll(db.getMovies());
        db.close();
        customAdapter = new CustomAdapter(ShowMovies.this, R.layout.row, alMovies1);
        lv.setAdapter(customAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmovies);

        btnShowAll = findViewById(R.id.buttonShowAll);
        lv = findViewById(R.id.lv);

        DBHelper db = new DBHelper(ShowMovies.this);
        alMovies1 = db.getMovies();
        db.close();

        customAdapter = new CustomAdapter(this, R.layout.row, alMovies1);
        lv.setAdapter(customAdapter);

        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedItem = sp.getSelectedItem().toString();
                alMovies2 = new ArrayList<>();
                for (int i = 0; i < alMovies1.size(); i++) {
                    if (alMovies1.get(i).getRating().equals(selectedItem)) {
                        alMovies2.add(alMovies1.get(i));
                    }
                }
                customAdapter = new CustomAdapter(ShowMovies.this, R.layout.row, alMovies2);
                lv.setAdapter(customAdapter);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies movie;
                if (alMovies2 != null && alMovies2.size() > position) {
                    movie = alMovies2.get(position);
                } else {
                    movie = alMovies2.get(position);
                }
                Intent intent = new Intent(ShowMovies.this, ModifyMovies.class);
                intent.putExtra("movie", (CharSequence) movie);
                startActivity(intent);
            }
        });

    }
}
