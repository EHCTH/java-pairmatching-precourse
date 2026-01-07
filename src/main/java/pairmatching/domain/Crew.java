package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Crew {
    private final DevelopType developType;
    private final String name;

    public Crew(DevelopType developType, String name) {
        this.developType = developType;
        this.name = name;
    }

    public DevelopType getDevelopType() {
        return developType;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Crew crew)) return false;
        return developType == crew.developType && Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developType, name);
    }
}
