
package com.shilpa.SimpleDemoApp.activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shilpa.SimpleDemoApp.R;
import com.shilpa.SimpleDemoApp.base.activty.AppBaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderListActivity extends AppBaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headers);
        ButterKnife.bind(this);



        setSupportActionBar(mToolBar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent=getIntent();
        final ListView listview = (ListView) findViewById(R.id.listview);
               ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, intent.getStringArrayListExtra("HeaderList"));


        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                int itemPosition = position;


                String itemValue = (String) listview.getItemAtPosition(position);


                Toast.makeText(getApplicationContext(),
                        "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });
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