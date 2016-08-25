import java.util.ArrayList;

import javax.swing.JOptionPane;


public class AFD {
	
	private ArrayList<Transicao> listTransicao = new ArrayList<Transicao>();
	private String Alfabeto;
	private int Estados[];
	private int EstadosFinal[];
	private int EstadoInicial[];
	private boolean valido;
	
	public AFD(String Estados)
	{		
	}
	
	public void setarEstados(String Estados){
		for(int i = 0,j = 0;i<Estados.length();i++,j++) {
			if(Estados.charAt(i) != ',')
				continue;
			else
				this.Estados[j++] = Integer.parseInt(Estados, i);
		}
	}
	
	public void AdicionarTransição(int eOrigem, char Elemento,int eDestino){
		try {
			listTransicao.add(new Transicao(eOrigem,Elemento,eDestino));
			validarTransição(listTransicao.get(listTransicao.size())); // pega sempre o último e valida.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void definirEstadosFinais(String estadosFinais) {
			for(int i=0;i<Estados.length;i++){
				if(Estados[i] == Integer.parseInt(estadosFinais))
					EstadosFinal[i] = Integer.parseInt(estadosFinais);
			}
	}
	
	public void definirEstadoInicial(String estadoInicial) throws AutomatoException {
		
		for(int i=0;i<Estados.length;i++) {
			if(Estados[i] == Integer.parseInt(estadoInicial))
				EstadoInicial[i] = Integer.parseInt(estadoInicial);
				if(estadoInicial.length() > 1)
					break;
		}
			if(estadoInicial.length() > 1) {
					throw new AutomatoException(this);
			}
	}
	
	public void validarTransição(Transicao trans) throws AutomatoException{
		for(int i=0;i<Alfabeto.length();i++){
			if(trans.getElemento() != Alfabeto.charAt(i)){
				throw new AutomatoException(this);
			}	
		}
		
		
	}
	
	public void validarAutomato(String Input) throws AutomatoException {
	
			for(int i =0;i<Input.length();i++){
				if(Input.charAt(i) != Alfabeto.charAt(i)) {
					this.valido = false;
					throw new AutomatoException(this);
				}		
			}
			
			for(int i=0;i<Input.length();i++) {
				listTransicao.get(i).geteOrigem()	
				listTransicao.get(i).geteDestino()
			}
				
			
	}
	
	


	
	
	
}
