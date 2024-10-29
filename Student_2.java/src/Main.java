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
    private final String brojIndeksa;
    private final String datumRodjenja;
    private final List<Integer> ocjene;

    public Student(String ime, String prezime, String brojIndeksa, String datumRodjenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojIndeksa = brojIndeksa;
        this.datumRodjenja = datumRodjenja;
        this.ocjene = new ArrayList<>();
    }

    public void setOcjene(List<Integer> ocjene) {
        this.ocjene.clear();
        this.ocjene.addAll(ocjene);
    }

    public double prosjek() throws DijeljenjeSNulomException {
        if (ocjene.isEmpty()) {
            throw new DijeljenjeSNulomException("Student nema nijednu unesenu ocjenu.");
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
            return String.format("Unos studenta uspješan! Student: %s %s, broj indeksa: %s, prosjek: %.6f", ime, prezime, brojIndeksa, prosjek());
        } catch (DijeljenjeSNulomException e) {
            System.out.println(e.getMessage());
            return "";
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

        System.out.print("Unesite broj indeksa: ");
        String brojIndeksa = scanner.nextLine();

        Student student = new Student(ime, prezime, brojIndeksa, datumRodjenja);

        System.out.print("Unesite ocjene studenta: (x,y,...): ");
        String ocjeneString = scanner.nextLine();
        String[] ocjeneNiz = ocjeneString.split(",");
        List<Integer> ocjeneList = new ArrayList<>();

        for (String ocjenaStr : ocjeneNiz) {
            try {
                int ocjena = Integer.parseInt(ocjenaStr.trim());
                ocjeneList.add(ocjena);
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos ocjene: " + ocjenaStr);
            }
        }

        student.setOcjene(ocjeneList);

        System.out.println(student.toString());

        scanner.close();
    }
}
