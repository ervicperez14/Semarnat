package semarnat.mac.ervic.nexura.semarnat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ervic on 23/08/17.
 */

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private ObjetoMensaje[] mDataOriginal;
    private Context context;


    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView mess_code, mess_text, mess_date;


        public DataObjectHolder(View itemView) {
            super(itemView);
            mess_code = (TextView) itemView.findViewById(R.id.id_mensaje);
            mess_text = (TextView) itemView.findViewById(R.id.tv_mensaje);
            mess_date = (TextView) itemView.findViewById(R.id.fecha);

        }


        @Override
        public void onClick(View view) {
            
        }

    }

    public MyRecyclerViewAdapter(Context contexts, ObjetoMensaje[] myDataset) {
        this.context = contexts;
        this.mDataOriginal = myDataset;

    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);

        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {


        holder.mess_code.setText(mDataOriginal[position].getMess_code());
        holder.mess_text.setText(mDataOriginal[position].getMess_text());
        String date = mDataOriginal[position].getMess_date();
        char year[] = new char[4];
        date.getChars(0, 4, year, 0);
        char month[] = new char[4];
        date.getChars(4, 6, month, 0);
        char day[] = new char[4];
        date.getChars(6,8, day, 0);
        holder.mess_date.setText(String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day));


    }

    @Override
    public int getItemCount() {
        return mDataOriginal.length;
    }




}