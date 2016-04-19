package com.example.recruitment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Toshif on 28-03-2016.
 */
public class Seekers_profile_adapter extends BaseAdapter {

    String [] result;
    Context context;
    String [] imageId;
    private LayoutInflater layoutInflater;
    ArrayList<String> str_seekerid;
    ArrayList<String> str_firstname ;
    ArrayList<String> str_lastname ;
    ArrayList<String> str_email ;
    ArrayList<String> str_phone;

    public  Seekers_profile_adapter (Context context,ArrayList<String> job_id, ArrayList<String>  job_tile,
                                     ArrayList<String>  job_detail, ArrayList<String>  salary, ArrayList<String>  experience) {
        // TODO Auto-generated constructor stub
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.str_seekerid = job_id;
        this.str_firstname = job_tile;
        this.str_lastname = job_detail;
        this.str_email = salary;
        this.str_phone = experience;

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return str_seekerid.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return str_seekerid.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        final ViewHolder holder;
        view = layoutInflater.inflate(R.layout.list_items_seekers_profile, null);
        holder = new ViewHolder();

        holder.text_name_seeker= (TextView) view.findViewById(R.id.text_name_seeker);
        holder.text_profile_seeker = (TextView) view.findViewById(R.id.text_profile_seeker);
        holder.text_exp_seeker = (TextView) view.findViewById(R.id.text_exp_seeker);
        holder.text_more_seeker = (TextView) view.findViewById(R.id.text_more_seeker);

        holder.text_more_seeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent post_des = new Intent(context,Seeker_des.class);
                post_des.putExtra("seeker's_id", str_seekerid.get(position));

                context.startActivity(post_des);
            }
        });
        view.setTag(holder);

        holder.text_name_seeker.setText("Name : " + str_firstname.get(position)+" "+ str_lastname.get(position));

        holder.text_profile_seeker.setText("Contact number : "+str_phone.get(position));
        holder.text_exp_seeker.setText("Email id : "+ str_email.get(position));

        String clr = "#000000";

        holder.text_name_seeker.setTextColor(Color.parseColor(clr));
        holder.text_profile_seeker.setTextColor(Color.parseColor(clr));
        holder.text_exp_seeker.setTextColor(Color.parseColor(clr));
        holder.text_more_seeker.setTextColor(Color.parseColor(clr));

        return view;

    }

    public class ViewHolder {

        TextView text_name_seeker, text_exp_seeker,text_profile_seeker,text_more_seeker;


    }

}
