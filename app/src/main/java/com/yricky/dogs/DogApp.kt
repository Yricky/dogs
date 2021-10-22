package com.yricky.dogs

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yricky.android.utils.ThreadUtils
import java.io.File
import java.lang.RuntimeException

/**
 * @author  Yricky
 * @date  2021/10/22 下午7:36
 */
class DogApp:Application() {
    companion object{
        lateinit var app:DogApp
    }

    override fun onCreate() {
        super.onCreate()
        ThreadUtils.init()
        app = this
    }

}


val gson: Gson by lazy{ GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() }
val dogsDir: File get() = DogApp.app.getExternalFilesDir("Dogs") ?: throw RuntimeException("External dir is null!")