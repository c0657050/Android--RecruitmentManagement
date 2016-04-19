package com.example.recruitment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Toshif on 27-03-2016.
 */

public class Home_employer extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    ActionBar action;
    int Tag = 0;
    TextView titletext;
    boolean my;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_employer);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();


        action = getSupportActionBar();
        action.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0033cc")));

        action.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        action.setCustomView(R.layout.actionbar_parent);
        //  action.setHomeAsUpIndicator(R.drawable.menu);
        View v = getSupportActionBar().getCustomView();

        titletext = (TextView) v.findViewById(R.id.textView1);
        titletext.setText("Posted Jobs");

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);



    }

    public void setActionBarTitle(String title) {
        titletext.setText(title);
        titletext.setTextSize(20);
        // titletext.setTypeface(font, Typeface.BOLD);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment mfragment = null;
        FragmentManager fragmentManager;

        switch (position) {

            case 0:
                mTitle = "Posted Jobs";
                if (Tag == 1) {
                    titletext.setText("Posted Jobs");

                }

                mfragment = new Old_posted_jobs();

                break;
            case 1:
                mfragment = new New_job_post();
                mTitle = "Post New Job";
                titletext.setText(mTitle);

                break;

            case 2:
                // mfragment = new Report_and_Incidents();
                mfragment = new Seekers_profile();
                mTitle = "Seeker's Profile";
                titletext.setText(mTitle);

                break;
            case 3:
                // mfragment = new Report_and_Incidents();
                mfragment = new Rec_Applied_condidates();
                mTitle = "Interested Candidates";
                titletext.setText(mTitle);

                // Toast.makeText(Home_employer.this,"coming soon!!",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                //  Toast.makeText(Home_employer.this,"coming soon!!",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Home_employer.this);
                alertDialogBuilder.setTitle("Do you want to logout?");
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(getApplicationContext(), MainScreen.class);

                                Util.RemoveSharedPreference(Home_employer.this, "company_name_preference");
                                Util.RemoveSharedPreference(Home_employer.this, "emaill_preference");
                                Util.RemoveSharedPreference(Home_employer.this, "firstname_preference");
                                Util.RemoveSharedPreference(Home_employer.this, "lastname_preference");
                                Util.RemoveSharedPreference(Home_employer.this, "recruiterid_preference");

                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();

                    }
                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                break;
            default:
                break;

        }
        if (mfragment != null) {

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, mfragment).commit();

            Tag = 1;
        } else {

        }

    }
    public void onSectionAttached(int number) {

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home_employer, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }



        @Override
        public void onResume() {
            super.onResume();

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home_employer, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Home_employer) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
