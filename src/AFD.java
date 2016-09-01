import java.util.ArrayList;

import javax.swing.JOptionPane;


public class AFD {
	
	private ArrayList<Transicao> listTransicao = new ArrayList<Transicao>();
	private String Alfabeto = "";
	private String Estados = "";
	private String EstadosFinal = "";
	private int EstadoInicial;
	private String Entrada = "";
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
		EstadosFinal.replaceAll(",", "");
		EstadosFinal.replaceAll(" ", "");
			for(int i=0;i<estadosFinais.length();i++){
				if(estadosFinais.charAt(i) == ',' || estadosFinais.charAt(i) == ' ')
					continue;
				else
					this.EstadosFinal = this.EstadosFinal  + estadosFinais.charAt(i);
			}	
	}

	
		
	public int getEstadoInicial() {
		return EstadoInicial;
	}

	public void setEstadoInicial(int estadoInicial) {
		EstadoInicial = estadoInicial;
	}

	public void validarTransi��o(Transicao trans) throws AutomatoException{
		
		if (Alfabeto.indexOf(trans.getElemento()) == -1 )	// isso mostra se existe ou n�o
			throw new AutomatoException(this, "AUT�MATO INV�LIDO! Verifique se o elemento " +trans.getElemento()+ " da sua transi��o pertence ao seu alfabeto");
		if (Estados.indexOf(Integer.toString(trans.geteOrigem())) == -1 || Estados.indexOf(Integer.toString(trans.geteDestino())) == -1) 
			throw new AutomatoException(this, "AUT�MATO INV�LIDO! Verifique se seu estados de transi��o");
	}
	
	public void validarEntrada(String Input) throws AutomatoException {
		
		int EstadoCorrente = EstadoInicial;
		int countElementoI = 0;
		int elementosDiferentes = 0;// conta os elementos do estado inicial
		
		for(int i = 0;i<Input.length();i++) 
		{
			if (Alfabeto.indexOf(Input.charAt(i)) == -1 )	{// isso mostra se existe ou n�o
				this.valido = false;
					throw new AutomatoException(this, "AUT�MATO INV�LIDO! Verifique se os elementos da sua entrada pertence ao alfabeto");
			}
		}	
		
		
		for(int i = 0;i<listTransicao.size();i++){
			if(listTransicao.get(i).geteOrigem() == EstadoInicial)
				countElementoI++;
		}
		for(int i = 0;i<listTransicao.size();i++){
			if(listTransicao.get(i).geteOrigem() == EstadoInicial
			   && Input.charAt(0) != listTransicao.get(i).getElemento())
				 elementosDiferentes++;
		}
		System.out.println(elementosDiferentes);
		System.out.println(countElementoI);
		
		// se meu primeiro char do input for diferente de todos elementos do estado inicial
		if(elementosDiferentes == countElementoI){ 
			throw new AutomatoException(this, "verifique se seu primeiro elemento sai de um estado inicial");
		}
	
		
		for(int i = 0;i<Input.length();i++){
			for (int j = 0; j<listTransicao.size(); j++){
				if(Input.charAt(i) == listTransicao.get(j).getElemento()
				   && listTransicao.get(j).geteOrigem() == EstadoCorrente) {
					EstadoCorrente = listTransicao.get(j).geteDestino();
					Entrada +="q"+listTransicao.get(j).geteOrigem() + " -> "+ Input.charAt(i) + " -> "+"q"+EstadoCorrente+"\n";
					break;
				}
			}
		}
		
		/*
			if(EstadoCorrente == EstadoInicial){
				this.valido = false;
			
				Entrada += "�LTIMO ESTADO � INICIAL ";
		*/
			if(EstadosFinal.indexOf(Integer.toString(EstadoCorrente)) == -1) {
				this.valido = false;
				Entrada += "A SUA ENTRADA N�O FINALIZOU EM UM ESTADO FINAL";
			} else
			    Entrada += "Aut�mato validado";
			
}
		
	public String getEntrada() {
		return Entrada;
	}

	public void setEntrada(String entrada) {
		Entrada = entrada;
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
		return EstadosFinal.replaceAll(",", "");
	}

	public void setEstadosFinal(String estadosFinal) {
		EstadosFinal = estadosFinal;
	}



	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}
	
	


	
	
	
}
