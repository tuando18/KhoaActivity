package com.example.khoaactivity.DAO;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khoaactivity.DTO.KhoaDTO;
import com.example.khoaactivity.Dbhelper.MyDbHelper;

import java.util.ArrayList;
import java.util.List;

public class KhoaDAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase db;

    public KhoaDAO(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public int AddRow (KhoaDTO objkhoa){
        ContentValues values = new ContentValues();
        values.put("ten_khoa",objkhoa.getTen_khoa());
        return (int) db.insert("tb_khoa",null,values);
    }
    public int UpdateRow (KhoaDTO objkhoa){
        ContentValues values = new ContentValues();
        values.put("ten_khoa",objkhoa.getTen_khoa());
        String[] dieukien = new String[]{String.valueOf(objkhoa.getId())};
        return db.update("tb_khoa",values,"id=?",dieukien);
    }
    public int DeleteRow (KhoaDTO objkhoa){
        String[] dieukien = new String[]{String.valueOf(objkhoa.getId())};
        return db.delete("tb_khoa","id=?",dieukien);
    }
    public List<KhoaDTO> getAll(){
        List<KhoaDTO> list_khoa = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_khoa",null);
        if(c != null && c.getCount()>0){
            c.moveToFirst();
         while (!c.isAfterLast()){
            int id_khoa = c.getInt(0);
            String ten_khoa = c.getString(1);
            KhoaDTO khoaDTO = new KhoaDTO(id_khoa,ten_khoa);
            list_khoa.add(khoaDTO);
            c.moveToNext();
         }
        }
        return list_khoa;

    }
}
