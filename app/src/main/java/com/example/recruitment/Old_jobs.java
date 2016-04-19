package com.example.recruitment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshif on 21-03-2016.
 */

public class Old_jobs extends ActionBarActivity {

    ImageView img_back;
    TextView txt_top;
    ListView old_list;

    Context context;

    ArrayList prgmName;
    public static String [] prgmImages={"Company:","Company:",
            "Company:","Company:"
            ,"Company:"};
    public static String [] prgmNameList={"Profile:","Profile:",
            "Profile:","Profile:"
            ,"Profile:"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_jobs);
        getSupportActionBar().hide();

        context=this;

        old_list = (ListView) findViewById(R.id.old_jobs_list);

        img_back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                finish();
            }

        });

    //    old_list.setAdapter(new Old_jobs_adapter(this,prgmNameList,prgmImages));

        if(Util.isConnected(Old_jobs.this))Util.buildDialog(Old_jobs.this).show();
        else {

            new Jobs_old().execute();

        }

    }

    class Jobs_old extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg,success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(Old_jobs.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
            // mProgressHUD = ProgressHUD.show(Forget_pw.this,"Loading...", true,true);
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();


              params.add(new BasicNameValuePair("tag", "recRJobViewList"));
            params.add(new BasicNameValuePair("submit", "submit"));
            params.add(new BasicNameValuePair("recruiterid","" ));
            params.add(new BasicNameValuePair("jobid", ""));


            json = jsonParser.makeHttpRequest(Login.UrlPrifix +"FetchUserData.php", "POST", params);

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

                        JSONArray job_id_rec = json.getJSONArray("jobid");
                        JSONArray job_title_rec = json.getJSONArray("job_title");
                        JSONArray job_detail_rec = json.getJSONArray("job_detail");
                        JSONArray salary_rec = json.getJSONArray("salary");
                        JSONArray experience_rec = json.getJSONArray("experience");
                        JSONArray location_rec = json.getJSONArray("location");
                        JSONArray role_rec = json.getJSONArray("role");
                        JSONArray job_type_rec = json.getJSONArray("job_type");

                        Toast.makeText(Old_jobs.this, msg, Toast.LENGTH_SHORT)
                                .show();
                        ArrayList<String> str_job_id_rec = new ArrayList<>();
                        ArrayList<String> str_job_tile_rec = new ArrayList<>();
                        ArrayList<String> str_job_detail_rec = new ArrayList<>();
                        ArrayList<String> str_salary_rec = new ArrayList<>();
                        ArrayList<String> str_experience_rec = new ArrayList<>();
                        ArrayList<String> str_location_rec = new ArrayList<>();
                        ArrayList<String> str_role_rec = new ArrayList<>();
                        ArrayList<String> str_job_type_rec = new ArrayList<>();

                        for (int i = 0; i < job_id_rec.length(); i++) {

                            str_job_id_rec.add(job_id_rec.getString(i));
                            str_job_tile_rec.add(job_title_rec.getString(i));
                            str_job_detail_rec.add(job_detail_rec.getString(i));
                            str_salary_rec.add(salary_rec.getString(i));
                            str_experience_rec.add(experience_rec.getString(i));
                            str_location_rec.add(location_rec.getString(i));
                            str_role_rec.add(role_rec.getString(i));
                            str_job_type_rec.add(job_type_rec.getString(i));
                        }

                        old_list.setAdapter(new Old_jobs_adapter(Old_jobs.this, str_job_id_rec, str_job_tile_rec,
                                str_job_detail_rec,str_salary_rec,str_experience_rec,str_location_rec,str_role_rec, str_job_type_rec));
                    }


                    else {

                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Old_jobs.this);
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
                    Toast.makeText(Old_jobs.this, "Please try again in some time", Toast.LENGTH_SHORT)
                            .show();
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

                Toast.makeText(Old_jobs.this, "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
}