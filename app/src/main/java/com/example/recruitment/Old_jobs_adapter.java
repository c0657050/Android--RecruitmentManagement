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
 * Created by Toshif on 17-03-2016.
 */
public class Old_jobs_adapter extends BaseAdapter {

    String [] result;
    Context context;
    String [] imageId;
    private LayoutInflater layoutInflater;

    ArrayList<String> str_job_id_rec;
    ArrayList<String> str_job_tile_rec;
    ArrayList<String> str_job_detail_rec;
    ArrayList<String> str_salary_rec;
    ArrayList<String> str_experience_rec;
    ArrayList<String> str_location_rec;
    ArrayList<String> str_role_rec;
    ArrayList<String> str_job_type_rec;

    public  Old_jobs_adapter(Context context, ArrayList<String> job_id, ArrayList<String>  job_tile,
                             ArrayList<String>  job_detail, ArrayList<String>  salary, ArrayList<String>  experience,
                             ArrayList<String>  location, ArrayList<String>  role,  ArrayList<String>  job_type) {
        // TODO Auto-generated constructor stub

        layoutInflater = LayoutInflater.from(context);
        this.str_job_id_rec = job_id;
        this.str_job_tile_rec = job_tile;
        this.str_job_detail_rec = job_detail;
        this.str_salary_rec = salary;
        this.str_experience_rec = experience;
        this.str_location_rec = location;
        this.str_role_rec = role;
        this.str_job_type_rec = job_type;
        this.context = context;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return str_job_id_rec.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return str_job_id_rec.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        final ViewHolder holder;
        view = layoutInflater.inflate(R.layout.list_items_old_jobs, null);
        holder = new ViewHolder();

        holder.text_company_find = (TextView) view.findViewById(R.id.text_company);
        holder.text_profile_find = (TextView) view.findViewById(R.id.text_profile);
        holder.text_exp_find = (TextView) view.findViewById(R.id.text_exp);
        holder.text_more = (TextView) view.findViewById(R.id.text_more);


        holder.text_company_find.setText("Job ID : "+ str_job_id_rec.get(position));
        holder.text_profile_find.setText("Job Title : " + str_job_tile_rec.get(position));
        holder.text_exp_find.setText("Experience : " + str_experience_rec.get(position));
        String colr = "#000000";
        holder.text_company_find.setTextColor(Color.parseColor(colr));
        holder.text_profile_find.setTextColor(Color.parseColor(colr));
        holder.text_exp_find.setTextColor(Color.parseColor(colr));
        holder.text_more.setTag(position);

        holder.text_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String a = holder.text_more.getTag(position).toString();

                Intent post_des = new Intent(context, Post_description.class);
                post_des.putExtra("id_res", str_job_id_rec.get(position));
                post_des.putExtra("job_title_res", str_job_tile_rec.get(position));
                post_des.putExtra("details_res", str_job_detail_rec.get(position));
                post_des.putExtra("salary_res", str_salary_rec.get(position));
                post_des.putExtra("exp_res", str_experience_rec.get(position));
                post_des.putExtra("location_res", str_location_rec.get(position));
                post_des.putExtra("role_res", str_role_rec.get(position));
                post_des.putExtra("type_res",str_job_type_rec.get(position));

                System.out.println("missssssssssss  " + str_experience_rec.get(position)+" "+ str_salary_rec.get(position));
                context.startActivity(post_des);
            }
        });
        view.setTag(holder);
        return view;
    }

    public class ViewHolder {

        TextView text_more, text_company_find,text_profile_find,text_exp_find;
    }
}