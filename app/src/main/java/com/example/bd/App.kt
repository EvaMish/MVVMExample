package com.example.bd

import android.app.Application
import com.example.bd.data.AppDB

class App : Application() {
    val database by lazy { AppDB.createDB(this) }
}