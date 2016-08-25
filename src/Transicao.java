public class Transicao {
	
	private int eOrigem; // estado origem
	private int eDestino; // estado destino
	private char Elemento;

	public Transicao(int eOrigem, char elemento,int eDestino) {
		super();
		this.eOrigem = eOrigem;
		this.eDestino = eDestino;
		Elemento = elemento;
	}
	
	
	public int geteOrigem() {
		return eOrigem;
	}
	public void seteOrigem(int eOrigem) {
		this.eOrigem = eOrigem;
	}
	public int geteDestino() {
		return eDestino;
	}
	public void seteDestino(int eDestino) {
		this.eDestino = eDestino;
	}
	public char getElemento() {
		return Elemento;
	}
	public void setElemento(char elemento) {
		Elemento = elemento;
	}
	
}
