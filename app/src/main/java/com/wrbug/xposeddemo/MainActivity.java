package com.wrbug.xposeddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, isActive() ? "Module is active!": "Module is not active!", Toast.LENGTH_SHORT).show();
    }

    public boolean isActive() {
        return false;
    }
}
