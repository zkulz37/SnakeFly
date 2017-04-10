package helloworld.fly.snake.giupbehoctoan;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtTime;
    TextView txtScore;
    TextView txtSo1;
    TextView txtSo2;
    TextView txtTong;

    ImageButton imgV;
    ImageButton imgX;
    ImageButton imgReset;

   // ArrayList<KetQua> list;

    int sO1;
    int sO2;
    int tOng;
    int traP;
    int diemSo =100;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        //  list = new ArrayList<>();
        txtTime = (TextView) findViewById(R.id.textViewTime);
        txtScore = (TextView) findViewById(R.id.textViewScore);
        txtSo1 = (TextView) findViewById(R.id.textViewSo1);
        txtSo2 = (TextView) findViewById(R.id.textViewSo2);
        txtTong = (TextView) findViewById(R.id.textViewTong);

        imgV = (ImageButton) findViewById(R.id.imageButtonV);
        imgX = (ImageButton) findViewById(R.id.imageButtonX);
        imgReset = (ImageButton) findViewById(R.id.imageReset);
        imgReset.setVisibility(View.GONE);
    }

    private void addEvents() {

        countDownTimerStart();
        ranDom();

        imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"V",Toast.LENGTH_SHORT).show();
                if (traP==tOng){
                    countDownTimerStop();
                    countDownTimerStart();
                    ranDom();
                    cOngDiem();

                }
                else {
                    Toast.makeText(MainActivity.this, "Sai r", Toast.LENGTH_SHORT).show();
                    countDownTimerStop();
                    anControls();
                    resetDiem();

                }
            }
        });

        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"X",Toast.LENGTH_SHORT).show();
                if(traP==tOng) {
                    Toast.makeText(MainActivity.this, "Sai r", Toast.LENGTH_SHORT).show();
                    countDownTimerStop();
                    anControls();
                    resetDiem();

                }
                else {
                    countDownTimerStop();
                    countDownTimerStart();
                    ranDom();
                    cOngDiem();

                }
            }
        });

    }

    private void countDownTimerStart() {
        countDownTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTime.setText(millisUntilFinished/1000+"s");
            }

            @Override
            public void onFinish() {
                //Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                anControls();
                resetDiem();
            }
        };
        countDownTimer.start();
    }

    private void countDownTimerStop() {
        countDownTimer.cancel();
    }

    private void ranDom() {
        Random random = new Random();
        sO1 = random.nextInt(10);
        sO2 = random.nextInt(10);
        tOng = sO1 + sO2;
        traP = tOng + random.nextInt(2);

        txtSo1.setText(sO1+"");
        txtSo2.setText(sO2+"");
        txtTong.setText(traP+"");

    }

    private void resetDiem() {
        diemSo = 100;
        txtScore.setText(diemSo+"");
    }

    private void cOngDiem() {
        diemSo +=5;
        txtScore.setText(diemSo+"");
    }

    private void anControls() {
        txtSo1.setVisibility(View.GONE);
        txtSo2.setVisibility(View.GONE);
        txtTong.setVisibility(View.GONE);
        txtTime.setVisibility(View.GONE);
        txtScore.setVisibility(View.GONE);
        imgV.setVisibility(View.GONE);
        imgX.setVisibility(View.GONE);

        imgReset.setVisibility(View.VISIBLE);
        imgReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienControls();
                countDownTimerStart();
                ranDom();
            }
        });
    }

    private void hienControls() {
        imgReset.setVisibility(View.GONE);

        txtSo1.setVisibility(View.VISIBLE);
        txtSo2.setVisibility(View.VISIBLE);
        txtTong.setVisibility(View.VISIBLE);
        txtTime.setVisibility(View.VISIBLE);
        txtScore.setVisibility(View.VISIBLE);
        imgV.setVisibility(View.VISIBLE);
        imgX.setVisibility(View.VISIBLE);

    }

}
