package project;

public class Vehicle {

    private static int counter = 1;

    int id;
    double capacity = 500;
    double currentLoad = 0;

    public Vehicle() {
        id = counter++;
    }

    public boolean canLoad(double amount) {
        return currentLoad + amount <= capacity;
    }

    public void load(double amount) {
        currentLoad += amount;
    }

    public double remainingCapacity() {
        return capacity - currentLoad;
    }

    // ✅ BU METOT ŞART
    public void display() {
        System.out.println(
                "Araç " + id +
                " | Yük: " + currentLoad + "/" + capacity + " kg"
        );
    }
}