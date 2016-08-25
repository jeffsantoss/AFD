import java.util.ArrayList;
import java.util.ListResourceBundle;

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
					throw new AutomatoException(this,"Estado inicial inváido.");
			}
	}
	
	public void validarTransição(Transicao trans) throws AutomatoException{
		for(int i=0;i<Alfabeto.length();i++){
			if(trans.getElemento() != Alfabeto.charAt(i)){
				throw new AutomatoException(this,"Elemento inexistente no seu alfabeto");
			}	
		}
		
		
	}
	
	public void validarAutomato(String Input) throws AutomatoException {
		
			for(int i=0;i<Input.length();i++){
				if(Input.charAt(i) != Alfabeto.charAt(i)) {
					this.valido = false;
					throw new AutomatoException(this,"Inserido elemento inexistente no alfabeto");
				}	
			}	
			// 1° Verifico se meu primeiro caractere do input vem de um EstadoInicial
			
			if (Input.charAt(0) == listTransicao.get(0).getElemento()
				&& listTransicao.get(0).geteOrigem() != EstadoInicial[EstadoInicial.length])
			{
				throw new AutomatoException(this,"O Automâto deve sempre começar pelo Estado Inicial");
			}
			
			for(int i = 0;i<Input.length();i++){
				estadosOrigens(Input.charAt(i));
					estadosDestinos(Input.charAt(i));
			}
			
	}
	
	public void estadosDestinos(char Elemento){
		for(int i = 0 ;i<listTransicao.size();i++){
			listTransicao.get(i).geteDestino();
		}
	}
	
	public void estadosOrigens(char Elemento[]){
		for(int i = 0 ;i<listTransicao.size();i++){
			Elemento = listTransicao.get(i).getElemento();
			listTransicao.get(i).geteOrigem();
		
		

	}
	
	


	
	
	
}
