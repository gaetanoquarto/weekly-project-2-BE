
public class Libro extends ElementoBiblioteca{
	
	String autore;
	String genere;
	

	public Libro(long codiceIsbn, String titolo, int annoPubblicazione, int nPagine, String autore, String genere) {
		super(codiceIsbn, titolo, annoPubblicazione, nPagine);
		
		this.autore = autore;
		this.genere = genere;
	}

	@Override
	public String toString() {
		return"**LIBRO** -" + " CODICE ISBN: " + codiceIsbn + "-" + " TITOLO: " + titolo + "-" + " ANNO: " + annoPubblicazione + "-" + " PAGINE: " + nPagine
				+ "-" + " AUTORE: " + autore + "-" + " GENERE: " + genere;
	}
	
}
