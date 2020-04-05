package com.memad.covid_19.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.memad.covid_19.R;
import com.memad.covid_19.ViewModels.EgyptViewModel;
import com.memad.covid_19.ViewModels.WorldViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        Toolbar mainToolbar = findViewById(R.id.toolbar);
        WorldViewModel worldViewModel = new ViewModelProvider(this).get(WorldViewModel.class);
        EgyptViewModel egyptViewModel = new ViewModelProvider(this).get(EgyptViewModel.class);
        navigationView.setItemIconTintList(null);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setOnNavigationItemReselectedListener(item -> {
            if(item.getItemId() == R.id.egyptFragment){
                egyptViewModel.refresh();
            }
            else if(item.getItemId() == R.id.worldFragment){
                worldViewModel.refresh();
            }
        });

        mainToolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.about){
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
            else if(item.getItemId() == R.id.share){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                //TODO: Change the text
                sendIntent.putExtra(Intent.EXTRA_TEXT, "");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
            else if(item.getItemId() == R.id.licences){
                startActivity(new Intent(MainActivity.this, LicenceActivity.class));
            }
            return true;
        });

    }


}
