import java.io.*;
import java.util.*;

class Patient implements Serializable {
    private String name;
    private int age;
    private String contact;
    private List<DiagnosisReport> reports;

    public Patient(String name, int age, String contact) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.reports = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<DiagnosisReport> getReports() {
        return reports;
    }

    public void addReport(DiagnosisReport report) {
        reports.add(report);
    }
}

class DiagnosisReport implements Serializable {
    private String date;
    private String description;
    private String doctorComments;

    public DiagnosisReport(String date, String description, String doctorComments) {
        this.date = date;
        this.description = description;
        this.doctorComments = doctorComments;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Description: " + description + ", Doctor Comments: " + doctorComments;
    }
}

public class HealthMonitoringSystem {
    private static final String DATA_DIR = "patients_data/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new File(DATA_DIR).mkdir(); // Create directory for storing patient data

        while (true) {
            System.out.println("1. Add Patient");
            System.out.println("2. Add Diagnosis");
            System.out.println("3. View Reports");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    addDiagnosis(scanner);
                    break;
                case 3:
                    viewReports(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addPatient(Scanner scanner) {
        System.out.print("Enter patient's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient's age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter patient's contact information: ");
        String contact = scanner.nextLine();

        Patient patient = new Patient(name, age, contact);
        savePatient(patient);
        System.out.println("Patient " + name + " added successfully.");
    }

    private static void addDiagnosis(Scanner scanner) {
        System.out.print("Enter patient's name: ");
        String name = scanner.nextLine();
        Patient patient = loadPatient(name);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter diagnosis date: ");
        String date = scanner.nextLine();
        System.out.print("Enter diagnosis description: ");
        String description = scanner.nextLine();
        System.out.print("Enter doctor's comments: ");
        String doctorComments = scanner.nextLine();

        DiagnosisReport report = new DiagnosisReport(date, description, doctorComments);
        patient.addReport(report);
        savePatient(patient);
        System.out.println("Diagnosis added for " + name + ".");
    }

    private static void viewReports(Scanner scanner) {
        System.out.print("Enter patient's name: ");
        String name = scanner.nextLine();
        Patient patient = loadPatient(name);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Reports for " + name + ":");
        for (DiagnosisReport report : patient.getReports()) {
            System.out.println(report);
        }
    }

    private static void savePatient(Patient patient) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIR + patient.getName() + ".dat"))) {
            oos.writeObject(patient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Patient loadPatient(String name) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_DIR + name + ".dat"))) {
            return (Patient) ois.readObject();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
