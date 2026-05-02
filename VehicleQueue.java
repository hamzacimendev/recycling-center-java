package project;

import java.util.LinkedList;
import java.util.Queue;

public class VehicleQueue {

    Queue<Vehicle> queue = new LinkedList<>();

    public void enqueue(Vehicle v) {
        queue.add(v);
    }

    public Vehicle peek() {
        return queue.peek();
    }

    public Vehicle poll() {
        return queue.poll();
    }

    public void display() {
        if (queue.isEmpty()) {
            System.out.println("Araç yok.");
            return;
        }

        for (Vehicle v : queue) {
            v.display(); // <-- Vehicle.display() ÇAĞRILIYOR
        }
    }
}