package com.example.cuestionario_20

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (contexto: Context, nombre: String, factory : SQLiteDatabase.CursorFactory?, id:Int) : SQLiteOpenHelper(contexto, nombre, factory, id) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table Usuarios(usuario text primary key, password text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}