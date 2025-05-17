package Model;

import Exceptions.ElementoEsistenteException;
import Exceptions.ElementoNonTrovatoException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Archivio {
    private List<Consultabile> elementi;

    public Archivio() {
        this.elementi = new ArrayList<>();
    }

    public List<Consultabile> getElementi() {
        return elementi;
    }

    public void setElementi(List<Consultabile> elementi) {
        this.elementi = elementi;
    }

    public void aggiungiElemento(Consultabile elemento ) throws ElementoEsistenteException{
        boolean esiste = elementi.contains(elemento);
        if (esiste) {
            throw new ElementoEsistenteException("Elemento con ISBN " + elemento.getIsbn() + " già presente.");
        }
        elementi.add(elemento);
    }
    public Consultabile cercaElemento(String isbn) throws ElementoNonTrovatoException {
        return elementi.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ElementoNonTrovatoException("Elemento con ISBN " + isbn + " non trovato."));
    }

    public boolean rimuoviElemento(String isbn) {
        return elementi.removeIf(elementi -> elementi.getIsbn().equals(isbn));
    }

    public List<Consultabile> ricercaPerAnno (int anno){
        return elementi.stream().filter(e->e.getAnno()==anno).toList();
    }

    public List<Libri> ricercaPerAutore (String autore) {
        return elementi.stream().filter(e->e instanceof Libri)
                .map(e->(Libri) e)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore)).toList();


    }

    public void aggiornaElemento(Consultabile input) throws ElementoNonTrovatoException {
        // Trovo l'elemento tramite ISBN
        Consultabile consultabile = elementi.stream()
                .filter(e -> e.getIsbn().equals(input.getIsbn()))
                .findFirst()
                .orElseThrow(() -> new ElementoNonTrovatoException("Elemento con ISBN " + input.getIsbn() + " non trovato."));

        // Aggiorno l'elemento con i valori di input
        consultabile.setAnno(input.getAnno());
        consultabile.setIsbn(input.getIsbn());
        consultabile.setPagine(input.getPagine());
        consultabile.setTitolo(input.getTitolo());
    }

    public void statistiche (){
        long numeroLibri= elementi.stream().filter(e->e instanceof Libri).count();
        long numeroRiviste= elementi.stream().filter(e->e instanceof Riviste).count();

        Consultabile maxPagine = elementi.stream()
                .max((e1, e2) -> Integer.compare(e1.getPagine(), e2.getPagine()))
                .orElse(null);

        double mediaPagine = elementi.stream()
                .mapToInt(Consultabile::getPagine)
                .average()
                .orElse(0.0);

        System.out.println("Numero totale di libri: " + numeroLibri);
        System.out.println("Numero totale di riviste: " + numeroRiviste);

        if (maxPagine != null) {
            System.out.println("Elemento con più pagine: " + maxPagine.getTitolo() + " (" + maxPagine.getPagine() + " pagine)");
        } else {
            System.out.println("Nessun elemento trovato.");
        }

        System.out.println("Media pagine: " + mediaPagine);

    }


}

