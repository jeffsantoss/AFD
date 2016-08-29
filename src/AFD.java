import java.util.ArrayList;

import javax.swing.JOptionPane;


public class AFD {
	
	private ArrayList<Transicao> listTransicao = new ArrayList<Transicao>();
	private String Alfabeto = "";
	private String Estados = "";
	private String EstadosFinal = "";
	private String EstadoInicial = "";
	private boolean valido = true;
	
	public AFD() {
	}

	public void setarEstados(String Estados){
		for(int i = 0;i < Estados.length();i++) {
			if(Estados.charAt(i) == ',' || Estados.charAt(i) == ' ')
				continue;
			else
				this.Estados = this.Estados + Estados.charAt(i);
		}
	}
	
	public void AdicionarTransição(int eOrigem, char Elemento,int eDestino){		
		int index = listTransicao.size();
		
		try {
			listTransicao.add(new Transicao(eOrigem,Elemento,eDestino));
			validarTransição(listTransicao.get(index)); // pega sempre o último e valida.
		} catch (Exception e) {
			this.valido = false;
		}
		index--;
	}
	
	public void definirEstadosFinais(String estadosFinais) {
		estadosFinais.replaceAll(",", "");
		estadosFinais.replaceAll(" ", "");
		
		if(Estados.indexOf(estadoInicial) == -1)
			throw new AutomatoException(this,"AUTÔMATO INVÁLIDO! O estado final citado q"+Integer.parseInt(estadoInicial)+" não é um estado válido");
		}

	public void definirEstadoInicial(String estadoInicial) throws AutomatoException {
		
		estadoInicial.replaceAll(",", "");
		estadoInicial.replaceAll(" ", "");
		
		if(Estados.indexOf(estadoInicial) == -1)
			throw new AutomatoException(this,"AUTÔMATO INVÁLIDO! O estado inicial q"+Integer.parseInt(estadoInicial)+" não pertence aos estados");
			if(estadoInicial.length() > 1) {
				this.valido = false;
					throw new AutomatoException(this,"AUTÔMATO INVÁLIDO! Só pode haver apenas 1 estado Inicial");
			}
	}
		
	public void validarTransição(Transicao trans) throws AutomatoException{
		
		if (Alfabeto.indexOf(trans.getElemento()) == -1 )	// isso mostra se existe ou não
			throw new AutomatoException(this, "AUTÔMATO INVÁLIDO! Verifique se o elemento " +trans.getElemento()+ " da sua transição pertence ao seu alfabeto");
		if (Estados.indexOf(Integer.toString(trans.geteOrigem())) == -1 || Estados.indexOf(Integer.toString(trans.geteDestino())) == -1) 
			throw new AutomatoException(this, "AUTÔMATO INVÁLIDO! Verifique se seu estados de transição");
	}
	
	public void validarEntrada(String Input) throws AutomatoException {
		for(int i = 0;i<Input.length();i++) 
		{
			if (Alfabeto.indexOf(Input.charAt(i)) == -1 )	{// isso mostra se existe ou não
				this.valido = false;
					throw new AutomatoException(this, "AUTÔMATO INVÁLIDO! Verifique se os elementos da sua entrada pertence ao alfabeto");
			}
		}	
		
		 for(int j = 0;i<listTransicao.size();j++)
		 	if(Input.charAt(0) == listTransicao.get(j).getElemento()) {
				if(listTransicao.get(j).geteOrigem() != this.EstadoInicial) {
					this.valido = false;
						throw ne AutomatoException(this, "AUTÔMATO INVÁLIDO! O elemento" + Input.charAt(0) + "não sai do Estado Inicial");
		}
		// verifico os estados finais.

		for(int i=0;i<listTransicao.size();i++)
			if(Input.charAt(Input.length() - 1 ) == listTransicao.get(i).getElemento()) {
				for(int j=0;j<EstadosFinal.length();j++){
					if(listTransicao.get(i).geteDestino() != Character.getNumericValue(EstadosFinal.charAt(j))){
						System.out.println("Estado destino Transição: " +EstadosFinal.charAt(j));
						System.out.println("Estados finais: " +EstadosFinal.charAt(j));
							this.valido = false;
							throw new AutomatoException(this, "AUTÔMATO INVÁLIDO! o elemento " + Input.charAt(Input.leght() - 1) + " da sua entrada não terminou no estado final");
					}
					else
						break;
				}
		}
		
	}
	
	public void Delta(String delta){			
		delta = delta.replaceAll(",", "");
		delta = delta.replaceAll(" ", "");
	
		for(int i=0;i<delta.length();i+=3){
			this.AdicionarTransição(Character.getNumericValue(delta.charAt(i)), delta.charAt(i+1), Character.getNumericValue(delta.charAt(i+2)));		
		}
	}
	
	
	public ArrayList<Transicao> getListTransicao() {
		return listTransicao;
	}

	public void setListTransicao(ArrayList<Transicao> listTransicao) {
		this.listTransicao = listTransicao;
	}

	public String getAlfabeto() {
		return Alfabeto.replaceAll(",", "");
	}

	public void setAlfabeto(String alfabeto) {
		this.Alfabeto = alfabeto;
	}



	public String getEstados() {
		return Estados.replaceAll(",", "");
	}

	public void setEstados(String estados) {
		Estados = estados;
	}

	public String getEstadosFinal() {
		return EstadosFinal;
	}

	public void setEstadosFinal(String estadosFinal) {
		EstadosFinal = estadosFinal;
	}

	public String getEstadoInicial() {
		return EstadoInicial;
	}

	public void setEstadoInicial(String estadoInicial) {
		EstadoInicial = estadoInicial;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}
	
	


	
	
	
}
