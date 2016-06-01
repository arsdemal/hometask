package ru.mail.arseniy.hometask;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageResource(R.drawable.smiley);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(0);
                Intent intent = new Intent(MainActivity.this, ListViewBaseAdapterActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }

        }, 2000);


    }


}
