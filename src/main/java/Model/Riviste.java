package Model;

public class Riviste extends Consultabile{
    public enum Periodicita {SETTIMANLE,MENSILE,SEMESTRALE}

    private Periodicita periodicita;

    public Riviste(String isbn, String titolo, int anno, int pagine, Periodicita periodicita) {
        super(isbn, titolo, anno, pagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String getTipo() {
        return "Rivista";
    }

    @Override
    public String toString() {
        return super.toString() + ", Periodicità: " + periodicita;
    }
}
