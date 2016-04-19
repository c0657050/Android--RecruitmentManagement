package com.example.recruitment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Toshif on 18/04/2016.
 */
public class Applied_condidates_adaptor extends BaseAdapter {

    ArrayList<String> str_seekerid_rec_apply;
    ArrayList<String> str_firstname_rec_apply;
    ArrayList<String> str_lastname_rec_apply;
    ArrayList<String> str_email_rec_apply;
    ArrayList<String> str_phone_rec_apply;
    ArrayList<String> str_address_rec_apply;

    Context context;

    private LayoutInflater layoutInflater;

    public Applied_condidates_adaptor(Context context, ArrayList<String> s_id, ArrayList<String> s_fname,
                                      ArrayList<String> s_lname, ArrayList<String> s_email,
                                      ArrayList<String> s_phone, ArrayList<String> s_add
    ) {

        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.str_address_rec_apply = s_add;
        this.str_email_rec_apply = s_email;
        this.str_firstname_rec_apply = s_fname;
        this.str_lastname_rec_apply = s_lname;
        this.str_phone_rec_apply = s_phone;
        this.str_seekerid_rec_apply = s_id;
    }

    @Override
    public int getCount() {
        return str_seekerid_rec_apply.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return str_seekerid_rec_apply.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        final ViewHolder holder;
        view = layoutInflater.inflate(R.layout.list_items_condi_appliyed, null);
        holder = new ViewHolder();
       /* this.str_address_rec_apply = s_add;
        this.str_email_rec_apply = s_email;
        this.str_firstname_rec_apply = s_fname;
        this.str_lastname_rec_apply = s_fname;
        this.str_phone_rec_apply = s_phone;
        this.str_seekerid_rec_apply = s_id;*/
        holder.text_name = (TextView) view.findViewById(R.id.text_fname);
        holder.text_email = (TextView) view.findViewById(R.id.text_email);
        holder.text_con = (TextView) view.findViewById(R.id.text_con);
      //  holder.text_more = (TextView) view.findViewById(R.id.text_more_app);

        holder.text_name.setText("Name : "+str_firstname_rec_apply.get(position)+" "+str_lastname_rec_apply.get(position));
        holder.text_email.setText("Email ID : "+str_email_rec_apply.get(position));
        holder.text_con.setText("Contact number : "+str_phone_rec_apply.get(position));

        String clr = "#000000";

        holder.text_name.setTextColor(Color.parseColor(clr));
        holder.text_email.setTextColor(Color.parseColor(clr));
        holder.text_con.setTextColor(Color.parseColor(clr));
       // holder.text_more.setTextColor(Color.parseColor(clr));


       /* Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/ufonts.com_futura-md-bt-medium.ttf");

        holder.text_q.setTypeface(font);
        holder.text_ans.setTypeface(font);*/
      //  holder.text_more.setTag(position);

       /* holder.text_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = holder.text_more.getTag(position).toString();


              Intent post_des = new Intent(context, Details_condidates.class);


                context.startActivity(post_des);
            }
        });*/
        view.setTag(holder);


        return view;

    }

    public class ViewHolder {

        TextView text_more, text_name, text_email, text_con;


    }
}