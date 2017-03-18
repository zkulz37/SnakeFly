package helloworld.fly.snake.ttlmht;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Champions> list;
    ArrayList<Champions> listB;
    RecyclerView recyclerView;
    AdapterChampions adapterChampions;
    SQLiteDatabase database;
    final String DATABASE_NAME = "Champions.sqlite";
    LinearLayoutManager layoutManager;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //edtSearch.getVisibility();

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rclView);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        list = new ArrayList<>();
        listB = new ArrayList<>();
        adapterChampions = new AdapterChampions(this, list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterChampions);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(String.valueOf(s));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM Champions", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String namE = cursor.getString(0);
            String nickName = cursor.getString(1);
            String adresS = cursor.getString(2);
            byte[] imGanh = cursor.getBlob(3);
            list.add(new Champions(namE, nickName, adresS, imGanh));
            listB.add(new Champions(namE, nickName, adresS, imGanh));
            // adapterChampions.notifyDataSetChanged();
        }
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this,History.class);
                String name = list.get(position).getNamE();
                intent.putExtra("Name",name);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));
    }

    public static interface ClickListener{
        public void onClick(View v,int position);
        public void onLongClick(View v,int position);
    }
    //tao class RecyclerTouchListener de click dc item cua recycler view
    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }



    private void filter(String s) {
        s=s.toLowerCase();
        list.clear();
        if(s.length()==0){
            list.addAll(listB);
            appendAddItem(listB);
        }
        else {
            for(int i=0;i<listB.size();i++){
                if(listB.get(i).getNamE().toLowerCase().contains(s)){
                    list.add(listB.get(i));
                }
            }
            appendAddItem(list);
        }
    }

    private void appendAddItem(ArrayList<Champions> novaList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapterChampions.notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
        //anh xa

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
