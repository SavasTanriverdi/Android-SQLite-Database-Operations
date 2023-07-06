package com.savastanriverdi.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hataları yakalamak için kullanılır
        // Veritabanlarında çalışırken try-catch kullanmayı öneririm
        try {
            //Veritabanı açtık
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);

            //Bir tablo oluşturmamız gerekiyor bunun içinde şöyle yapıyoruz
            //CREATE TABLE IF NOT EXISTS  Bir tablo oluştur eğer yoksa
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR, age INTEGER)");
            //VARCHAR: String demek SQLite da
            //INTEGER: int demek

            //GÜNCELEME
            //database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'");
            //database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3");

            //SİLME
            database.execSQL("DELETE FROM musicians WHERE id = 2");

            //database.execSQL("INSERT INTO musicians (name, age)VALUES ('James',50)");
            //database.execSQL("INSERT INTO musicians (name, age)VALUES ('Lars',60)");
            //database.execSQL("INSERT INTO musicians (name, age)VALUES ('Kirk',55)");
            //INSERT INTO: Bir tabloya bir değer kayıt ederken kullanılır.
            //INSERT: YERLEŞTİRMEK DEMEK

            //Bunu nasıl veritabanından okuruz?
            //Okumak için "Cursor" gerekiyor
            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);

            //Benim bu Cursora hangi sütunlara gideceğimi söylemem gerek
            //getColumnIndex(): objenin kaçıncı sütunda olduğunu gösterir.
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getString(ageIx));
                System.out.println("Id: " + cursor.getInt(idIx));
            }
            cursor.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
