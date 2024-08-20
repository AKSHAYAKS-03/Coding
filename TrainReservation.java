import java.util.ArrayList;
import java.util.Scanner;

class Passenger {
    String name;
    int age;
    String status;
    int seatNo;
    String berth;

    Passenger(String name, int age, String status, int seatNo, String berth) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.seatNo = seatNo;
        this.berth = berth;
    }
}

class ReservationSystem {
    private ArrayList<Passenger> confirmedList = new ArrayList<>();
    private ArrayList<Passenger> racList = new ArrayList<>();
    private ArrayList<Passenger> waitingList = new ArrayList<>();

    private final int maxConfirmed = 2;
    private final int maxRac = 2;
    private final int maxWaiting = 2;

    private int seatCounter = 1;
    private int[] berthCount = {1, 1, 1}; // Lower, Middle, Upper berth limits

    public void bookTicket(String name, int age, String preferredBerth) {
        int berthIndex = getBerthIndex(preferredBerth);
        if (berthIndex == -1 || berthCount[berthIndex] <= 0) {
            System.out.println("Invalid or unavailable berth preference. Please choose another.");
            return;
        }

        if (confirmedList.size() < maxConfirmed) {
            confirmedList.add(new Passenger(name, age, "Confirmed", seatCounter++, preferredBerth));
            berthCount[berthIndex]--; // Decrease the count for the chosen berth
            System.out.println("Ticket confirmed for " + name + " with " + preferredBerth + " berth.");
        } else if (racList.size() < maxRac) {
            racList.add(new Passenger(name, age, "RAC", seatCounter++, preferredBerth));
            System.out.println("Added to RAC list: " + name + " with " + preferredBerth + " berth.");
        } else if (waitingList.size() < maxWaiting) {
            waitingList.add(new Passenger(name, age, "Waiting", seatCounter++, preferredBerth));
            System.out.println("Added to Waiting list: " + name + " with " + preferredBerth + " berth.");
        } else {
            System.out.println("No tickets available. Try later.");
        }
    }

    public void cancelTicket(String name) {
        boolean found = false;
        for (Passenger p : confirmedList) {
            if (p.name.equalsIgnoreCase(name)) {
                confirmedList.remove(p);
                berthCount[getBerthIndex(p.berth)]++; // Replenish the berth count
                System.out.println("Ticket canceled for " + name);
                adjustListsAfterCancellation(p.berth);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Passenger not found in the confirmed list.");
        }
    }

    public void displayConfirmedList() {
        System.out.println("Confirmed List:");
        for (Passenger p : confirmedList) {
            System.out.println("Seat No: " + p.seatNo + ", Name: " + p.name + ", Age: " + p.age + ", Berth: " + p.berth + ", Status: " + p.status);
        }
    }

    public void displayRacList() {
        System.out.println("RAC List:");
        for (Passenger p : racList) {
            System.out.println("Seat No: " + p.seatNo + ", Name: " + p.name + ", Age: " + p.age + ", Berth: " + p.berth + ", Status: " + p.status);
        }
    }

    public void displayWaitingList() {
        System.out.println("Waiting List:");
        for (Passenger p : waitingList) {
            System.out.println("Seat No: " + p.seatNo + ", Name: " + p.name + ", Age: " + p.age + ", Berth: " + p.berth + ", Status: " + p.status);
        }
    }

    private void adjustListsAfterCancellation(String canceledBerth) {
        if (!racList.isEmpty()) {
            Passenger racPassenger = racList.remove(0);
            racPassenger.status = "Confirmed";
            racPassenger.berth = canceledBerth;  // Assign the canceled berth to the RAC passenger
            confirmedList.add(racPassenger);
            System.out.println(racPassenger.name + " from RAC moved to Confirmed list with " + canceledBerth + " berth.");

            if (!waitingList.isEmpty()) {
                Passenger waitingPassenger = waitingList.remove(0);
                waitingPassenger.status = "RAC";
                waitingPassenger.berth = racPassenger.berth;  // Assign the RAC passenger's old berth to the Waiting passenger
                racList.add(waitingPassenger);
                System.out.println(waitingPassenger.name + " from Waiting list moved to RAC list with " + waitingPassenger.berth + " berth.");
            }
        }
    }

    private int getBerthIndex(String berth) {
        switch (berth.toUpperCase()) {
            case "L":
                return 0;
            case "M":
                return 1;
            case "U":
                return 2;
            default:
                return -1;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nChoose any one \n 1.Book ticket \n 2.Cancel ticket"
                    + " \n 3.Display Confirmed list\n 4.Display RAC list"
                    + "\n 5.Display Waiting list\n 6.Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter passenger name: ");
                    String name = scanner.next();
                    System.out.print("Enter passenger age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter berth preference (L for Lower, M for Middle, U for Upper): ");
                    String preferredBerth = scanner.next();
                    reservationSystem.bookTicket(name, age, preferredBerth);
                    break;
                case 2:
                    System.out.print("Enter passenger name to cancel: ");
                    String cancelName = scanner.next();
                    reservationSystem.cancelTicket(cancelName);
                    break;
                case 3:
                    reservationSystem.displayConfirmedList();
                    break;
                case 4:
                    reservationSystem.displayRacList();
                    break;
                case 5:
                    reservationSystem.displayWaitingList();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
