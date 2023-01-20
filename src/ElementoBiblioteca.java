
public abstract class ElementoBiblioteca {
	
	long codiceIsbn;
	String titolo;
	int annoPubblicazione;
	int nPagine;
	
	public ElementoBiblioteca(long codiceIsbn, String titolo, int annoPubblicazione, int nPagine) {
		
		this.codiceIsbn = codiceIsbn;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.nPagine = nPagine;
		
	}
	

	public long getCodiceIsbn() {
		return this.codiceIsbn;
	}
}
