package com.example.recruitment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by Toshif on 18-03-2016.
 */

public class Old_posted_jobs extends Fragment {

    ImageView img_back;
    TextView txt_top;
    ListView old_list;

    Context context;

    ArrayList prgmName;

    String recruiterid;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_old_jobs, container, false);

        recruiterid = Util.getSharedPreferences(getActivity(), "recruiterid_preference");
        System.out.println( "iiiiiiiiiiiiiii " + recruiterid);

        old_list = (ListView) view.findViewById(R.id.old_jobs_list);

        if (!Util.isConnected(getActivity())) Util.buildDialog(getActivity()).show();
        else {
            new Old_posted().execute();
        }
        return view;
    }

    public void onResume() {

        // Set title
        ((Home_employer) getActivity()).setActionBarTitle("Posted Jobs");
        super.onResume();
    }

    class Old_posted extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        String msg, success;
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

            params.add(new BasicNameValuePair("tag", "recRJobViewList"));
            params.add(new BasicNameValuePair("submit", "submit"));
            params.add(new BasicNameValuePair("recruiterid", recruiterid));

            json = jsonParser.makeHttpRequest(Login.UrlPrifix + "index.php", "POST", params);
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

                            JSONArray job_id = json.getJSONArray("jobid");
                            JSONArray job_title = json.getJSONArray("job_title");
                            JSONArray job_detail = json.getJSONArray("job_detail");
                            JSONArray salary = json.getJSONArray("salary");
                            JSONArray experience = json.getJSONArray("experience");
                            JSONArray location = json.getJSONArray("location");
                            JSONArray role = json.getJSONArray("role");

                            JSONArray job_type = json.getJSONArray("job_type");

                            ArrayList<String> str_job_id_rec = new ArrayList<>();
                            ArrayList<String> str_job_tile_rec = new ArrayList<>();
                            ArrayList<String> str_job_detail_rec = new ArrayList<>();
                            ArrayList<String> str_salary_rec = new ArrayList<>();
                            ArrayList<String> str_experience_rec = new ArrayList<>();
                            ArrayList<String> str_location_rec = new ArrayList<>();
                            ArrayList<String> str_role_rec = new ArrayList<>();
                            ArrayList<String> str_job_type_rec = new ArrayList<>();

                            for (int i = 0; i < job_id.length(); i++) {

                                str_job_id_rec.add(job_id.getString(i));
                                str_job_tile_rec.add(job_title.getString(i));
                                str_job_detail_rec.add(job_detail.getString(i));
                                str_salary_rec.add(salary.getString(i));
                                str_experience_rec.add(experience.getString(i));
                                str_location_rec.add(location.getString(i));
                                str_role_rec.add(role.getString(i));
                                str_job_type_rec.add(job_type.getString(i));
                            }

                            old_list.setAdapter(new Old_jobs_adapter(getActivity(), str_job_id_rec, str_job_tile_rec,
                                    str_job_detail_rec, str_salary_rec, str_experience_rec, str_location_rec, str_role_rec, str_job_type_rec));

                        } else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                            final AlertDialog alertDialog = alertDialogBuilder.create();

                            alertDialog.setTitle("Oops!!");
                            alertDialog.setMessage("No previous jobs found");
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
