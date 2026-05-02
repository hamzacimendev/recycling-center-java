package project;

public class WasteRecord {

    int customerId;
    String wasteType;
    double amount;

    public WasteRecord(int customerId, String wasteType, double amount) {
        this.customerId = customerId;
        this.wasteType = wasteType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Müşteri ID: " + customerId +
               " | Tür: " + wasteType +
               " | Miktar: " + amount + " kg";
    }
}