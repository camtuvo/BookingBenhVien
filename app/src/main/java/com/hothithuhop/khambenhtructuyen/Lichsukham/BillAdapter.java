package com.hothithuhop.khambenhtructuyen.Lichsukham;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hothithuhop.khambenhtructuyen.R;
import com.hothithuhop.khambenhtructuyen.model.BillResponse;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private ArrayList<BillResponse> arrayList;
    private Context context;

    public BillAdapter(Context context, ArrayList<BillResponse> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvHoadon.setText("HD"+arrayList.get(position).getIdHD()+"" +"HPBT");
        holder.tvngay.setText(arrayList.get(position).getNgaykham());
        holder.tvgio.setText(arrayList.get(position).getGiokham());
        holder.tvbs.setText(arrayList.get(position).getTenBS());
        holder.tvbhyt.setText(arrayList.get(position).getBhyt());
        holder.tvtienkham.setText(arrayList.get(position).getTongtien()+"");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageButton img_back_licsukham;
        public TextView tvngay, tvgio, tvbs, tvbhyt, tvtienkham, tvHoadon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_back_licsukham = itemView.findViewById(R.id.img_back_licsukham);
            tvngay = itemView.findViewById(R.id.tvngay);
            tvgio = itemView.findViewById(R.id.tvgio);
            tvbs = itemView.findViewById(R.id.tvbs);
            tvbhyt = itemView.findViewById(R.id.tvbhyt);
            tvtienkham = itemView.findViewById(R.id.tvtienkham);
            tvHoadon = itemView.findViewById(R.id.tvHoadon);
        }
        @Override
        public void onClick(View view) {

        }
    }
}
