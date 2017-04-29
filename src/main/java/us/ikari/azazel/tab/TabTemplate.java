package us.ikari.azazel.tab;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class TabTemplate {

    @Getter private final List<String> left;
    @Getter private final List<String> middle;
    @Getter private final List<String> right;

    public TabTemplate() {
        left = new ArrayList<>();
        middle = new ArrayList<>();
        right = new ArrayList<>();
    }

    public TabTemplate left(String string) {
        return left(left.size(), string);
    }

    public TabTemplate middle(String string) {
        return middle(middle.size(), string);
    }

    public TabTemplate right(String string) {
        return right(right.size(), string);
    }

    public TabTemplate left(int index, String string) {
        left.add(index, string);
        return this;
    }

    public TabTemplate middle(int index, String string) {
        middle.add(index, string);
        return this;
    }

    public TabTemplate right(int index, String string) {
        right.add(index, string);
        return this;
    }

}
