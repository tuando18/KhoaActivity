package com.example.khoaactivity.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khoaactivity.DTO.LopDTO;
import com.example.khoaactivity.R;

import java.util.List;

public class LopAdapter extends RecyclerView.Adapter<LopAdapter.ViewHolderLop> {

    Context context;
    List<LopDTO> list_lop;
    //tạo contructer để truyền vào context và dữ liệu cho adapter


    public LopAdapter(Context context, List<LopDTO> list_lop) {
        this.context = context;
        this.list_lop = list_lop;
    }

    @NonNull
    @Override
    public ViewHolderLop onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_row_lop, parent, false);

        ViewHolderLop holder = new ViewHolderLop(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLop holder, int position) {
        LopDTO objLop= list_lop.get(position);
        //gán dữ liệu

        holder.tv_id.setText(objLop.getId()+"");
        holder.tv_ten_lop.setText(objLop.getTen_lop());
        holder.tv_ten_khoa.setText(objLop.getTen_khoa());
    }

    @Override
    public int getItemCount() {
        return list_lop.size();
    }

    public static class ViewHolderLop extends RecyclerView.ViewHolder{
        TextView tv_id, tv_ten_lop, tv_ten_khoa;
        ImageView btn_xoa, btn_update;


        public ViewHolderLop(@NonNull View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_id_lop);
            tv_ten_lop = itemView.findViewById(R.id.tv_ten_lop);
            tv_ten_khoa = itemView.findViewById(R.id.tv_ten_khoa);

            btn_xoa = itemView.findViewById(R.id.btn_remove);
            btn_update = itemView.findViewById(R.id.btn_update);

        }
    }

}
