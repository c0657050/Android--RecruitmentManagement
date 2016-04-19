package com.example.recruitment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Toshif on 01-04-2016.
 */

public class Home_employee_tabs extends ActionBarActivity {

    ImageView jobs_view_epmlyee,resume_create,profile_employee,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_employee_tabs);
        getSupportActionBar().hide();

        jobs_view_epmlyee = (ImageView) findViewById(R.id.jobs_view_epmlyee);
        resume_create = (ImageView) findViewById(R.id.resume);
        profile_employee= (ImageView) findViewById(R.id.profile_employee);

        logout = (ImageView) findViewById(R.id.logout_employee);

        jobs_view_epmlyee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in_find_job = new Intent(Home_employee_tabs.this,Find_jobs_list_employee.class);
                startActivity(in_find_job);
            }
        });
        resume_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in_rc = new Intent(Home_employee_tabs.this,Resume_create_employee.class);
                startActivity(in_rc);
            }
        });
        profile_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in_pro = new Intent(Home_employee_tabs.this,Profile_employee.class);
                startActivity(in_pro);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Home_employee_tabs.this);
                alertDialogBuilder.setTitle("Do you want to logout?");
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Util.RemoveSharedPreference(Home_employee_tabs.this,"seekerid_preference");
                                Util.RemoveSharedPreference(Home_employee_tabs.this,"email_preference");
                               /* Util.RemoveSharedPreference(Home_employee_tabs.this,"log_emp");
                                Util.RemoveSharedPreference(Home_employee_tabs.this,"log_pw");*/

                                Intent in_pro = new Intent(Home_employee_tabs.this,MainScreen.class);
                                in_pro.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(in_pro);
                                finish();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();

                    }
                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

    }


}
