package com.example.khoaactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.khoaactivity.Adapter.KhoaAdapter;
import com.example.khoaactivity.Adapter.LopAdapter;
import com.example.khoaactivity.DAO.KhoaDAO;
import com.example.khoaactivity.DAO.LopDAO;
import com.example.khoaactivity.DTO.KhoaDTO;
import com.example.khoaactivity.DTO.LopDTO;

import java.util.List;

public class LopActivity extends AppCompatActivity {

    RecyclerView rc_lop;
    LopDAO lopDAO;
    List<LopDTO> list_lop;
    LopAdapter lopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop);

        rc_lop = findViewById(R.id.rc_lop);

        lopDAO = new LopDAO(this);
        list_lop = lopDAO.getAll();

        lopAdapter = new LopAdapter(this, list_lop);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rc_lop.setLayoutManager( linearLayoutManager );

        rc_lop.setAdapter(lopAdapter);

        //thêm
        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogAddLop();
            }
        });
    }
    void ShowDialogAddLop(){
        //định nghĩa dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(LopActivity.this);

        LayoutInflater inflater = getLayoutInflater();

        View v = inflater.inflate(R.layout.layout_dialog_add_lop, null);
        builder.setView(v);
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();//khởi tạo dialog

        //tương tác view
        EditText edTenLop = v.findViewById(R.id.ed_ten_lop);
        EditText edSiSo = v.findViewById(R.id.ed_si_so);

        Spinner sp_khoa = v.findViewById(R.id.sp_khoa);

        Button btn_save = v.findViewById(R.id.btn_save);
        Button btn_cancel = v.findViewById(R.id.btn_cancel);

        //đưa dữ liệu lên spiner
        KhoaDAO khoaDAO = new KhoaDAO(LopActivity.this);
        List<KhoaDTO> listKhoa = khoaDAO.getAll();
        KhoaAdapter khoaAdapter = new KhoaAdapter(listKhoa, LopActivity.this);

        sp_khoa.setAdapter(khoaAdapter);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_lop = edTenLop.getText().toString();
                int si_so = Integer.parseInt(edSiSo.getText().toString());
                int id_khoa = (int) sp_khoa.getSelectedItemId();

                LopDAO lopDAO1 = new LopDAO(LopActivity.this);
                LopDTO objLop = new LopDTO(ten_lop, si_so, id_khoa);//ten_lop, int si_so, int id_khoa

                int id = lopDAO1.AddRow(objLop);

                if (id > 0){

                    //load lại cv
                    list_lop.clear();
                    list_lop.addAll(lopDAO1.getAll());

                    lopAdapter.notifyDataSetChanged();
                    Toast.makeText(LopActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(LopActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}