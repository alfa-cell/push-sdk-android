package com.alfa.cell.push.sample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alfa.cell.push.sample.databinding.ActivityMainBinding;
import com.alfa.cell.push.sdk.MobilyPushService;
import com.alfa.cell.push.sdk.models.User;
import com.alfa.cell.push.sdk.models.register.RegisterResult;
import com.alfa.cell.push.sdk.models.user.UserResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        FirebaseInstanceId.getInstance()
                .getInstanceId()
                .addOnCompleteListener(this::onTokenComplete);
        binding.acceptButton.setOnClickListener(v -> {
            User user = new User();
            user.setUsername(binding.mainName.getText().toString());
            user.setPhone(binding.mainPhone.getText().toString());
            user.setEmail(binding.mainEmail.getText().toString());
            userRegistration(user);
        });
    }

    private void onTokenComplete(Task<InstanceIdResult> task) {
        if (!task.isSuccessful()) {
            Log.e("MainActivity", "getInstanceId failed", task.getException());
        } else {
            if (task.getResult() != null) {
                tokenRegistration(task.getResult().getToken());
            }
        }
    }

    private void userRegistration(User user) {
        MobilyPushService.registerUser(user, new MobilyPushService.UserRegisterListener() {
            @Override
            public void onRegistered(UserResult result) {
                Toast.makeText(MainActivity.this, result.getData().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tokenRegistration(String token) {
        MobilyPushService.registerToken(token, new MobilyPushService.RegisterListener() {
            @Override
            public void onRegistered(RegisterResult result) {
                Toast.makeText(MainActivity.this, result.getData().toString(), Toast.LENGTH_SHORT).show();
                binding.succesAuth.setText("Success: " + result.getData().getUid());
            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("tag", throwable.getMessage());
            }
        });
    }
}