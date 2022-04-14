package edu.neu.madcourse.paws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Button next_button = (Button) findViewById(R.id.button_next);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPetSetting();
            }
        });
    }

    public void openPetSetting(){
        Intent intent = new Intent(this,PetSettingActivity.class);
        startActivity(intent);
    }
}