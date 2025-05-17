package Model;

import Exceptions.ElementoEsistenteException;
import Exceptions.ElementoNonTrovatoException;
import Model.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);

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
                case "2" -> archivio.cercaElemento(scanner.nextLine());
                case "3" -> archivio.rimuoviElemento(scanner.nextLine());
                case "4" -> archivio.ricercaPerAnno(Integer.parseInt(scanner.nextLine()));
                case "5" -> archivio.ricercaPerAutore(scanner.nextLine());
                case "6" -> archivio.aggiornaElemento(scanner.nextLine(Consultabile));
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
            int anno = Integer.parseInt(scanner.nextLine());
            System.out.print("Numero pagine: ");
            int pagine = Integer.parseInt(scanner.nextLine());

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
}