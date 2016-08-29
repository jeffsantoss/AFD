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
	
	public void AdicionarTransi��o(int eOrigem, char Elemento,int eDestino){		
		int index = listTransicao.size();
		
		try {
			listTransicao.add(new Transicao(eOrigem,Elemento,eDestino));
			validarTransi��o(listTransicao.get(index)); // pega sempre o �ltimo e valida.
		} catch (Exception e) {
			this.valido = false;
		}
		index--;
	}
	
	public void definirEstadosFinais(String estadosFinais) {
			for(int i=0;i<estadosFinais.length();i++){
				if(estadosFinais.charAt(i) == ',' || estadosFinais.charAt(i) == ' ')
					continue;
				else
					this.EstadosFinal = this.EstadosFinal  + estadosFinais.charAt(i);
			}	
	}

	public void definirEstadoInicial(String estadoInicial) throws AutomatoException {
		
		estadoInicial.replaceAll(",", "");
		estadoInicial.replaceAll(" ", "");
		
		if(Estados.indexOf(estadoInicial) == -1)
			throw new AutomatoException(this,"AUT�MATO INV�LIDO! O estado inicial q"+Integer.parseInt(estadoInicial)+" n�o pertence aos estados");
			if(estadoInicial.length() > 1) {
				this.valido = false;
					throw new AutomatoException(this,"AUT�MATO INV�LIDO! S� pode haver apenas 1 estado Inicial");
			}
	}
		
	public void validarTransi��o(Transicao trans) throws AutomatoException{
		
		if (Alfabeto.indexOf(trans.getElemento()) == -1 )	// isso mostra se existe ou n�o
			throw new AutomatoException(this, "AUT�MATO INV�LIDO! Verifique se o elemento " +trans.getElemento()+ " da sua transi��o pertence ao seu alfabeto");
		if (Estados.indexOf(Integer.toString(trans.geteOrigem())) == -1 || Estados.indexOf(Integer.toString(trans.geteDestino())) == -1) 
			throw new AutomatoException(this, "AUT�MATO INV�LIDO! Verifique se seu estados de transi��o");
	}
	
	public void validarEntrada(String Input) throws AutomatoException {
		for(int i = 0;i<Input.length();i++) 
		{
			if (Alfabeto.indexOf(Input.charAt(i)) == -1 )	{// isso mostra se existe ou n�o
				this.valido = false;
					throw new AutomatoException(this, "AUT�MATO INV�LIDO! Verifique se os elementos da sua entrada pertence ao alfabeto");
			}
		}	
		
		if(Input.charAt(0) != listTransicao.get(0).getElemento()) {
			this.valido = false;
			throw new AutomatoException(this, "AUT�MATO INV�LIDO! O elemento" + Input.charAt(0) + "n�o sai do Estado Inicial");
		}
		// verifico os estados finais.

		for(int i=0;i<listTransicao.size();i++)
			if(Input.charAt(Input.length() - 1 ) == listTransicao.get(i).getElemento()) {
				for(int j=0;j<EstadosFinal.length();j++){
					if(listTransicao.get(i).geteDestino() != Character.getNumericValue(EstadosFinal.charAt(j))){
						System.out.println("Estado destino Transi��o: " +EstadosFinal.charAt(j));
						System.out.println("Estados finais: " +EstadosFinal.charAt(j));
							this.valido = false;
							throw new AutomatoException(this, "AUT�MATO INV�LIDO! o elemento " + Input.charAt(0) + " da sua entrada n�o terminou no estado final");
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
			this.AdicionarTransi��o(Character.getNumericValue(delta.charAt(i)), delta.charAt(i+1), Character.getNumericValue(delta.charAt(i+2)));		
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
