
public class Car {

    private String color;
    private String make;
    private String model;

    public final static String COLOR = "color";
    public final static String MAKE = "make";
    public final static String MODEL = "model";

    public Car(String color, String make, String model){
        this.color = color;
        this.make = make;
        this.model = model;
    }

    public Car(){}

    public Car(String color, String make){
        this.color = color;
        this.make = make;
    }

    public String getProperty(String property){
        if(property.equals(COLOR)) return getColor();
        if(property.equals(MAKE)) return getMake();
        if(property.equals(MODEL)) return getModel();
        return null;
    }

    public void print() {
        System.out.println("Make: " + make + " Color: " + color);
    }

    public String getColor() {
        return color;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    /** I have not included setters (trivial), as the prompt was unclear as to whether the car objects themselves
     *  were to be considered unmodifiable, or rather the list containing the car objects was unmodifiable.
     */
}
