package com.example.khoaactivity.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khoaactivity.DAO.KhoaDAO;
import com.example.khoaactivity.DAO.LopDAO;
import com.example.khoaactivity.DTO.KhoaDTO;
import com.example.khoaactivity.DTO.LopDTO;
import com.example.khoaactivity.LopActivity;
import com.example.khoaactivity.R;

import java.util.List;

public class LopAdapter extends RecyclerView.Adapter<LopAdapter.ViewHolderLop> {

    Context context;
    List<LopDTO> list_lop;
    //tạo contructer để truyền vào context và dữ liệu cho adapter
//    LopAdapter lopAdapter;

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
        LopDTO objLop = list_lop.get(position);
        //gán dữ liệu

        holder.tv_id.setText(objLop.getId() + "");
        holder.tv_ten_lop.setText(objLop.getTen_lop());
        holder.tv_ten_khoa.setText(objLop.getTen_khoa());

        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogUpdateLop(objLop);
            }
        });

        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogDeleteLop(objLop);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_lop.size();
    }

    public static class ViewHolderLop extends RecyclerView.ViewHolder {
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


    void ShowDialogUpdateLop(LopDTO objLop) {
        //định nghĩa dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        View v = inflater.inflate(R.layout.layout_dialog_update_lop, null);
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
        KhoaDAO khoaDAO = new KhoaDAO(context);
        List<KhoaDTO> listKhoa = khoaDAO.getAll();
        KhoaAdapter khoaAdapter = new KhoaAdapter(listKhoa, context);

        sp_khoa.setAdapter(khoaAdapter);

        //hiện thị dữ liệu text
        edTenLop.setText(objLop.getTen_lop());
        edSiSo.setText(objLop.getSi_so() + "");

        for (int i = 0; i < listKhoa.size(); i++) {
            if (listKhoa.get(i).getId() == objLop.getId_khoa()) {
                sp_khoa.setSelection(i);
            }

        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_lop = edTenLop.getText().toString();
                int si_so = Integer.parseInt(edSiSo.getText().toString());
                int id_khoa = (int) sp_khoa.getSelectedItemId();

                LopDAO lopDAO1 = new LopDAO(context);
                LopDTO objLop_Moi = new LopDTO(ten_lop, si_so, id_khoa);//ten_lop, int si_so, int id_khoa
                objLop_Moi.setId(objLop.getId());

                int kq = lopDAO1.UpdateRow(objLop_Moi);

                if (kq > 0) {
                    //load lại cv
                    list_lop.clear();
                    list_lop.addAll(lopDAO1.getAll());

                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
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

    void ShowDialogDeleteLop(LopDTO objLop) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setCancelable(false);
        builder.setMessage("Bạn có đồng ý xóa lớp: " + objLop.getTen_lop());
        builder.setPositiveButton("Dồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LopDAO lopDAO = new LopDAO(context);
                int kq = lopDAO.DeleteRow(objLop);

                if (kq > 0) {
                    //load lại cv
                    list_lop.clear();
                    list_lop.addAll(lopDAO.getAll());

                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
