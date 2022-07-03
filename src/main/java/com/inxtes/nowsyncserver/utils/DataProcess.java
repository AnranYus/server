package com.inxtes.nowsyncserver.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataProcess {

    /**
     * @param content 数据
     * @param tClass  对象Class
     *                Note 不可以使用Gson().fromJson(ArrayList<T>()):
     *                Exception:com.google.gson.internal.LinkedTreeMap cannot be cast to xxx
     */
    public static <T> List<T> JsonToObj(String content, Class<T> tClass) throws IOException {
        Gson gson = new Gson();
        gson.getAdapter(JsonElement.class).fromJson(content);
        JsonArray array = JsonParser.parseString(content).getAsJsonArray();
        List<T> list = new ArrayList<>();

        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, tClass));
        }

        return list;
    }
}
