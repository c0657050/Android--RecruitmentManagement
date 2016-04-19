package com.example.recruitment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshif on 18-03-2016.
 */
public class New_job_post extends Fragment {


    EditText job_title,job_details,job_id,exp,job_type,location,role,salary,name,email_job_owner,compny_name;
    TextView submit_job;
String recruiterid;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.jobt_post, container, false);

        recruiterid = Util.getSharedPreferences(getActivity(), "recruiterid_preference");
        job_id  = (EditText) view.findViewById(R.id.job_id);
        compny_name  = (EditText) view.findViewById(R.id.compny_name);
        job_title  = (EditText) view.findViewById(R.id.job_title);
        job_details  = (EditText)view. findViewById(R.id.job_details);

        exp  = (EditText)view. findViewById(R.id.exp);
        job_type  = (EditText)view. findViewById(R.id.job_type);
        location  = (EditText) view.findViewById(R.id.location);
        role  = (EditText)view. findViewById(R.id.role);
        salary  = (EditText) view.findViewById(R.id.salary);
        name  = (EditText) view.findViewById(R.id.name);
        email_job_owner  = (EditText) view.findViewById(R.id.email_job_owner);

        submit_job= (TextView) view.findViewById(R.id.submit_job);

        submit_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (job_id.length()==0){

                    Toast.makeText(getActivity(),"Please enter job ID",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (compny_name.length()==0){

                    Toast.makeText(getActivity(),"Please enter company name",Toast.LENGTH_SHORT).show();

                    return;
                }

                if (job_title.length()==0){

                    Toast.makeText(getActivity(),"Please enter job title",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (job_details.length()==0){

                    Toast.makeText(getActivity(),"Please enter job details",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (salary.length()==0){

                    Toast.makeText(getActivity(),"Please enter salary",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (exp.length()==0){

                    Toast.makeText(getActivity(),"Please enter experience",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (location.length()==0){

                    Toast.makeText(getActivity(),"Please enter location",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (role.length()==0){

                    Toast.makeText(getActivity(),"Please enter role",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (job_type.length()==0){

                    Toast.makeText(getActivity(),"Please enter job type",Toast.LENGTH_SHORT).show();
                    return;

                }


                if (name.length()==0){

                    Toast.makeText(getActivity(),"Please enter name",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (email_job_owner.length()==0){

                    Toast.makeText(getActivity(),"Please enter email",Toast.LENGTH_SHORT).show();


                }

                else {
                 //   Toast.makeText(getActivity(),"Your job posted successfully",Toast.LENGTH_SHORT).show();
                    if (!Util.isConnected(getActivity())) Util.buildDialog(getActivity()).show();
                    else {
                        new Post_job().execute();
                    }
                }
            }
        });
        return  view;
    }
    class Post_job extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg,success;
        JSONObject json;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... names) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("tag", "recRJobAdd"));
            params.add(new BasicNameValuePair("submit","submit"));
            params.add(new BasicNameValuePair("recruiterid",recruiterid));
            params.add(new BasicNameValuePair("job_title", job_title.getText().toString()));
            params.add(new BasicNameValuePair("job_detail", job_details.getText().toString()));
            params.add(new BasicNameValuePair("salary", salary.getText().toString()));
            params.add(new BasicNameValuePair("location", location.getText().toString()));
            params.add(new BasicNameValuePair("experience", exp.getText().toString()));
            params.add(new BasicNameValuePair("job_type", job_type.getText().toString()));
            params.add(new BasicNameValuePair("role", role.getText().toString()));

            json = jsonParser.makeHttpRequest(Login.UrlPrifix +"index.php", "POST", params);
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
                            Toast.makeText(getActivity(), "Your job posted successfully", Toast.LENGTH_SHORT)
                                    .show();
                            Fragment mfragment = null;
                            FragmentManager fragmentManager;
                            mfragment = new Old_posted_jobs();
                            fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, mfragment).commit();

                        }
                        else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
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
                        Toast.makeText(getActivity(), "Please try again in some time", Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {

                Toast.makeText(getActivity(), "Please try again in some time", Toast.LENGTH_LONG).show();
            }

        }
    }
}