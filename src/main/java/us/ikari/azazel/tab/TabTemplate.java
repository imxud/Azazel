package us.ikari.azazel.tab;

import lombok.Getter;

public class TabTemplate {

    @Getter private final String[] left;
    @Getter private final String[] middle;
    @Getter private final String[] right;

    public TabTemplate() {
        left = new String[20];
        middle = new String[20];
        right = new String[20];
    }

    public TabTemplate left(int index, String string) {
        left[index] = string;
        return this;
    }

    public TabTemplate middle(int index, String string) {
        middle[index] = string;
        return this;
    }

    public TabTemplate right(int index, String string) {
        right[index] = string;
        return this;
    }

}
