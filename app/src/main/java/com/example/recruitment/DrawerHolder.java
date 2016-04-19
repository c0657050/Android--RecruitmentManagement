package com.example.recruitment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by Toshif on 27-03-2016.
 */

public class DrawerHolder extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] web;
	int icon[];
	String btn_clr;

	public DrawerHolder(Activity context, String[] web, int[] icon) {
		super(context, R.layout.drawer_list_items, web);
		this.context = context;
		this.web = web;
		this.icon = icon;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		

		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.drawer_list_items, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.drawer_text);
		/*final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(context);
 	    
		 btn_clr=(mSharedPreference.getString("mbutton_color", ""));
		*/
		/*Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/FreeSans.ttf");
		txtTitle.setTypeface(font,Typeface.BOLD);*/
	//	txtTitle.setTextColor(Color.parseColor(btn_clr));
		
		ImageView image = (ImageView) rowView.findViewById(R.id.drawer_image);

		txtTitle.setText(web[position]);
		

		image.setImageResource(icon[position]);
		
		
		return rowView;
	}
}
