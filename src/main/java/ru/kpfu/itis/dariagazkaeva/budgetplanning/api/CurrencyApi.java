package ru.kpfu.itis.dariagazkaeva.budgetplanning.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyApi {
    private static final String URL_PREFIX = "https://currate.ru/api/";
    private static final String API_KEY = "758cff5b3b1d005067c7c949b450e0b4";

    public static Map<String, String> getRates(List<String> pairs) throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = URL_PREFIX + "?get=rates&pairs=" + StringUtils.join(pairs, ",") + "&key=" + API_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Map<String, String> rates = new HashMap<>();

            HashMap result = new ObjectMapper().readValue(response.body().string(), HashMap.class);

            if (result.get("status").equals(200)) {
                String data = result.get("data").toString();

                String[] arrayData = data.substring(1, data.length() - 1).split(", ");
                for (String st : arrayData) {
                    rates.put(st.split("=")[0], st.split("=")[1]);
                }
            }
            return rates;
        }
    }
}
