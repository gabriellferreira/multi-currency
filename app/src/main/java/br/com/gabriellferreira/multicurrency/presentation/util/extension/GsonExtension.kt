package br.com.gabriellferreira.multicurrency.presentation.util.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String?): T? = this.fromJson<T>(json, object : TypeToken<T>() {}.type)