package com.example.recruitment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Toshif on 20-03-2016.
 */

public class Util {
	
	public static Context appContext;
	private static String PREFERENCE;
	 Util() {
	    }

	public boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static void setSharedPreference(Context context, String name,
			String value) {
		
		
		appContext = context;
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
		SharedPreferences.Editor editor = settings.edit();
		// editor.clear();
		editor.putString(name, value);
		editor.commit();

	}
	public static void setSharedPreference1(Context context, String name,
										   boolean value) {


		appContext = context;
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
		SharedPreferences.Editor editor = settings.edit();
		// editor.clear();
		editor.putBoolean(name, value);
		editor.commit();

	}
	public static void RemoveSharedPreference(Context context, String name) {
		appContext = context;
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
		SharedPreferences.Editor editor = settings.edit();
		// editor.clear();
		editor.remove(name);
		editor.commit();
	}
	public static String getSharedPreferences(Context context, String name) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFERENCE, 0);
		return settings.getString(name, null);
	}
	public static Boolean getSharedPreferences1(Context context, String name) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFERENCE, 0);
		return settings.getBoolean(name, Boolean.parseBoolean(null));

	}

	public static void setArraySharedPreference(Context context, String name,
			ArrayList<String> value) {
		appContext = context;
		SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
		SharedPreferences.Editor editor = settings.edit();
		//Set the values
		Set<String> set = new HashSet<String>();
		set.addAll(value);
		editor.putStringSet(name, set);
		editor.commit();
		
		Log.d("set iiiiid", value.toString());
		
	}
	
	public static Set<String> getArraySharedPreference(Context context, String name) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFERENCE, 0);
		
		

		return  settings.getStringSet("key", null);
	}

	
	
	static int dpToPx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }
	
	static Typeface setFont(Context context) {
		appContext = context;
		Typeface font = Typeface.createFromAsset(context.getAssets(), "Exo-Regular.otf"); 
        return font;
    }
	
	static Typeface setBoldFont(Context context) {
		appContext = context;
		Typeface font = Typeface.createFromAsset(context.getAssets(), "Exo-Bold.otf"); 
        return font;
    }

    static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
	public static boolean isConnected(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();

		if (netinfo != null && netinfo.isConnectedOrConnecting()) {
			NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

			if ((mobile != null && mobile.isConnectedOrConnecting())
					|| (wifi != null && wifi.isConnectedOrConnecting()))
				return true;
			else
				return false;
		} else
			return false;
	}
	public static AlertDialog.Builder buildDialog(Context c) {

		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		//builder.setIcon(R.drawable.iconlogo);
		builder.setTitle("No Internet connectivity.");
		builder.setMessage("Please check your internet connection and try again");
		// builder.setIcon(R.drawable.cross);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();
			}
		});

		return builder;
	}
}
