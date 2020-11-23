package springref.model;

import java.util.Objects;

public class Widget {

    private String name;
    private int size;

    public Widget() {}

    public Widget(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Widget widget = (Widget) o;
        return size == widget.size &&
                Objects.equals(name, widget.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}

