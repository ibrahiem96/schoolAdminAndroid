package com.example.ibrahiemmohammad.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class StudentListHolder{
    CheckBox checkBox;
    TextView listItem;
    Button detailsButton;
    ImageButton unlockButton;

    StudentListHolder(View row){
        this.checkBox = (CheckBox)row.findViewById(R.id.checkItem);
        this.listItem = (TextView)row.findViewById(R.id.listItem);
        this.detailsButton = (Button)row.findViewById(R.id.detailsButton);
        this.unlockButton = (ImageButton)row.findViewById((R.id.imageButton));
        this.unlockButton.setTag(this.listItem.getText());
        this.detailsButton.setTag(this.listItem.getText());
        this.checkBox.setTag(this.listItem.getText());
    }
}
