package me.tonoy;

import java.util.Collections;
import java.util.List;

public record ImmutablePerson(String name, int age, List<String> friends) {
    @Override
    public List<String> friends() {
        return Collections.unmodifiableList(friends);
    }
}
