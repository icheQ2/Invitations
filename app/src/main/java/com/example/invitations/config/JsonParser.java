package com.example.invitations.config;

import com.example.invitations.entity.Party;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonParser {
    public Party parse(InputStream inputStream) {
        try (Reader reader = new InputStreamReader(inputStream)){
            Gson gson = new Gson();
            return gson.fromJson(reader, Party.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
