
public class Rivista extends ElementoBiblioteca {

	Periodicita periodicita;
	
	public Rivista(long codiceIsbn, String titolo, int annoPubblicazione, int nPagine, Periodicita periodicita) {
		super(codiceIsbn, titolo, annoPubblicazione, nPagine);
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {
		return"RIVISTA -" + " CODICE ISBN: " + codiceIsbn + " TITOLO: " + titolo + " ANNO: " + annoPubblicazione + " PAGINE: " + nPagine
				+ " PERIODICITA': " + periodicita;
	}

	
}
