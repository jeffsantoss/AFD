import javax.swing.JOptionPane;

public class AutomatoException extends Exception {
	
	private AFD automato;
	private String mensagem;
	
	public AutomatoException(AFD automato, String MensagemErro) {
		this.automato = automato;
		JOptionPane.showMessageDialog(null, MensagemErro);
	}
	
}
