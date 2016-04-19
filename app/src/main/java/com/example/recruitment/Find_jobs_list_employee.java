package com.example.recruitment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshif on 27-03-2016.
 */

public class Find_jobs_list_employee extends ActionBarActivity {
    public static String[] prgmImages = {"Company:", "Company:",
            "Company:", "Company:"
            , "Company:"};
    public static String[] prgmNameList = {"Profile:", "Profile:",
            "Profile:", "Profile:"
            , "Profile:"};
    ListView fin_job_list;
    ImageView back_jobs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_jobs_list_employee);
        getSupportActionBar().hide();

        fin_job_list = (ListView) findViewById(R.id.find_jobs_list);
        back_jobs = (ImageView) findViewById(R.id.img_find_jobs);
        if(!Util.isConnected(Find_jobs_list_employee.this))Util.buildDialog(Find_jobs_list_employee.this).show();
        else {


            new Find_job_list().execute();
        }
        back_jobs.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                finish();
            }

        });
    }

    class Find_job_list extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg, success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Find_jobs_list_employee.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
            // mProgressHUD = ProgressHUD.show(Forget_pw.this,"Loading...", true,true);
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("tag", "employeeSjobList"));
            params.add(new BasicNameValuePair("submit", "submit"));
/*
            <form action="index.php" method="post">
            <input type="hidden" name="tag" value="employeeSjobList" />
            <input type="submit" name="submit" value="submit"><br>
            </form>*/
            json = jsonParser.makeHttpRequest(Login.UrlPrifix + "index.php", "POST", params);
//            Log.d("prrrrrrrrrrrrrr", "" + json.toString());
            System.out.println("daaataaaa >>>>>>>>>>>>>" + json.toString());
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            pDialog.dismiss();
            super.onPostExecute(result);
            if (json != null) {

                try {
                    msg = json.getString("error_msg");
                    success = json.getString("error");


                    if (!(success == null)) {
                        if (success.equals("false")) {
                           /* Toast.makeText(Find_jobs_list_employee.this, msg, Toast.LENGTH_SHORT)
                                    .show();*/

                            JSONArray job_id = json.getJSONArray("jobid");
                            JSONArray job_title = json.getJSONArray("job_title");
                            JSONArray job_detail = json.getJSONArray("job_detail");
                            JSONArray salary = json.getJSONArray("salary");
                            JSONArray experience = json.getJSONArray("experience");
                            JSONArray location = json.getJSONArray("location");
                            JSONArray role = json.getJSONArray("role");
                            JSONArray job_type = json.getJSONArray("job_type");

                            ArrayList<String> str_job_id = new ArrayList<>();
                            ArrayList<String> str_job_tile = new ArrayList<>();
                            ArrayList<String> str_job_detail = new ArrayList<>();
                            ArrayList<String> str_salary = new ArrayList<>();
                            ArrayList<String> str_experience = new ArrayList<>();
                            ArrayList<String> str_location = new ArrayList<>();
                            ArrayList<String> str_role = new ArrayList<>();
                            ArrayList<String> str_job_type = new ArrayList<>();

                            for (int i = 0; i < job_id.length(); i++) {

                                str_job_id.add(job_id.getString(i));
                                str_job_tile.add(job_title.getString(i));
                                str_job_detail.add(job_detail.getString(i));
                                str_salary.add(salary.getString(i));
                                str_experience.add(experience.getString(i));
                                str_location.add(location.getString(i));
                                str_role.add(role.getString(i));
                                str_job_type.add(job_type.getString(i));

                             /*   System.out.println(str_job_id+"  "+ str_job_tile+ " "+ str_job_detail
                                +" "+ str_salary+ " "+str_experience);*/

                            }
                            fin_job_list.setAdapter(new Find_job_list_adapter(Find_jobs_list_employee.this, str_job_id, str_job_tile,
                                    str_job_detail,str_salary,str_experience,str_location,str_role, str_job_type));

                        } else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Find_jobs_list_employee.this);
                            final AlertDialog alertDialog = alertDialogBuilder.create();

                            alertDialog.setTitle(msg);
                            alertDialog.setCancelable(false);

                            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, final int which) {


                                    dialog.dismiss();
                                }
                            });
                            alertDialog.show();
                        }

                    } else {
                        Toast.makeText(Find_jobs_list_employee.this, "Please try again in some time", Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

                Toast.makeText(Find_jobs_list_employee.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
}
