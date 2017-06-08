package com.example.alexa.shopifyinterviewapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alexa on 6/5/2017.
 */

public class ContentAdapter extends RelativeLayout {

    View rootView;
    TextView title;
    TextView subtitle;
    Boolean listOpen;
    ArrayList<CheckableItem> checkableItems;
    ArrayList<ListItem> listItems;
    LinearLayout layout;

    public ContentAdapter(Context context) {
        super(context);
    }

    public ContentAdapter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {

        rootView = inflate(context, R.layout.list_group, this);
        title = (TextView) rootView.findViewById(R.id.list_title);
        subtitle = (TextView) rootView.findViewById(R.id.subtitle);
        listOpen = false;

        TypedArray ta = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ContentAdapter, 0, 0);
        title.setText(ta.getString(R.styleable.ContentAdapter_title));
        subtitle.setText(ta.getString(R.styleable.ContentAdapter_subtitle));
        layout = (LinearLayout) findViewById(R.id.item_layout);
        checkableItems = new ArrayList<>();
        listItems = new ArrayList<>();
    }

    public boolean isListOpen() {
        return listOpen;
    }

    public ArrayList<CheckBox> getCheckboxes() {
        if (listItems.size() > 1) {
            ArrayList<CheckBox> checkBoxes = new ArrayList<>();
            for (ListItem listItem : listItems) {
                checkBoxes.add(listItem.checkBox);
            }
            return checkBoxes;
        }
        return null;
    }

    public void setCheckableItems(ArrayList<CheckableItem> items) {
        this.checkableItems = items;
    }

    public void setListAdapter(ArrayList<CheckableItem> items) {
        if (checkableItems.size() < 1) {
            setCheckableItems(items);
        }

        for (CheckableItem item : checkableItems) {
            ListItem t = new ListItem(rootView.getContext());
            t.setItemData(item);
            listItems.add(t);
            layout.addView(t);
            expand(t);
        }
        listOpen = true;
    }

    public void removeListAdapter() {
        if (checkableItems != null && isListOpen()) {
            for (CheckableItem item : checkableItems) {
                layout.removeViewAt(layout.getChildCount() - 1);
                listItems.clear();
            }
        }
        listOpen = false;
    }

    public void expand(final View v) {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(300);
        v.startAnimation(a);
    }

}
