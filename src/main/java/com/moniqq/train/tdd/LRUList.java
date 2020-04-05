package com.moniqq.train.tdd;

import java.util.LinkedList;
import java.util.List;

public class LRUList {

    private LinkedList<String> content = new LinkedList<String>();

    public List<String> content() {
        return content;
    }

    public void add(String element) {
        content.remove(element);
        content.addFirst(element);
    }
}
