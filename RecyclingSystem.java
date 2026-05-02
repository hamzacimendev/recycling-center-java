package project;

import java.util.HashMap;

public class RecyclingSystem {

    VehicleQueue vehicleQueue = new VehicleQueue();
    WasteLinkedList wasteList = new WasteLinkedList();

    HashMap<Integer, Customer> customers = new HashMap<>();
    HashMap<Integer, WasteLinkedList> customerWastes = new HashMap<>();

    public RecyclingSystem() {
        vehicleQueue.enqueue(new Vehicle());
    }

    // ================= MUSTERI EKLE =================
    public boolean addCustomer(int id, String name, String phone) {
        if (customers.containsKey(id))
            return false;

        customers.put(id, new Customer(id, name, phone));
        customerWastes.put(id, new WasteLinkedList());
        return true;
    }

    public boolean hasCustomer(int id) {
        return customers.containsKey(id);
    }

    // ================= ATIK EKLE =================
    public boolean addWaste(int customerId, String type, double amount) {

        if (!customers.containsKey(customerId) || amount <= 0)
            return false;

        double remaining = amount;
        Vehicle currentVehicle = vehicleQueue.peek();

        while (remaining > 0) {

            if (currentVehicle.canLoad(remaining)) {
                currentVehicle.load(remaining);
                remaining = 0;
            } else {
                double space = currentVehicle.remainingCapacity();
                currentVehicle.load(space);
                remaining -= space;

                Vehicle newVehicle = new Vehicle();
                vehicleQueue.enqueue(newVehicle);
                currentVehicle = newVehicle;
            }
        }

        WasteRecord record = new WasteRecord(customerId, type, amount);
        wasteList.add(record);
        customerWastes.get(customerId).add(record);

        return true;
    }

    // ================= TEK MUSTERI DETAY =================
    public String getCustomerDetailById(int id) {

        if (!customers.containsKey(id))
            return "Bu ID ye ait musteri yok.";

        StringBuilder sb = new StringBuilder();
        sb.append(customers.get(id)).append("\n");
        sb.append(customerWastes.get(id).toString());

        return sb.toString();
    }

    // ================= TUM ATIKLAR =================
    public String getAllWastesText() {
        return wasteList.toString();
    }

    // ================= ARACLAR =================
    public String getVehiclesText() {

        StringBuilder sb = new StringBuilder();

        for (Vehicle v : vehicleQueue.queue) {
            sb.append("Arac ")
              .append(v.id)
              .append(" -> ")
              .append(v.currentLoad)
              .append(" / ")
              .append(v.capacity)
              .append(" kg\n");
        }

        return sb.toString();
    }
}