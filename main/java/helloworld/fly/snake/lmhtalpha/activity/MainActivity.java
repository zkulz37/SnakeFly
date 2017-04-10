package helloworld.fly.snake.lmhtalpha.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import helloworld.fly.snake.lmhtalpha.R;

public class MainActivity extends AppCompatActivity {
    ImageView imgBia;
    Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBia = (ImageView) findViewById(R.id.imageView);
        btnNext = (Button) findViewById(R.id.button);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RecyclerviewChamp.class);
                startActivity(intent);
            }
        });

    }
}
