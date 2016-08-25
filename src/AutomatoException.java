public class AutomatoException extends Exception {
	
	private AFD automato;
	
	public AutomatoException(AFD automato) {
		this.automato = automato;
	}
	
}
