package com.shilpa.SimpleDemoApp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shilpa.SimpleDemoApp.R;
import com.shilpa.SimpleDemoApp.base.activty.AppBaseActivity;
import com.shilpa.SimpleDemoApp.home.HomePresenter;
import com.shilpa.SimpleDemoApp.home.HomePresenterImpl;
import com.shilpa.SimpleDemoApp.home.HomeView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppBaseActivity implements NavigationView.OnNavigationItemSelectedListener, HomeView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.editText)
    EditText editTextData;
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        homePresenter = new HomePresenterImpl(this);
    }
   public void onSubmitButtonClicked(View v)
    {
      String url= editTextData.getText().toString();
        if(url.isEmpty())
        {
            showToast("Please enter a URL");
        }
        else {
            showProgress();

            loadHeaders(url);
        }
    }
   void  loadHeaders(final String url)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<String> data=new ArrayList<String>();
                final StringBuilder builder=new StringBuilder();
                try {
                    Document doc=Jsoup.connect(url).get();
                    String title=doc.title();

                    Elements headers = doc.select("h1, h2, h3, h4, h5, h6");
                    data.add(title);

                    for(Element header:headers) {
                        data.add( header.text());
                    }
                   // doc.


                }
                catch(IOException e){
                    showToast("Unsupported URL");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Intent i=new Intent(getApplicationContext(),HeaderListActivity.class);
                        i.putStringArrayListExtra("HeaderList",data);
                         startActivity(i);
hideProgress();                    }
                });
            }
        }).start();

    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.id1) {
            // Handle the action
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onRequestFailure(String message) {

    }

    @Override
    public void onRequestSuccess() {

    }


}
