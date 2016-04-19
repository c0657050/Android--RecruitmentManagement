package com.example.recruitment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
/**
 * Created by Toshif on 27-03-2016.
 */

public class MainScreen extends ActionBarActivity {
    TextView text_empyer,text_employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().hide();

        text_empyer = (TextView) findViewById(R.id.empyer);
        text_employee = (TextView) findViewById(R.id.employee);

        text_empyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent empyr = new Intent(MainScreen.this,Login_employer.class);
                startActivity(empyr);

            }
        });

        text_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent empyr = new Intent(MainScreen.this,Login.class);
                startActivity(empyr);
            }
        });
    }
}
