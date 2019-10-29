package com.andi.kotasejarah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListKotaAdapter extends RecyclerView.Adapter<ListKotaAdapter.ListViewHolder> {
    private ArrayList<Kota> listKota;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }
    public ListKotaAdapter(ArrayList<Kota> list){
        this.listKota = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_kota, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListKotaAdapter.ListViewHolder holder, int position) {
        Kota kota = listKota.get(position);
        holder.tvKota.setText(kota.getName());
        holder.tvDetail.setText(kota.getDetail());
        Glide.with(holder.itemView.getContext())
                .load(kota.getPhoto())
                .apply(new RequestOptions().override(60,55))
                .into(holder.Gambar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listKota.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKota.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvKota, tvDetail;
        ImageView Gambar;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKota = itemView.findViewById(R.id.tv_name);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            Gambar = itemView.findViewById(R.id.img_kota);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Kota data);
    }
}
