package com.example.recruitment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Toshif on 27-03-2016.
 */

public class Post_description extends ActionBarActivity {
    Button submit_job_show;
    TextView job_title_show,job_details_show,job_id_show,exp_show,job_type_show,location_show,role_show,salary_show,name_show,
            email_job_owner_show,compny_name_show;

    ImageView img_job_edit_show;
    String job_id_fd_res , titel_fd_res,details_fd_res,salary_fd_res,exp_fd_res,location_fd_res,role_fd_res,type_fd_res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_description);
        getSupportActionBar().hide();

        Intent i = getIntent();
        job_id_fd_res=   i.getStringExtra("id_res");
        titel_fd_res=  i.getStringExtra("job_title_res");
        details_fd_res=  i.getStringExtra("details_res");
        salary_fd_res=   i.getStringExtra("salary_res");
        exp_fd_res=i.getStringExtra("exp_res");
        location_fd_res= i.getStringExtra("location_res");
        role_fd_res= i.getStringExtra("role_res");
        type_fd_res=   i.getStringExtra("type_res");


        String email_pref,fname_pref,lname_pref,company_name_pref;


        company_name_pref =    Util.getSharedPreferences(Post_description.this, "company_name_preference");
        email_pref  = Util.getSharedPreferences(Post_description.this, "emaill_preference");
        fname_pref= Util.getSharedPreferences(Post_description.this, "firstname_preference");
        lname_pref=  Util.getSharedPreferences(Post_description.this, "lastname_preference");
        System.out.println("mmmmmmmmmmmmm       "+ exp_fd_res +" "+ salary_fd_res + " " +company_name_pref );


        img_job_edit_show = (ImageView) findViewById(R.id.img_job_edit_show);
        submit_job_show = (Button) findViewById(R.id.submit_job_show);

        job_id_show  = (TextView) findViewById(R.id.job_id_show);
        compny_name_show  = (TextView) findViewById(R.id.compny_name_show);
        job_title_show  = (TextView) findViewById(R.id.job_title_show);
        job_details_show  = (TextView) findViewById(R.id.job_details_show);

        exp_show  = (TextView) findViewById(R.id.exp_show);
        job_type_show  = (TextView)findViewById(R.id.job_type_show);
        location_show  = (TextView) findViewById(R.id.location_show);
        role_show  = (TextView)findViewById(R.id.role_show);
        salary_show  = (TextView) findViewById(R.id.salary_show);
        name_show  = (TextView) findViewById(R.id.name_show);
        email_job_owner_show  = (TextView) findViewById(R.id.email_job_owner_show);

        job_id_show.setText("Job id : "+job_id_fd_res);
        job_title_show.setText("Job title : "+titel_fd_res);
        job_details_show.setText("Details : "+details_fd_res);
        exp_show.setText("Experience : " + exp_fd_res);
        role_show.setText("Role : "+ role_fd_res);
        salary_show.setText("Salary : "+salary_fd_res);
        name_show.setText("Name : "+fname_pref +" "+ lname_pref);
        email_job_owner_show.setText("Email id : "+ email_pref);
        location_show.setText("Location : " +location_fd_res);
        job_type_show.setText("Job type : "+type_fd_res);
        compny_name_show.setText("Company : " + company_name_pref);


        String colr = "#000000";
        job_id_show.setTextColor(Color.parseColor(colr));
        job_title_show.setTextColor(Color.parseColor(colr));
        job_details_show.setTextColor(Color.parseColor(colr));
        exp_show.setTextColor(Color.parseColor(colr));
        role_show.setTextColor(Color.parseColor(colr));
        salary_show.setTextColor(Color.parseColor(colr));
        name_show.setTextColor(Color.parseColor(colr));
       compny_name_show.setTextColor(Color.parseColor(colr));
        email_job_owner_show.setTextColor(Color.parseColor(colr));
        location_show.setTextColor(Color.parseColor(colr));
        job_type_show.setTextColor(Color.parseColor(colr));

        img_job_edit_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit_job_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Post_description.this,Edit_posted_job.class);
                i.putExtra("id_rese", job_id_fd_res);
                i.putExtra("job_title_rese",titel_fd_res);
                i.putExtra("details_rese", details_fd_res);
                i.putExtra("salary_rese", salary_fd_res);
                i.putExtra("exp_rese", exp_fd_res);
                i.putExtra("location_rese", location_fd_res);
                i.putExtra("role_rese", role_fd_res);
                i.putExtra("type_rese",type_fd_res);
                startActivity(i);
                finish();
            }
        });
    }
}