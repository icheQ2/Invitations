package com.example.invitations.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FetchImage extends Thread {

    final String URL;
    final Handler handler;
    final ImageView imageView;
    Bitmap bitmap;

    public FetchImage(String URL, Handler handler, ImageView imageView) {
        this.URL = URL;
        this.handler = handler;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        InputStream inputStream;
        try {
            inputStream = new URL(URL).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        handler.post(() -> imageView.setImageBitmap(bitmap));
    }
}
