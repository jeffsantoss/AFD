import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;


public class Janela extends JFrame implements ActionListener{
	
	private JTable table = new JTable();
	private DefaultTableModel model;
	private AFD automato;
	private JPanel PanelTable,PanelTexts,PanelEntrada;
	private JTextField textAlfabeto,textEstado;
	private JTextField textDelta,textEstadoFIM;
	private JTextField textEstadoINI,textInput;
	private JButton newValue,verificar,montarTabela,editar; 
	
	public Janela(){
		super("Autômato Deterministico Finito");

		PanelTexts = new JPanel(new MigLayout());
		PanelTexts.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		PanelTexts.setBounds(0, 0, 530, 350);
		
		PanelTable = new JPanel(new MigLayout());
		PanelTable.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		PanelTable.setBounds(0, 220, 805, 450);
		

		PanelEntrada = new JPanel(new MigLayout());
		PanelEntrada.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		PanelEntrada.setBounds(0, 500, 500, 350);
		
		textAlfabeto = new JTextField(20);
		textEstado =  new JTextField(20);
		textDelta = new JTextField(50);
		textEstadoFIM =  new JTextField(20);
		textEstadoINI = new JTextField(20);
		textInput = new JTextField(20);
		
		PanelTexts.add(new JLabel("Alfabeto ( Σ ) - Ex: a,b,c "));
		PanelTexts.add(textAlfabeto,"wrap");
		PanelTexts.add(new JLabel("Estados ( Q ) - Ex: 1,2,3"));
		PanelTexts.add(textEstado,"wrap");
		PanelTexts.add(new JLabel("Delta ( δ ) - Ex: 1c1, 1a2, 2b3"));
		PanelTexts.add(textDelta,"wrap");
		PanelTexts.add(new JLabel("Estados Finais (F) - Ex: 2,3"));
		PanelTexts.add(textEstadoFIM,"wrap");
		PanelTexts.add(new JLabel("Estados iniciais (I) - Ex: 1"));
		PanelTexts.add(textEstadoINI,"wrap");
		PanelTexts.add(new JLabel("Entrada - Ex aaabbcc"));
		PanelTexts.add(textInput,"wrap");
		
		newValue = new JButton("Novo AFD");
		PanelTexts.add(newValue);
		newValue.addActionListener(this);
		verificar = new JButton("Validar Automâto");
		PanelTexts.add(verificar);
		verificar.addActionListener(this);
		verificar.setEnabled(false);
		montarTabela = new JButton("Preencher Tabela");
		PanelTexts.add(montarTabela);
		montarTabela.addActionListener(this);
		montarTabela.setEnabled(false);
		editar = new JButton("Editar AFD");
		PanelTexts.add(editar);
		editar.addActionListener(this);
		editar.setEnabled(false);

		EditarJanela();
		
		PanelTable.add(table);
		DesabilitarCampos();
		this.add(PanelTexts);
		this.add(PanelTable);

	}
	
	public void SetarValores(){
		automato = new AFD();
		automato.setAlfabeto(textAlfabeto.getText());
		automato.setarEstados(textEstado.getText());
		automato.Delta(textDelta.getText());
		automato.setEstadoInicial(Integer.parseInt(textEstadoINI.getText()));
		automato.definirEstadosFinais(textEstadoFIM.getText());	
	}
	
	public void EditarJanela(){
		setContentPane(new JPanel());
		setLayout(null);
		//setResizable(false);
		setVisible(true);
		setMinimumSize(new Dimension(550,500));
		setLocationRelativeTo(null);
	}
	
	public void habilitarCampos(){
		textAlfabeto.setEditable(true);
		textEstado.setEditable(true);
		textDelta.setEditable(true);
		textEstadoFIM.setEditable(true);
		textEstadoINI.setEditable(true);
		textInput.setEditable(true);
	}
	
	
	public void DesabilitarCampos(){
		textAlfabeto.setEditable(false);;
		textEstado.setEditable(false);
		textDelta.setEditable(false);
		textEstadoFIM.setEditable(false);
		textEstadoINI.setEditable(false);
		textInput.setEditable(false);
	}
	
	public void mostrarTabela(){
		
		int posEstado,posAlfabeto;
		
		model = (DefaultTableModel) table.getModel();
	
		model.setColumnCount(automato.getAlfabeto().length() + 1);
		model.setRowCount(automato.getEstados().length() + 1);
		table.setValueAt("        Q/Σ ", 0, 0);

		for(int i =0;i<automato.getAlfabeto().length();i++){
			table.setValueAt(automato.getAlfabeto().charAt(i), 0, i+1);			
		}
		
		for(int i =0;i<automato.getEstados().length();i++) {
			table.setValueAt("q"+automato.getEstados().charAt(i), i+1, 0);
		}
		
		for(int posTransicao = 0;  posTransicao < automato.getListTransicao().size() ; posTransicao++){		
			for(posEstado = 0; posEstado < automato.getEstados().length() ; posEstado++)
			{
				if(automato.getListTransicao().get(posTransicao).geteOrigem() == Character.getNumericValue(automato.getEstados().charAt(posEstado))){
					break;
				}
				
			}
				for (posAlfabeto = 0; posAlfabeto < automato.getAlfabeto().length() ; posAlfabeto++)
				{
					if(automato.getListTransicao().get(posTransicao).getElemento() == automato.getAlfabeto().charAt(posAlfabeto)){
						break;
					}
				}
			
				table.setValueAt("q"+automato.getListTransicao().get(posTransicao).geteDestino(), posEstado + 1, posAlfabeto + 1);
		}
	}	
	

	public void LimparTudo() {
        int linhas = 0;  
        int colunas = 0;  
        String zer = null;  
        for (linhas = 0; linhas <= table.getRowCount() - 1; linhas++) {  
            for (colunas = 0; colunas <= table.getColumnCount() - 1; colunas++) {  
                table.setValueAt(zer, linhas, colunas);  
            }  
        }  
 
        textAlfabeto.setText("");
		textEstado.setText("");
		textDelta.setText("");
		textEstadoFIM.setText("");
		textEstadoINI.setText("");
		textInput.setText("");
        automato = null;
    }  


	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == newValue){
				LimparTudo();
				montarTabela.setEnabled(true);
				editar.setEnabled(true);
				verificar.setEnabled(false);
				habilitarCampos();
			} else if(e.getSource() == verificar){
				SetarValores();
				try {
					automato.validarEntrada(textInput.getText());
				} catch (AutomatoException e1) {
					automato = null;
				}
				if(automato.isValido())
					JOptionPane.showMessageDialog(null, "Seu autômato é válido!");
				else
					JOptionPane.showMessageDialog(null, "Seu autômato é inválido!");
				
				JOptionPane.showMessageDialog(null, "Veja o caminho da sua entrada\n" + automato.getEntrada());
				montarTabela.setEnabled(true);
			} else if(e.getSource() == montarTabela){
				SetarValores();
				mostrarTabela();
				montarTabela.setEnabled(false);
				verificar.setEnabled(true);
				DesabilitarCampos();
			} else if(e.getSource() == editar){
				habilitarCampos();
			}
	}

}
