package com.example.wanderlusty.utils

import org.json.JSONObject

object JsonParser {
    fun parseEntity(jsonString: String, keys: Map<String, String>): Map<String, String> {
        val jsonObject = JSONObject(jsonString)
        val entityJson = jsonObject.getJSONObject(keys["entity"]!!)

        val result = mutableMapOf<String, String>()
        keys.forEach { (key, value) ->
            if (value != "entity") {
                result[key] = entityJson.getString(value)
            }
        }

        return result
    }
}