package Resizeable;

public class Shape {
    private String color = "green";
    private boolean filled = true;

    public Shape() {
    }

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isFilled() {
        return this.filled;
    }

    public String getColor() {
        return this.color;
    }
    @Override
    public String toString() {
        return "A Shape with color of " + getColor()
                + " and " + (isFilled() ? "filled" : "not filled");
    }
}
