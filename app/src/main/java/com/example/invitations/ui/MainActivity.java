package com.example.invitations.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.invitations.R;
import com.example.invitations.config.JsonParser;
import com.example.invitations.entity.Party;
import com.example.invitations.entity.Person;
import com.example.invitations.service.FetchImage;
import com.example.invitations.config.PersonAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final Handler mainHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonParser jsonParser = new JsonParser();
        Party party = jsonParser.parse(getResources().openRawResource(R.raw.party));

        String partyImageUrl = party.getPartyImageUrl();
        String partyName = party.getPartyName();
        Person inviter = party.getInviter();
        List<Person> guests = party.getGuests();

        ImageView partyImageView = findViewById(R.id.partyImage);
        TextView partyNameView = findViewById(R.id.partyName);
        ImageView inviterPhotoView = findViewById(R.id.inviterPhoto);
        TextView inviterNameView = findViewById(R.id.inviterName);
        ListView guestsView = findViewById(R.id.guestsList);

        new FetchImage(partyImageUrl, mainHandler, partyImageView).start();
        partyNameView.setText(partyName);
        new FetchImage(inviter.getPhotoUrl(), mainHandler, inviterPhotoView).start();
        inviterNameView.setText(inviter.getName());
        PersonAdapter personAdapter = new PersonAdapter(this, R.layout.guest, guests, mainHandler);
        guestsView.setAdapter(personAdapter);
    }
}