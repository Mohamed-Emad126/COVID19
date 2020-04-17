package com.memad.covid_19.UI.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.memad.covid_19.R;


public class EmergencyFragment extends Fragment {


    public EmergencyFragment() {
        // Required empty public constructor
    }

    int btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_emergency, container, false);

        MaterialButton callButton105 = rootView.findViewById(R.id._105_number_call);
        MaterialButton callButton1553 = rootView.findViewById(R.id._15535_number_call);
        MaterialButton callButton080 = rootView.findViewById(R.id._080_number_call);
        MaterialButton callButton022 = rootView.findViewById(R.id._022_number_call);

        callButton105.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(requireActivity(),
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                btn = 1;
                requestPhoneCallPermission();
            } else {
                dialPhoneNumber("105");
            }
        });

        callButton1553.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(requireActivity(),
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                btn = 2;
                requestPhoneCallPermission();
            } else {
                dialPhoneNumber("15335");
            }
        });
        callButton080.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(requireActivity(),
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                btn = 3;
                requestPhoneCallPermission();
            } else {
                dialPhoneNumber("080-8880700");
            }
        });
        callButton022.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(requireActivity(),
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                btn = 4;
                requestPhoneCallPermission();
            } else {
                dialPhoneNumber("0220816831");
            }
        });

        return rootView;
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void requestPhoneCallPermission() {

        if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {

            new AlertDialog.Builder(requireActivity())
                    .setTitle(R.string.permission_nedded)
                    .setMessage(R.string.permission_reason)
                    .setPositiveButton(R.string.ok, (dialog, which) -> requestPermissions(
                            new String[]{Manifest.permission.CALL_PHONE}, 70))
                    .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss())
                    .create().show();
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 70);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 70) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (btn == 1) {
                    dialPhoneNumber("105");
                } else if (btn == 2) {
                    dialPhoneNumber("15335");
                } else if (btn == 3) {
                    dialPhoneNumber("080-8880700");
                } else if (btn == 4) {
                    dialPhoneNumber("0220816831");
                }
                Toast.makeText(requireActivity(), R.string.granted, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireActivity(), R.string.denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
