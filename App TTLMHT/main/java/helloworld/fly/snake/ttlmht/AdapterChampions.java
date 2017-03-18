package helloworld.fly.snake.ttlmht;

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

/**
 * Created by LaptopF1 on 3/17/2017.
 */

public class AdapterChampions extends RecyclerView.Adapter<AdapterChampions.MyViewHolder> {


    Context context;
    ArrayList<Champions> list;

    public AdapterChampions(Context context, ArrayList<Champions> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.rclview_item,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Champions champions = list.get(position);
        String namE = champions.getNamE();
        String nickName = champions.getNickName();
        String adresS = champions.getAdresS();
        byte[] imGanh = champions.getImG();
        holder.txtName.setText(namE);
        holder.txtNickName.setText(nickName);
        holder.txtAdress.setText(adresS);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imGanh,0,imGanh.length);
        holder.imGGG.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imGGG;
        TextView txtName;
        TextView txtNickName;
        TextView txtAdress;
        public MyViewHolder(View itemView) {
            super(itemView);
            imGGG = (ImageView) itemView.findViewById(R.id.imageImg);
            txtName = (TextView) itemView.findViewById(R.id.textViewName);
            txtNickName = (TextView) itemView.findViewById(R.id.textViewNickName);
            txtAdress = (TextView) itemView.findViewById(R.id.textViewAdress);
        }
    }
}
