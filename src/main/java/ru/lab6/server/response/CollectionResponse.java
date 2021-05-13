package ru.lab6.server.response;

import ru.lab6.common.humanbeing.HumanBeing;

import java.util.LinkedHashSet;
import java.util.PriorityQueue;


//show, filter_greater_than_mood, print_ascending
public class CollectionResponse implements Response{
    private LinkedHashSet<HumanBeing> collection = new LinkedHashSet<HumanBeing>();

    CollectionResponse(PriorityQueue<HumanBeing> collection){
        this.collection.addAll(collection);
    }

    public String json() {
        return null;
    }
}
