package com.example.alexa.shopifyinterviewapp;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by alexa on 6/6/2017.
 */

public class ListItem extends RelativeLayout{

    View rootView;
    CheckBox checkBox;
    TextView listItem;
    CheckableItem itemData;
    boolean init;

    public ListItem(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.list_item, this);
        checkBox = (CheckBox) findViewById(R.id.list_item_check_box);
        listItem = (TextView) findViewById(R.id.checkable_list_item);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setItemDataChecked(isChecked);
                if(!init) {
                    MainActivity.totalCheckedTextView.setText(getResources().getString(R.string.total_0, MainActivity.checkedCount));
                }
                else{
                    init = false;
                }
            }
        });
    }

    private void setItemDataChecked(boolean isChecked) {
        itemData.setChecked(isChecked);
        if(isChecked && !init){
            MainActivity.checkedCount++;
        }
        else if(!init) {
            MainActivity.checkedCount--;
        }
    }

    public void setItemData(CheckableItem itemData) {
        this.itemData = itemData;
        listItem.setText(itemData.getText());
        init = itemData.isChecked();
        checkBox.setChecked(itemData.isChecked());
    }
}
