package com.example.invitations.config;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.invitations.R;
import com.example.invitations.entity.Person;
import com.example.invitations.service.FetchImage;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {
    private final Context context;
    private final int resource;
    private final Handler handler;

    public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects, Handler handler) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.handler = handler;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);

        ImageView inviterPhotoView = convertView.findViewById(R.id.inviterPhoto);
        TextView inviterNameView = convertView.findViewById(R.id.inviterName);

        new FetchImage(getItem(position).getPhotoUrl(), handler, inviterPhotoView).start();
        inviterNameView.setText(getItem(position).getName());

        return convertView;
    }
}
