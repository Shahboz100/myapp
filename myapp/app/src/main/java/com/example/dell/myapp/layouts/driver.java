package com.example.dell.myapp.layouts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import com.example.dell.myapp.R;
import com.example.dell.myapp.fragments.AdsFragment;


public class driver extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public String basic_url = "http://10.0.3.2:5000/";
    private String token;
    ///
    public static final String APP_PREFERENCES = "user_settings";
    public static final String APP_PREFERENCES_NAME = "name";
    public static final String APP_PREFERENCES_SURNAME = "surname";
    public static final String APP_PREFERENCES_TEL = "tel";
    public static final String APP_PREFERENCES_PASSWORD = "password";
    private SharedPreferences mSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //settings = new settingsFragment();
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.driver, menu);

        // Выводим Имя и Фамилию водителя
        setName();

        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ftruns = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_ads) {
            ftruns.replace(R.id.container, AdsFragment.newInstance());
            Toast.makeText(getApplicationContext(), "Объявления", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_location) {

        } else if (id == R.id.nav_cars) {

        } else if (id == R.id.nav_settings) {
            //        android.support.v4.app.FragmentTransaction replace = ftruns.replace(R.id.settings_fragment, settings);
        } else if (id == R.id.nav_send) {

        }
        ftruns.commit();
        item.setChecked(true);
        // Выводим выбранный пункт в заголовке
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void setName() {
        Intent intent = getIntent();
        TextView d_name = (TextView) findViewById(R.id.name_driver);
        TextView byline = (TextView) findViewById(R.id.byline);
        token = intent.getStringExtra("token");
        d_name.setText(intent.getStringExtra("surname") + " " + intent.getStringExtra("name"));
        byline.setText("+" + intent.getStringExtra("tel"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные при закрытии
        Intent intent = getIntent();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_NAME, intent.getStringExtra("name"));
        editor.putString(APP_PREFERENCES_SURNAME, intent.getStringExtra("surname"));
        editor.putString(APP_PREFERENCES_PASSWORD, intent.getStringExtra("password"));
        editor.putString(APP_PREFERENCES_TEL, intent.getStringExtra("tel"));
        editor.apply();
    }
}
