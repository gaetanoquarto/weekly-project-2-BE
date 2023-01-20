import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class Archivio {
	
	static Scanner in = new Scanner(System.in);
	static String fileName = "archivio.txt";
    static File fileInfo = new File(fileName);
    private static final String ENCODING = "utf-8";
    
	
	public static void main(String[] args) throws IOException {
		
		 ArrayList<String> archivio = new ArrayList<>(FileUtils.readLines(fileInfo, ENCODING));
		
		
		boolean continuaProgramma = true;

		do {
		System.out.println("BENVENUTI NELL'ARCHIVIO");
		System.out.println("1 - AGGIUNGI ELEMENTO");
		System.out.println("2 - RIMUOVI ELEMENTO TRAMITE ISBN");
		System.out.println("3 - RICERCA ELEMENTO TRAMITE ISBN");
		System.out.println("4 - RICERCA ELEMENTO PER ANNO");
		System.out.println("5 - RICERCA ELEMENTO PER AUTORE");
		System.out.println("6 - MOSTRA ARCHIVIO");
		System.out.println("0 - ABBANDONA ARCHIVIO");
		System.out.println("---------------------------------------------");
		System.out.println("Seleziona un numero: ");
		try {
			 int selezione = Integer.parseInt(in.nextLine());
			 switch(selezione) {
				case(1):
					System.out.println("1 - Libri");
					System.out.println("2 - Riviste");
					System.out.println("inserisci tipo: ");
					int selezioneTipo = Integer.parseInt(in.nextLine());
					if(selezioneTipo == 1) {
						aggiungiLibro();
					} else {
						aggiungiRivista();
					}
					break;
				case(2):
				System.out.println("Inserisci il codice ISBN da rimuovere: ");
				long isbnRimuovi = Long.parseLong(in.nextLine());
				 archivio.removeIf(line -> line.contains(Long.toString(isbnRimuovi)));
			     FileUtils.writeLines(fileInfo, archivio);
			
				 System.out.println("Elemento rimosso con successo!");
				 
				break;
				case(3):
					System.out.println("Inserisci il codice ISBN dell'elemento da trovare: ");
					long isbnRicerca = Long.parseLong(in.nextLine());
					archivio.stream().filter((e) -> e.contains(Long.toString(isbnRicerca))).forEach((e) -> System.out.println(e));
					break;
				case(4):
					System.out.println("Inserisci l'anno dell'elemento da trovare: ");
				int annoRicerca = Integer.parseInt(in.nextLine());
				archivio.stream().filter((e) -> e.contains(Integer.toString(annoRicerca))).forEach((e) -> System.out.println(e));
				break;
				case(5):
					System.out.println("Inserisci l'autore dell'elemento da trovare: ");
				String autoreRicerca = in.nextLine();
				archivio.stream().filter((e) -> e.contains(autoreRicerca)).forEach((e) -> System.out.println(e));
				break;
				case(6):
					System.out.println("Carico il nostro archivio...");
		  		System.out.println(FileUtils.readFileToString(fileInfo, ENCODING));
		  		break;
				case(0):
					System.out.println("Grazie per aver visitato il nostro archivio!");
					System.exit(0);
				break;
				default:
					System.out.println("Valore errato o non esistente!");
					break;
				}
		} catch(NumberFormatException e) {
			System.out.println("ERRORE! hai inserito un valore diverso da un numero!");
		}
		
		
			
		
		System.out.println("Vuoi continuare? (S/N)");
	    String input = in.nextLine();
	    continuaProgramma = input.equalsIgnoreCase("S");
	} while(continuaProgramma);
}

	
	
	
	public static void aggiungiLibro() {
		System.out.println("inserisci titolo: ");
		String titolo = in.nextLine();
		System.out.println("inserisci anno di pubblicazione: ");
		int anno = Integer.parseInt(in.nextLine());
		System.out.println("inserisci numero pagine: ");
		int nPagine = Integer.parseInt(in.nextLine());
		System.out.println("inserisci autore: ");
		String autore = in.nextLine();
		System.out.println("inserisci genere: ");
		String genere = in.nextLine();
		
		
		
		Libro libro = new Libro(generatoreISBN(), titolo, anno, nPagine, autore, genere );
		
		String aggiungiLibro = libro.toString();
		try {
			writeOnFile(fileInfo, aggiungiLibro + System.lineSeparator(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("LIBRO AGGIUNTO CON SUCCESSO!");
		
	}
	
	
	public static void aggiungiRivista() {
		System.out.println("inserisci titolo: ");
		String titolo = in.nextLine();
		System.out.println("inserisci anno di pubblicazione: ");
		int anno = Integer.parseInt(in.nextLine());
		System.out.println("inserisci numero pagine: ");
		int nPagine = Integer.parseInt(in.nextLine());
		System.out.println("1 - settimanale");
		System.out.println("2 - mensile");
		System.out.println("3 - semestrale");
		System.out.println("Inserisci periodicita: ");
		int selezione = Integer.parseInt(in.nextLine());
		Periodicita periodicita = null;
		switch(selezione) {
		case(1):
			periodicita = Periodicita.SETTIMANALE;
			break;
		case(2):
			periodicita = Periodicita.MENSILE;
		break;
		case(3):
			periodicita = Periodicita.SEMESTRALE;
		break;
		default:
			System.out.println("Valore errato!");
		break;
		}
		
		Rivista rivista = new Rivista(generatoreISBN(), titolo, anno, nPagine, periodicita);
		
		String aggiungiRivista = rivista.toString();		
		try {
			writeOnFile(fileInfo, aggiungiRivista + System.lineSeparator(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("RIVISTA AGGIUNTA CON SUCCESSO!");
				
		}
	
	public static long generatoreISBN() {
		HashSet<Long> setISBN = new HashSet<>();
		Random random = new Random();
		long inizio = 9000000000000L;
		long fine = 9999999999999L;
		
        long nuovoISBN = 0;
        
        do {
            nuovoISBN = inizio + (long)(random.nextDouble()*(fine - inizio));
        } while (setISBN.contains(nuovoISBN));
        
        setISBN.add(nuovoISBN);
        return nuovoISBN;
	}
	
	public static void writeOnFile(File f, String s, boolean append) throws IOException {
        FileUtils.writeStringToFile(f, s, ENCODING, append);
    }
	
	

	}


