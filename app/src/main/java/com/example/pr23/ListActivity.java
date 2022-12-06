package com.example.pr23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        View view = findViewById(R.id.list_layout);
        View scrollview = findViewById(R.id.scrollview);
        view.setOnTouchListener(new OnSwipeTouchListener(ListActivity.this) {
            @Override
            public void onSwipeRight() {
                Intent i = new Intent(ListActivity.this, MainActivity.class);
                startActivity(i);
            }

        });
        scrollview.setOnTouchListener(new OnSwipeTouchListener(ListActivity.this) {
            @Override
            public void onSwipeRight() {
                Intent i = new Intent(ListActivity.this, MainActivity.class);
                startActivity(i);
            }

        });

    }
    public void onClick(View view) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS math (id INTEGER , name TEXT, time INTEGER)");
        db.execSQL("INSERT OR IGNORE INTO math VALUES (1,'Некоторые общематематические понятия',16), (2,'Действительные (вещественные) числа',12), (3,'Предел',20), (4,'Непрерывные функции',12), (5,'Дифференциальные исчисления',24),(6,'Интеграл',24),(7,'Функции многих переменных',12);");
        Cursor query = db.rawQuery("SELECT * FROM math;", null);
        TextView textView = findViewById(R.id.tv_db);
        textView.setText("");
        while (query.moveToNext()) {
            String id = query.getString(0);
            String name = query.getString(1);
            String time = query.getString(2);
            textView.append("Номер раздела: " + id + "\nНазвание: " + name + "\nДлительность обучения: " + time + " ч." + "\n\n\n");
        }
        query = db.rawQuery("SELECT COUNT(*) FROM math;", null);
        if(query.moveToFirst()){
            textView.append("\n"+"Количество записей: " + query.getString(0) + "\n");
        }
        query.close();
        db.execSQL("delete from math");
        db.close();
    }
}