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
 * Created by Toshif on 4/15/2016.
 */
public class Rec_Applied_condidates extends Fragment {

    Context context;
    ListView applied_list;
String recruiter_id_rec;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.rec_applied_condidates, container, false);
        recruiter_id_rec = Util.getSharedPreferences(getActivity(), "recruiterid_preference");


        applied_list = (ListView)view.findViewById(R.id.applied_condi_list);
        if (!Util.isConnected(getActivity()))Util.buildDialog(getActivity()).show();
        else {
            new Applied_condidates().execute();
        }
        return  view;

    }
    public void onResume() {

        // Set title
        ((Home_employer)getActivity()).setActionBarTitle("Interested Condidates");
        super.onResume();
    }

    class Applied_condidates extends AsyncTask<String, String, String> {

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

            params.add(new BasicNameValuePair("tag","employeeSjobAppliying" ));
            params.add(new BasicNameValuePair("submit", "submit"));
            params.add(new BasicNameValuePair("recruiterid",recruiter_id_rec ));


            json = jsonParser.makeHttpRequest(Login.UrlPrifix +"index.php", "POST", params);
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
                            JSONArray seekerid_rec_apply = json.getJSONArray("seekerid");
                            JSONArray firstname_rec_apply = json.getJSONArray("firstname");
                            JSONArray lastname_rec_apply = json.getJSONArray("lastname");
                            JSONArray phone_rec_apply = json.getJSONArray("phone");
                            JSONArray address_rec_apply = json.getJSONArray("address");
                            JSONArray email_rec_apply = json.getJSONArray("email");

                            ArrayList<String> str_seekerid_rec_apply = new ArrayList<>();
                            ArrayList<String> str_firstname_rec_apply = new ArrayList<>();
                            ArrayList<String> str_lastname_rec_apply = new ArrayList<>();
                            ArrayList<String> str_email_rec_apply= new ArrayList<>();
                            ArrayList<String> str_phone_rec_apply= new ArrayList<>();
                            ArrayList<String> str_address_rec_apply = new ArrayList<>();

                            for (int i = 0; i < seekerid_rec_apply.length(); i++) {

                                str_seekerid_rec_apply.add(seekerid_rec_apply.getString(i));
                                str_firstname_rec_apply.add(firstname_rec_apply.getString(i));
                                str_lastname_rec_apply.add(lastname_rec_apply.getString(i));
                                str_email_rec_apply.add(email_rec_apply.getString(i));
                                str_phone_rec_apply.add(phone_rec_apply.getString(i));
                                str_address_rec_apply.add(address_rec_apply.getString(i));
                            }

                            applied_list.setAdapter(new Applied_condidates_adaptor(getActivity(), str_seekerid_rec_apply, str_firstname_rec_apply,
                                    str_lastname_rec_apply, str_email_rec_apply, str_phone_rec_apply, str_address_rec_apply));

                        }

                        else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                            final AlertDialog alertDialog = alertDialogBuilder.create();

                            alertDialog.setTitle("Oops!!");
                            alertDialog.setMessage("No candidates found");
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
