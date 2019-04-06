package com.example.calenderapp;

import java.util.Comparator;

public class EventDateComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        return event1.getDate().compareTo(event2.getDate());
    }
}
