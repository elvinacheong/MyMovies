package com.myapplicationdev.android.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle, tvGenre, tvYear, tvRating;
    EditText etTitle, etGenre, etYear;
    Spinner spRating;
    Button btnInsert, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.textViewTitle);
        tvGenre = findViewById(R.id.textViewGenre);
        tvYear = findViewById(R.id.textViewYear);
        tvRating = findViewById(R.id.textViewRating);

        etTitle = findViewById(R.id.editTextTitle);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);

        spRating = findViewById(R.id.spinnerRating);

        btnInsert = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonShow);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                String selected = spRating.getSelectedItem().toString();
                db.insertMovie(etTitle.getText().toString(), etGenre.getText().toString(),
                        Integer.parseInt(etYear.getText().toString()), selected);
                Toast.makeText(MainActivity.this, "Movie successfully added!", Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowMovies.class);
                startActivity(intent);
            }
        });
    }
}