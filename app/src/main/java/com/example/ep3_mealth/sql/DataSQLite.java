package com.example.ep3_mealth.sql;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DataSQLite extends SQLiteOpenHelper  {

    public static final int INCOME = 1;
    public static final int EXPENSE = -1;

    public DataSQLite(@Nullable Context context) {
        super(context, "MisGastos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Transacciones(" +
                "TransaccionID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Fecha DATETIME DEFAULT CURRENT_TIMESTAMP,"+
                "Descripcion TEXT," +
                "Monto REAL," +
                "Tipo INTEGER)");
    }

    public void insertTransaction(String description, double amount, int type){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Descripcion", description);
        contentValues.put("Monto", amount);
        contentValues.put("Tipo", type);

        sqLiteDatabase.insert("Transacciones", null, contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<HashMap<String, String>> readTransactions(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursorTransactions = sqLiteDatabase.rawQuery("SELECT * FROM Transacciones", null);
        ArrayList<HashMap<String, String>> transactions = new ArrayList();
        if(cursorTransactions.moveToFirst()){
           do {
               HashMap<String, String> data = new HashMap<>();
               data.put("id", cursorTransactions.getString(0));
               data.put("datetime", cursorTransactions.getString(1));
               data.put("description", cursorTransactions.getString(2));
               data.put("amount", cursorTransactions.getString(3));
               data.put("type", cursorTransactions.getString(4));
               transactions.add(data);
           } while(cursorTransactions.moveToNext());
        }

        cursorTransactions.close();
        return transactions;
    }

    public ArrayList<HashMap<String, String>> readExpenses(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursorExpenses = sqLiteDatabase.rawQuery("SELECT * FROM Transacciones WHERE Tipo = -1", null);
        ArrayList<HashMap<String, String>> expenses = new ArrayList();
        if(cursorExpenses.moveToFirst()){
            do {
                HashMap<String, String> data = new HashMap<>();
                data.put("id", cursorExpenses.getString(0));
                data.put("datetime", cursorExpenses.getString(1));
                data.put("description", cursorExpenses.getString(2));
                data.put("amount", cursorExpenses.getString(3));
                data.put("type", cursorExpenses.getString(4));
                expenses.add(data);
            } while(cursorExpenses.moveToNext());
        }

        cursorExpenses.close();
        return expenses;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
