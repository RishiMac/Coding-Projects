/**
 * This file defines the Animals object.
 * @author Rishi.
 * @version 1.
 */
public class Animals {
    /**
     * main method.
     * @param args parameter.
     */
    public static void main(String[] args) {
        //Main method.
    }
    private String name = "Empty";
    private  String health;
    private  Animal animal;
    /**
     * Animals constructor.
     * @param name name.
     * @param health health.
     * @param animal animal.
     */
    public Animals(String name, String health, Animal animal) {
        if (name != null && name.trim().length() > 0) {
            this.name = name;
        } else {
            this.name = "No Name Yet";
        }
        if (health != null && health.trim().length() > 0 && (health.equalsIgnoreCase("1")
            || health.equalsIgnoreCase("2") || health.equalsIgnoreCase("3")
                || health.equalsIgnoreCase("4") || health.equalsIgnoreCase("5"))) {
            this.health = health;
        } else {
            this.health = "5";
        }
        this.animal = animal;
    }
    /**
     * toString method.
     * @return toString.
     */
    public String toString() {
        return  this.name + "\n Health: " + this.health + "\n Animal: " + this.animal;
    }
}