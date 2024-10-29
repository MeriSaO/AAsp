import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {

    public static class DijeljenjeSNulomException extends Exception {
        public DijeljenjeSNulomException(String message) {
            super(message);
        }
    }

    private final String ime;
    private final String prezime;
    private final String datumRodjenja;
    private final List<Integer> ocjene;

    public Student(String ime, String prezime, String datumRodjenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.ocjene = new ArrayList<>();
    }

    public void dodajOcjenu(int ocjena) {
        ocjene.add(ocjena);
    }

    public double prosjek() throws DijeljenjeSNulomException {
        if (ocjene.isEmpty()) {
            throw new DijeljenjeSNulomException("Student nema nijednu unesenu ocjenu! Nije moguće ispisati podatke.");
        }
        double suma = 0;
        for (int ocjena : ocjene) {
            suma += ocjena;
        }
        return suma / ocjene.size();
    }

    @Override
    public String toString() {
        try {
            return String.format("Unos studenta uspješan! Student: %s %s, prosjek: %.2f", ime, prezime, prosjek());
        } catch (DijeljenjeSNulomException e) {
            System.out.println(e.getMessage());
            return "";  // Vraća prazan string ako student nema ocjena
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Datum rođenja (dd/mm/yyyy): ");
        String datumRodjenja = scanner.nextLine();

        System.out.print("Unesite ime studenta: ");
        String ime = scanner.nextLine();

        System.out.print("Unesite prezime studenta: ");
        String prezime = scanner.nextLine();

        Student student = new Student(ime, prezime, datumRodjenja);

        System.out.print("Unesite ocjene studenta (x,y,...): ");
        String ocjeneString = scanner.nextLine();
        String[] ocjeneNiz = ocjeneString.split(",");

        for (String ocjenaStr : ocjeneNiz) {
            try {
                int ocjena = Integer.parseInt(ocjenaStr.trim());
                student.dodajOcjenu(ocjena);
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos ocjene: " + ocjenaStr);
            }
        }

        System.out.println(student.toString());

        scanner.close();
    }
}
