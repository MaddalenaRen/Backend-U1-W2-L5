package Model;

import Exceptions.ElementoEsistenteException;
import Exceptions.ElementoNonTrovatoException
import java.util.ArrayList;
import java.util.List;

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

    public List<Consultabile> ricercaPerAutore (String autore) {
        return elementi.stream().filter(e->e.)
    }

}

