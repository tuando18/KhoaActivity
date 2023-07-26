package com.example.khoaactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khoaactivity.Adapter.KhoaAdapter;
import com.example.khoaactivity.DAO.KhoaDAO;
import com.example.khoaactivity.DTO.KhoaDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class KhoaActivity extends AppCompatActivity {
    TextInputLayout ed_tenkhoa;
    Button btn_them,btn_sua,btn_xoa;
    ListView lv_khoa;
    KhoaAdapter khoaAdapter;
    KhoaDAO khoaDAO;
    KhoaDTO objCurrentKhoa;
    List<KhoaDTO> list_khoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoa);

        ed_tenkhoa = findViewById(R.id.ed_tenkhoa);
        btn_them = findViewById(R.id.btn_them);
        btn_sua = findViewById(R.id.btn_sua);
        btn_xoa = findViewById(R.id.btn_xoa);
        lv_khoa = findViewById(R.id.lv_khoa);

        khoaDAO = new KhoaDAO(this);
        list_khoa = khoaDAO.getAll();
        khoaAdapter = new KhoaAdapter(list_khoa,this);

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
                    Toast.makeText(KhoaActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(KhoaActivity.this, "Không Thêm Được", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(KhoaActivity.this, "Update Thành Công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(KhoaActivity.this, "Không Update Được", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(KhoaActivity.this, "Delete Thành Công", Toast.LENGTH_SHORT).show();
                    ed_tenkhoa.getEditText().setText("");
                    objCurrentKhoa = null;
                }else {
                    Toast.makeText(KhoaActivity.this, "Không Delete Được", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}