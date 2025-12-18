package ru.practicum.shareit.util;

public abstract class IdGenerator {
    private long currentId = 0;

    public long getNextId() {
        return ++currentId;
    }
}