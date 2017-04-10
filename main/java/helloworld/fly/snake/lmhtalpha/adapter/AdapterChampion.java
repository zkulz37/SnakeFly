package helloworld.fly.snake.lmhtalpha.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import helloworld.fly.snake.lmhtalpha.Champion;
import helloworld.fly.snake.lmhtalpha.R;

/**
 * Created by LaptopF1 on 3/31/2017.
 */

public class AdapterChampion extends RecyclerView.Adapter<AdapterChampion.MyViewHolder>{

    Context context;
    ArrayList<Champion> list;
    LayoutInflater inflater;

    public AdapterChampion(Context context, ArrayList<Champion> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_recyclerview,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //anh xa xong - set here
        Champion champion = list.get(position);
        String namE = champion.namE;
        String nickName = champion.nickName;
        String adresS = champion.adresS;
        byte[] imGChamp = champion.imG;
        holder.txtName.setText(namE);
        holder.txtNickName.setText(nickName);
        holder.txtAdress.setText(adresS);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imGChamp,0,imGChamp.length);
        holder.imgChamp.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtNickName;
        TextView txtAdress;
        ImageView imgChamp;
        public MyViewHolder(View itemView) {
            super(itemView);
            // anh xa here
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtNickName = (TextView) itemView.findViewById(R.id.txtNickName);
            txtAdress = (TextView) itemView.findViewById(R.id.txtAdress);
            imgChamp = (ImageView) itemView.findViewById(R.id.imgChamp);
        }
    }
}
