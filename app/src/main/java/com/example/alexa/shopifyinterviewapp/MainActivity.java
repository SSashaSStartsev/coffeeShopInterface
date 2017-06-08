package com.example.alexa.shopifyinterviewapp;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ContentAdapter coffee;
    ContentAdapter sweet;
    ContentAdapter food;
    ContentAdapter drink;
    static TextView totalCheckedTextView;
    static int checkedCount;
    CoordinatorLayout mainLayout;
    ArrayList<CheckBox> checkBoxes;

    int viewOpen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainLayout = (CoordinatorLayout) findViewById(R.id.main_layout);

        coffee = (ContentAdapter) findViewById(R.id.coffee);
        setupView(getResources().getStringArray(R.array.coffee_items),coffee);
        sweet = (ContentAdapter) findViewById(R.id.sweet);
        setupView(getResources().getStringArray(R.array.sweet_items),sweet);
        food = (ContentAdapter) findViewById(R.id.food);
        setupView(getResources().getStringArray(R.array.food_items),food);
        drink =(ContentAdapter) findViewById(R.id.drink);
        setupView(getResources().getStringArray(R.array.drink_items),drink);

        totalCheckedTextView = (TextView) findViewById(R.id.totalCount);
        totalCheckedTextView.setText(getResources().getString(R.string.total_0,checkedCount));
    }

    private void setupView(String[] items, final ContentAdapter view) {
        final ArrayList<CheckableItem> coffeeMenu = new ArrayList<>();
        for(String item: items){
         coffeeMenu.add(new CheckableItem(item));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewOpen ==0 &&!view.isListOpen()) {
                    view.setListAdapter(coffeeMenu);
                    viewOpen = view.getId();
                    checkBoxes = view.getCheckboxes();
                }
                else if(viewOpen == view.getId()) {
                    view.removeListAdapter();
                    viewOpen = 0;
                    checkBoxes = null;
                }
            }
        });
    }

}
