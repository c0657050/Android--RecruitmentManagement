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
 * Created by Toshif on 27-03-2016.
 */
public class Find_job_list_adapter extends BaseAdapter {

    String [] result;
    Context context;
    String [] imageId;
    private LayoutInflater layoutInflater;
    ArrayList<String> str_job_id;
    ArrayList<String> str_job_tile;
    ArrayList<String> str_job_detail;
    ArrayList<String> str_salary;
    ArrayList<String> str_experience;
    ArrayList<String> str_location;
    ArrayList<String> str_role;
    ArrayList<String> str_job_type;



    public  Find_job_list_adapter(Context context, ArrayList<String> job_id, ArrayList<String>  job_tile,
                                  ArrayList<String>  job_detail, ArrayList<String>  salary, ArrayList<String>  experience,
                                  ArrayList<String>  location, ArrayList<String>  role,  ArrayList<String>  job_type){

        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.str_job_id = job_id;
        this.str_job_tile = job_tile;
        this.str_job_detail = job_detail;
        this.str_salary = salary;
        this.str_experience = experience;
        this.str_location = location;
        this.str_role = role;
        this.str_job_type = job_type;

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return str_job_id.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return str_job_id.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        final ViewHolder holder;
        view = layoutInflater.inflate(R.layout.list_items_find_jobs, null);
        holder = new ViewHolder();

        holder.text_company_find = (TextView) view.findViewById(R.id.text_company_find);
        holder.text_profile_find = (TextView) view.findViewById(R.id.text_profile_find);
        holder.text_exp_find = (TextView) view.findViewById(R.id.text_exp_find);
        holder.text_more_find = (TextView) view.findViewById(R.id.text_more_find);

        holder.text_company_find.setText("Job ID : "+ str_job_id.get(position));
        holder.text_profile_find.setText("Job Title : " + str_job_tile.get(position));
        holder.text_exp_find.setText("Experience : " + str_experience.get(position));

        String colr = "#000000";
        holder.text_company_find.setTextColor(Color.parseColor(colr));
        holder.text_profile_find.setTextColor(Color.parseColor(colr));
        holder.text_exp_find.setTextColor(Color.parseColor(colr));
     /*   holder.text_company_find.setText(result[position]);

        holder.text_profile_find.setText(imageId[position]);*/
       /* Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/ufonts.com_futura-md-bt-medium.ttf");

        holder.text_q.setTypeface(font);
        holder.text_ans.setTypeface(font);*/
        holder.text_more_find.setTag(position);
        holder.text_more_find.setTextColor(Color.parseColor("#000000"));
        holder.text_more_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String a = holder.text_more_find.getTag(position).toString();
                Intent post_desc = new Intent(context, Find_job_des_employee.class);


                post_desc.putExtra("id", str_job_id.get(position));
                post_desc.putExtra("job_title", str_job_tile.get(position));
                post_desc.putExtra("details", str_job_detail.get(position));
                post_desc.putExtra("salary", str_salary.get(position));
                post_desc.putExtra("exp", str_experience.get(position));
                post_desc.putExtra("location", str_location.get(position));
                post_desc.putExtra("role", str_role.get(position));
                post_desc.putExtra("type",str_job_type.get(position));


                context.startActivity(post_desc);
            }
        });
        view.setTag(holder);



        return view;

    }

    public class ViewHolder {

        TextView text_company_find, text_exp_find,text_profile_find,text_more_find;


    }

}
