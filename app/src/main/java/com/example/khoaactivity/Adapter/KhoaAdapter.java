package com.example.khoaactivity.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.khoaactivity.DTO.KhoaDTO;
import com.example.khoaactivity.R;

import java.util.List;

public class KhoaAdapter extends BaseAdapter {
    List<KhoaDTO> list;
    Context context;

    public KhoaAdapter(List<KhoaDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView == null){
            row = View.inflate(context, R.layout.layout_row_khoa,null);
        }else {
            row = convertView;
        }
        KhoaDTO objkhoa = list.get(position);
        TextView tv_id = row.findViewById(R.id.tv_id);
        TextView tv_ten_khoa = row.findViewById(R.id.tv_ten_khoa);
        tv_id.setText(objkhoa.getId()+"");
        tv_ten_khoa.setText(objkhoa.getTen_khoa());

        return row;
    }
}
