package com.myapplicationdev.android.mymovie;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyMovies extends AppCompatActivity {

    EditText etId, etTitle, etGenre, etYear;
    Spinner sp;
    Button btnUpd, btnDel, btnCan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifymovies);

        etId = findViewById(R.id.editTextID);
        etTitle = findViewById(R.id.editTextTitle2);
        etGenre = findViewById(R.id.editTextGenre2);
        etYear = findViewById(R.id.editTextYear2);

        btnUpd = findViewById(R.id.buttonUpdate);
        btnDel = findViewById(R.id.buttonDelete);
        btnCan = findViewById(R.id.buttonCancel);
        sp = findViewById(R.id.spinnerRating2);

        Intent intent = getIntent();
        Movies data = (Movies) intent.getSerializableExtra("movie");
        etId.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(String.valueOf(data.getYear()));
        sp.setSelection(getIndex(sp, data.getRating()));

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ModifyMovies.this);
                String selectedItem = sp.getSelectedItem().toString();
                data.setTitle(etTitle.getText().toString());
                data.setGenre(etGenre.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setRating(selectedItem);
                db.updateMovie(data);
                db.close();
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ModifyMovies.this);
                db.deleteMovie(data.getId());
                finish();
            }
        });

        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private int getIndex(Spinner spinner, String myString){
        for (int i = 0; i < spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

}
