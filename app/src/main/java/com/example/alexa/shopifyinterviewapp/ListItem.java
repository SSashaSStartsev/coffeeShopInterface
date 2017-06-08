package com.example.alexa.shopifyinterviewapp;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by alexa on 6/6/2017.
 *
 * Manages the list item view
 */

public class ListItem extends RelativeLayout{

    private View rootView;
    CheckBox checkBox;
    private TextView listItem;
    private CheckableItem itemData;
    private boolean init;

    /**
     * Constructor
     * @param context: the context of the application
     */
    public ListItem(Context context) {
        super(context);
        init(context);
    }

    /**
     * Initializer for the displayed items, sets the checkbox click listener
     */

    private void init(Context context) {
        rootView = inflate(context, R.layout.list_item, this);
        checkBox = (CheckBox) findViewById(R.id.list_item_check_box);
        listItem = (TextView) findViewById(R.id.checkable_list_item);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setItemDataChecked(isChecked);
                //If we are initializing the data, then the onCheckedChanged method can get evoked
                //if the data is previously checked
                if(!init) {
                    MainActivity.totalCheckedTextView.setText(getResources().getString(R.string.total_0, MainActivity.checkedCount));
                }
                else{
                    init = false;
                }
            }
        });
    }


    /**
     * Sets the new state of the checkboxes and updates the total count
     * @param isChecked: should we increment or decrement?
     */
    private void setItemDataChecked(boolean isChecked) {
        itemData.setChecked(isChecked);
        if(isChecked && !init){
            MainActivity.checkedCount++;
        }
        else if(!init) {
            MainActivity.checkedCount--;
        }
    }

    /**
     * Set up previously saved state of the list
     * @param itemData: The item to set data from
     */

    public void setItemData(CheckableItem itemData) {
        this.itemData = itemData;
        listItem.setText(itemData.getText());
        init = itemData.isChecked();
        checkBox.setChecked(itemData.isChecked());
    }
}
