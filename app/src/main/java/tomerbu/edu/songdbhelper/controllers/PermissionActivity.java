package tomerbu.edu.songdbhelper.controllers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import tomerbu.edu.songdbhelper.R;

public class PermissionActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACTS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                methodThatRequiresPermission();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_CONTACTS && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //
            methodThatRequiresPermission();
        }
        else {
            //No permission
            Toast.makeText(PermissionActivity.this, "Sorry, No Permission", Toast.LENGTH_SHORT).show();
        }
    }


    private void methodThatRequiresPermission(){
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    }
}
