package com.example.alexa.shopifyinterviewapp;

/**
 * Created by alexa on 6/7/2017.
 *
 * Holder for values for each list item
 */

class CheckableItem {

    private final String text;
    private boolean checked;

    CheckableItem(String item) {
        text = item;
    }

    String getText() {
        return text;
    }

    boolean isChecked() {
        return checked;
    }

    void setChecked(boolean isChecked) {
        checked = isChecked;
    }

}
