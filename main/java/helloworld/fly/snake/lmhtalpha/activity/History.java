package helloworld.fly.snake.lmhtalpha.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import helloworld.fly.snake.lmhtalpha.Database;
import helloworld.fly.snake.lmhtalpha.R;

public class History extends AppCompatActivity {
    TextView txtHistory;
    ImageView imgHistory;
    TextView txtTruyenThuyet;
    SQLiteDatabase database;
    final String DATABASE_NAME = "TruyenThuyet.sqlite";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        addControls();
        readData();
    }

    private void readData() {
        String namE = getIntent().getExtras().getString("Name");
        byte[] img = getIntent().getByteArrayExtra("IMG");
        txtHistory.setText(namE);
        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
        imgHistory.setImageBitmap(bitmap);

        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM TruyenThuyet WHERE Name =?",new String[]{namE});
        cursor.moveToFirst();

        String tThuyet = cursor.getString(1);
        txtTruyenThuyet.setText(tThuyet);
        txtTruyenThuyet.setMovementMethod(new ScrollingMovementMethod());
        }


    private void addControls() {
        txtHistory = (TextView) findViewById(R.id.txtHistory);
        txtTruyenThuyet = (TextView) findViewById(R.id.txtTruyenThuyet);
        imgHistory = (ImageView) findViewById(R.id.imgHistory);

    }
}
