package com.roy.dialogtest;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mBtnLogin;

    boolean isFirstLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnLogin = (Button) findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFirstLogin) {
                    Toast.makeText(MainActivity.this, "可以登录", Toast.LENGTH_SHORT)
                         .show();
                } else {
                    showLoginDialog();
                    isFirstLogin = true;
                }

            }

        });

    }

    private void showLoginDialog() {
        Log.d("qwe", "showLoginDialog: ");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(getApplicationContext(), R.layout.dialog_setting_pswd, null);
        builder.setView(view);
        builder.show();
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_setting_pswd);
//        dialog.show();
    }
}
