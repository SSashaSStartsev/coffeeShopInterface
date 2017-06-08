package com.example.alexa.shopifyinterviewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //========
    // Field variables
    //========

    static TextView totalCheckedTextView;
    static int checkedCount;
    private int viewOpen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up each item, easier to do it in xml than inject into the layout
        ListHeader coffee = (ListHeader) findViewById(R.id.coffee);
        setupView(getResources().getStringArray(R.array.coffee_items), coffee);
        ListHeader sweet = (ListHeader) findViewById(R.id.sweet);
        setupView(getResources().getStringArray(R.array.sweet_items), sweet);
        ListHeader food = (ListHeader) findViewById(R.id.food);
        setupView(getResources().getStringArray(R.array.food_items), food);
        ListHeader drink = (ListHeader) findViewById(R.id.drink);
        setupView(getResources().getStringArray(R.array.drink_items), drink);

        totalCheckedTextView = (TextView) findViewById(R.id.totalCount);
        totalCheckedTextView.setText(getResources().getString(R.string.total_0,checkedCount));
    }


    /**
     * Generate the view with the needed information, info given by pre-specified arrays
     * Sets up an on click listener
     * @param items: the list items to show in the menu
     * @param view: the header itself
     */
    private void setupView(String[] items, final ListHeader view) {
        final ArrayList<CheckableItem> coffeeMenu = new ArrayList<>();
        for(String item: items){
         coffeeMenu.add(new CheckableItem(item));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if there's no open lists, open the tapped list and insert the menu items to it
                if(viewOpen ==0 &&!view.isListOpen()) {
                    view.setListAdapter(coffeeMenu);
                    viewOpen = view.getId();
                }
                // if the open item is the current one, close it and clean up the list items
                else if(viewOpen == view.getId()) {
                    view.removeListAdapter();
                    viewOpen = 0;
                }
            }
        });
    }

}
