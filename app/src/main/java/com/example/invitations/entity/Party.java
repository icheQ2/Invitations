package com.example.invitations.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Party {
    private String partyImageUrl;
    private String partyName;
    private Person inviter;
    private List<Person> guests;
}
