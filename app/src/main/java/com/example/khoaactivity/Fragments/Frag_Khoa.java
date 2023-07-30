package com.example.khoaactivity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.khoaactivity.Adapter.KhoaAdapter;
import com.example.khoaactivity.DAO.KhoaDAO;
import com.example.khoaactivity.DTO.KhoaDTO;
import com.example.khoaactivity.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Frag_Khoa extends Fragment {
    TextInputLayout ed_tenkhoa;
    Button btn_them,btn_sua,btn_xoa;
    ListView lv_khoa;
    KhoaAdapter khoaAdapter;
    KhoaDAO khoaDAO;
    KhoaDTO objCurrentKhoa;
    List<KhoaDTO> list_khoa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Gắn layout cho fragments ở đây
        View v = inflater.inflate(R.layout.layout_frag_khoa, container, false);
        return v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ed_tenkhoa = view.findViewById(R.id.ed_tenkhoa);
        btn_them = view.findViewById(R.id.btn_them);
        btn_sua = view.findViewById(R.id.btn_sua);
        btn_xoa = view.findViewById(R.id.btn_xoa);
        lv_khoa = view.findViewById(R.id.lv_khoa);

        khoaDAO = new KhoaDAO(getContext());
        list_khoa = khoaDAO.getAll();
        khoaAdapter = new KhoaAdapter(list_khoa,getContext());

        lv_khoa.setAdapter(khoaAdapter);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_khoa = ed_tenkhoa.getEditText().getText().toString();
                KhoaDTO khoaDTO = new KhoaDTO(ten_khoa);
                int id_moi = khoaDAO.AddRow(khoaDTO);
                if(id_moi >0 ){
                    list_khoa.clear();
                    list_khoa.addAll(khoaDAO.getAll());
                    khoaAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Không Thêm Được", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lv_khoa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                objCurrentKhoa = list_khoa.get(position);
                ed_tenkhoa.getEditText().setText(objCurrentKhoa.getTen_khoa());
                return true;
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_khoa = ed_tenkhoa.getEditText().getText().toString();
                objCurrentKhoa.setTen_khoa(ten_khoa);
                int kq = khoaDAO.UpdateRow(objCurrentKhoa);
                if(kq >0 ){
                    list_khoa.clear();
                    list_khoa.addAll(khoaDAO.getAll());
                    khoaAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Update Thành Công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Không Update Được", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int kq = khoaDAO.DeleteRow(objCurrentKhoa);
                if(kq >0 ){
                    list_khoa.clear();
                    list_khoa.addAll(khoaDAO.getAll());
                    khoaAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    ed_tenkhoa.getEditText().setText("");
                    objCurrentKhoa = null;
                }else {
                    Toast.makeText(getContext(), "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
