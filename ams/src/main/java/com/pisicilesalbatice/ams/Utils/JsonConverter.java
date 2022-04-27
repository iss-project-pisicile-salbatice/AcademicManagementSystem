package com.pisicilesalbatice.ams.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter
{
    public static <T> Object jsonToObject(String json, Class<T> clazz) {
        if (json == null)
            throw new IllegalArgumentException("null cannot be converted to Object");
        Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("dd-MMM-yyyy").create();
        return gson.fromJson(json, clazz);
    }
}