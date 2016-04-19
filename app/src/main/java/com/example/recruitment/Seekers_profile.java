package com.example.recruitment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by Toshif on 18-03-2016.
 */
public class Seekers_profile extends Fragment {

    ListView seeker_list;
String recruiter_id_s;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.seeker_profile, container, false);
        recruiter_id_s = Util.getSharedPreferences(getActivity(), "recruiterid_preference");

        seeker_list = (ListView)view. findViewById(R.id.seeker_list);

        if (!Util.isConnected(getActivity()))Util.buildDialog(getActivity()).show();
        else {

            new Profile_seekers().execute();
        }
        return view;
    }

    class Profile_seekers extends AsyncTask<String, String, String> {

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

            params.add(new BasicNameValuePair("tag","employeeSseekerList"));
            params.add(new BasicNameValuePair("submit", "submit"));
            params.add(new BasicNameValuePair("recruiterid", recruiter_id_s));

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
                            /*Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT)
                                    .show();*/
                            JSONArray seekerid = json.getJSONArray("seekerid");
                            JSONArray firstname = json.getJSONArray("firstname");
                            JSONArray lastname = json.getJSONArray("lastname");
                            JSONArray email = json.getJSONArray("email");
                            JSONArray phone = json.getJSONArray("phone");

                            ArrayList<String> str_seekerid = new ArrayList<>();
                            ArrayList<String> str_firstname = new ArrayList<>();
                            ArrayList<String> str_lastname = new ArrayList<>();
                            ArrayList<String> str_email = new ArrayList<>();
                            ArrayList<String> str_phone = new ArrayList<>();

                            for (int i = 0; i < seekerid.length(); i++) {

                                str_seekerid.add(seekerid.getString(i));
                                str_firstname.add(firstname.getString(i));
                                str_lastname.add(lastname.getString(i));
                                str_email.add(email.getString(i));
                                str_phone.add(phone.getString(i));
                            }
                            seeker_list.setAdapter(new Seekers_profile_adapter(getActivity(),str_seekerid, str_firstname,
                                    str_lastname,str_email,str_phone));

                        }
                        else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                            final AlertDialog alertDialog = alertDialogBuilder.create();

                            alertDialog.setTitle("Oops!!");
                            alertDialog.setMessage("No details found");
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
