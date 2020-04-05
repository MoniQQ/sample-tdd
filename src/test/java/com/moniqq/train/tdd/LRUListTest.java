package com.moniqq.train.tdd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LRUListTest {

    @Test
    public void IsEmptyOnCreation() {
        LRUList lruList = new LRUList();
        assertThat(lruList.content()).isEmpty();
    }

    @Test
    public void AddOneElement() {
        //Given
        LRUList lruList = new LRUList();

        //When
        lruList.add("Cucu");

        //Then
        assertThat(lruList.content()).containsExactly("Cucu");
    }

    @Test
    public void AddTwoElements() {
        //Given
        LRUList lruList = new LRUList();

        //When
        lruList.add("Cucu");
        lruList.add("Mucu");

        //Then
        assertThat(lruList.content()).containsExactly("Mucu", "Cucu");
    }

    @Test
    public void AddTwoIdenticalElements() {
        //Given
        LRUList lruList = new LRUList();

        //When
        lruList.add("Cucu");
        lruList.add("Cucu");

        //Then
        assertThat(lruList.content()).containsExactly("Cucu");
    }

    @Test
    public void AddMultipleElements() {
        //Given
        LRUList lruList = new LRUList();
        List<String> strings = Arrays.asList("Cucu", "Ion", "Cucu", "Maria", "Gigel", "Maria", "Cucu");

        //When
        for (String string : strings) {
            lruList.add(string);
        }

        //Then
        assertThat(lruList.content()).containsExactly("Cucu", "Maria", "Gigel", "Ion");
    }

    @Test
    public void AddNull() {
        //Given
        LRUList lruList = new LRUList();

        //When
        lruList.add(null);

        //Then
        assertThat(lruList.content().isEmpty());
    }
}
