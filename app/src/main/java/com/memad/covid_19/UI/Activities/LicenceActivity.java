
package com.memad.covid_19.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.memad.covid_19.R;

public class LicenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_licence);
        Toolbar toolbar = findViewById(R.id.licence_toolbar);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }
}
