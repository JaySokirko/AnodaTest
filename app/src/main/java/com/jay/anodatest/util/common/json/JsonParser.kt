package com.jay.anodatest.util.common.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jay.anodatest.BaseApplication
import com.jay.anodatest.util.common.json.JsonFilesManager.getJsonDataFromAsset

class JsonParser<T> {

    fun getParsedObject(jsonFileName: String, typeToken: TypeToken<List<T>>): List<T>? {
        val jsonFileString: String? = getJsonDataFromAsset(applicationContext, jsonFileName)
        return Gson().fromJson<List<T>>(jsonFileString, typeToken.type)
    }

    companion object {
        val applicationContext: Context =
            BaseApplication.baseComponent.application.applicationContext
    }
}