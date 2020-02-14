package com.example.relaystore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Order_Finalisation_Activity extends AppCompatActivity {
    Button btn_return;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_finalisation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_return = findViewById(R.id.btn_return);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Order_Finalisation_Activity.this, "Thankyou...", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(Order_Finalisation_Activity.this,Order_list_Activity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            }
        });

    }
}
