package com.example.relaystore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfile_Activity extends AppCompatActivity {

 //   Button btnValidate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
/*
        btnValidate = findViewById(R.id.btn_validate);
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile_Activity.this,Order_list_Activity.class);
                startActivity(intent);
            }
        });*/
    }
}
