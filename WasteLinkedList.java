package project;

/**
 * Atık kayıtlarını tutan tek yönlü bağlı liste (Linked List)
 */
public class WasteLinkedList {

    /**
     * Linked List düğümü
     */
    private static class Node {
        WasteRecord record;
        Node next;

        Node(WasteRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    private Node head;

    /**
     * Listeye yeni atık ekler
     */
    public void add(WasteRecord record) {
        Node newNode = new Node(record);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    /**
     * Listeyi ekrana yazdırır (console)
     */
    public void display() {
        if (head == null) {
            System.out.println("📭 Kayıt yok.");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.println(current.record);
            current = current.next;
        }
    }

    /**
     * Liste boş mu?
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Listeyi String olarak döndürür (GUI için)
     */
    @Override
    public String toString() {
        if (head == null) {
            return "📭 Kayıt yok.";
        }

        StringBuilder sb = new StringBuilder();
        Node current = head;

        while (current != null) {
            sb.append(current.record).append("\n");
            current = current.next;
        }

        return sb.toString();
    }
}