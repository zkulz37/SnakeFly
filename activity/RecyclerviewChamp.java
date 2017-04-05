package helloworld.fly.snake.lmhtalpha.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import helloworld.fly.snake.lmhtalpha.Champion;
import helloworld.fly.snake.lmhtalpha.Database;
import helloworld.fly.snake.lmhtalpha.R;
import helloworld.fly.snake.lmhtalpha.adapter.AdapterChampion;

public class RecyclerviewChamp extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    AdapterChampion adapter;
    ArrayList<Champion> list;
    ArrayList<Champion> listB;
    RecyclerView.LayoutManager layout;
    SQLiteDatabase database;
    final String DATABASE_NAME = "Champions.sqlite";

    // add Toolbar
    private Toolbar mToolbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_champ);

        addControls();
        readSQLite();
        addEvents();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("KetQua",newText);
        filter(newText);
        return true;
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
                if(listB.get(i).namE.toLowerCase().contains(s)){
                    list.add(listB.get(i));
                }
            }
            appendAddItem(list);
        }
    }
    private void appendAddItem(ArrayList<Champion> novaList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }



    public static interface ClickListener{
        public void onClick(View v,int position);
        public void onLongClick(View v, int position);

    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        GestureDetector gestureDetector;
        ClickListener clickListener;
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
            View child = rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child,rv.getChildPosition(child));
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

    private void addEvents() {

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Champion champion = list.get(position);
                String name = champion.namE;
                byte[] img = champion.imG;
                Intent intent = new Intent(RecyclerviewChamp.this,History.class);
                intent.putExtra("Name",name);
                intent.putExtra("IMG",img);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));

    }

    private void readSQLite() {
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM Champions",null);

        for(int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            String namE = cursor.getString(0);
            String nickName = cursor.getString(1);
            String adresS = cursor.getString(2);
            byte[] imGChamp = cursor.getBlob(3);

            list.add(new Champion(namE,nickName,adresS,imGChamp));
            listB.add(new Champion(namE,nickName,adresS,imGChamp));
        }
    }

    private void addControls() {
        recyclerView = (RecyclerView) findViewById(R.id.rclView);
        list = new ArrayList<>();
        listB = new ArrayList<>();
        adapter = new AdapterChampion(this,list);
        layout = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layout);
        recyclerView.setHasFixedSize(true);

        //anh xa Toolbar here
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view,menu);
        MenuItem item = menu.findItem(R.id.search_view);
        searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      /*  int id = item.getItemId();
        if(id == R.id.action_settings){
            Toast.makeText(this,"Hello.I'm Setting",Toast.LENGTH_SHORT).show();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
