package Model;

import Exceptions.ElementoEsistenteException;
import Exceptions.ElementoNonTrovatoException;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);

        Consultabile aggiornamento = new Libri("1", "Il mondo perduto", 2025, 160, "Sconosciuto", "Fantasy");

        boolean menu = true;

        while (menu) {
            System.out.println("Menu");
            System.out.println("1. Aggiungi elemento");
            System.out.println("2. Cerca elemento per ISBN");
            System.out.println("3. Rimuovi elemento per ISBN");
            System.out.println("4. Ricerca per anno di pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Aggiorna elemento");
            System.out.println("7. Statistiche");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1" -> aggiungiElemento(archivio, scanner);
                case "2" -> cercaElemento(archivio, scanner);
                case "3" -> archivio.rimuoviElemento(scanner.nextLine());
                case "4" -> archivio.ricercaPerAnno(parseInt(scanner.nextLine()));
                case "5" -> archivio.ricercaPerAutore(scanner.nextLine());
                case "6" -> archivio.aggiornaElemento(aggiornamento);
                case "7" -> archivio.statistiche();
                case "0" -> {
                    System.out.println("Uscita dal programma.");
                    menu = false;
                }
                default -> System.out.println("Scelta non valida.");
            }
        }

        scanner.close();
    }

    public static void aggiungiElemento(Archivio archivio, Scanner scanner) {
        System.out.println("Vuoi Aggiungere un Libro(1) o una Rivista(2)?");
        String tipo = scanner.nextLine();

        try {
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Titolo: ");
            String titolo = scanner.nextLine();
            System.out.print("Anno pubblicazione: ");
            int anno = parseInt(scanner.nextLine());
            System.out.print("Numero pagine: ");
            int pagine = parseInt(scanner.nextLine());

            if (tipo.equals("1")) {
                System.out.print("Autore: ");
                String autore = scanner.nextLine();
                System.out.print("Genere: ");
                String genere = scanner.nextLine();
                Libri libro = new Libri(isbn, titolo, anno, pagine, autore, genere);
                archivio.aggiungiElemento(libro);
            } else if (tipo.equals("2")) {
                System.out.print("Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                Riviste.Periodicita periodicita = Riviste.Periodicita.valueOf(scanner.nextLine().toUpperCase());
                Riviste rivista = new Riviste(isbn, titolo, anno, pagine, periodicita);
                archivio.aggiungiElemento(rivista);
            } else {
                System.out.println("Tipo non valido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Errore nel formato numerico.");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore di parametro: " + e.getMessage());
        } catch (ElementoEsistenteException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static void cercaElemento(Archivio archivio, Scanner scanner) {
        System.out.println("Inserisci codice ISBN da cercare:");
        String isbn = scanner.nextLine();

        try {
            Consultabile trovato = archivio.cercaElemento(isbn);
            System.out.println("Elemento Trovato: " + trovato.getTitolo());
        } catch (ElementoNonTrovatoException e) {
            System.out.println("Elemento non trovato");
        }
    }

    public static void rimuoviElemento(Archivio archivio, Scanner scenner) {
        System.out.println("Inserisci ISBN da rimuovere: ");
        String isbn = scenner.nextLine();

        try {
            boolean rimosso = archivio.rimuoviElemento(isbn);
            if (rimosso) {
                System.out.println("Elemento rimosso");
            } else {
                System.out.println("elemento non trovato");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void ricercaperAnno(Archivio archivio, Scanner scanner) {

        try {
            System.out.println("Inserisci anno da cercare: ");
            Integer anno = parseInt(scanner.nextLine());
            List<Consultabile> risultati = archivio.ricercaPerAnno(anno);
            if (risultati.isEmpty()) {
                System.out.println("Nessun elemento trovato per quell'anno");
            } else {
                risultati.forEach(e -> System.out.println(e.getTitolo() + " (" + e.getAnno() + ")"));
            }
        } catch (NumberFormatException e) {
            System.out.println("Anno non valido.");
        }
    }

    public static void ricercaPerAutore(Archivio archivio, Scanner scanner) {

        System.out.println("Inserisci Autore da cercare: ");
        String autore = scanner.nextLine();
        List<Libri> risultati = archivio.ricercaPerAutore(autore);
        if (risultati.isEmpty()) {
            System.out.println("Nessun Libro Trovato di questo Autore"
            );
        } else {
            risultati.forEach(l -> System.out.println(l.getTitolo() + " di " + l.getAutore()));
        }

    }

    private static void aggiornaElemento(Archivio archivio, Scanner scanner) {
        System.out.print("Inserisci ISBN dell’elemento da aggiornare: ");
        String isbn = scanner.nextLine();
        try {
            Consultabile esistente = archivio.cercaElemento(isbn);
            System.out.print("Nuovo titolo: ");
            String titolo = scanner.nextLine();
            System.out.print("Nuovo anno pubblicazione: ");
            Integer anno = parseInt(scanner.nextLine());
            System.out.print("Nuovo numero pagine: ");
            int pagine = Integer.parseInt(scanner.nextLine());

            if (esistente instanceof Libri) {
                System.out.print("Nuovo autore: ");
                String autore = scanner.nextLine();
                System.out.print("Nuovo genere: ");
                String genere = scanner.nextLine();
                Libri aggiornato = new Libri(isbn, titolo, anno, pagine, autore, genere);
                archivio.aggiornaElemento(aggiornato);
            } else if (esistente instanceof Riviste) {
                System.out.print("Nuova periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                Riviste.Periodicita periodicita = Riviste.Periodicita.valueOf(scanner.nextLine().toUpperCase());
                Riviste aggiornato = new Riviste(isbn, titolo, anno, pagine, periodicita);
                archivio.aggiornaElemento(aggiornato);
            }
            System.out.println("Elemento aggiornato con successo.");
        }
        catch (NumberFormatException e) {
            System.out.println("Errore nel formato numerico.");
        }
        catch (ElementoNonTrovatoException | IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }



}
