package com.example.calenderapp;

import java.util.Comparator;

public class EventPriorityComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        return event2.getPriority().compareTo(event1.getPriority());
    }
}
