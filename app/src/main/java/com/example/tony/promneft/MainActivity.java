package com.example.tony.promneft;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // TODO:  Инициализация
    Button goBtn;
    EditText adress_view;

    SharedPreferences sPref;
    private final String SAVED_ADRESS = "1";

    public String getADRESS() {
        return ADRESS;
    }

    private final String ADRESS ="123151";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPref();
        goBtn = (Button) findViewById(R.id.go_btn);
        adress_view = (EditText) findViewById(R.id.adress_View);



        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePref();
                if (adress_view.getText().toString() != null || adress_view.getText().toString() != ""){
                    String str = adress_view.getText().toString();
                    String[] parts = str.split(":");
                    if (parts[1] == "//lk.promneft.ru") {
                        // TODO: 12.08.2017 Проверка прошла, делаем интент с ссылкой.
                        Intent mainPageIntent = new Intent(MainActivity.this,MainPage.class);
                        mainPageIntent.putExtra(ADRESS,adress_view.getText().toString());
                        startActivity(mainPageIntent);
                    }
                    // TODO: 13.08.2017 Сделать обработку в случае невернного ввода ссылки
                    Toast error = Toast.makeText(getApplicationContext(), "Неверная ссылка!",Toast.LENGTH_LONG);
                    error.show();
                }


            }
        });

    }
    private void savePref () {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_ADRESS,adress_view.getText().toString());
        ed.apply();
    }
    private void loadPref (){
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_ADRESS,"");
        adress_view.setText(savedText);
    }
}
