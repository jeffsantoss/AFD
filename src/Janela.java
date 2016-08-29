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
	private JPanel PanelTable,PanelTexts;
	private JTextField textAlfabeto,textEstado;
	private JTextField textDelta,textEstadoFIM;
	private JTextField textEstadoINI,textInput;
	private JButton newValue,verificar,montarTabela,editar; 
	
	public Janela(){
		super("Autômato Deterministico Finito");
		PanelTable = new JPanel(new MigLayout());
		PanelTable.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		PanelTable.setBounds(0, 220, 805, 450);
		
		PanelTexts = new JPanel(new MigLayout());
		PanelTexts.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		PanelTexts.setBounds(0, 0, 500, 350);

		textAlfabeto = new JTextField(20);
		textEstado =  new JTextField(20);
		textDelta = new JTextField(50);
		textEstadoFIM =  new JTextField(20);
		textEstadoINI = new JTextField(20);
		textInput = new JTextField(20);
		
		PanelTexts.add(new JLabel("Alfabeto"));
		PanelTexts.add(textAlfabeto,"wrap");
		PanelTexts.add(new JLabel("Estados"));
		PanelTexts.add(textEstado,"wrap");
		PanelTexts.add(new JLabel("Delta"));
		PanelTexts.add(textDelta,"wrap");
		PanelTexts.add(new JLabel("Estados Finais"));
		PanelTexts.add(textEstadoFIM,"wrap");
		PanelTexts.add(new JLabel("Estados iniciais"));
		PanelTexts.add(textEstadoINI,"wrap");
		PanelTexts.add(new JLabel("Entrada"));
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
		editar = new JButton("Editar AFD atual");
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
		try {
			automato.definirEstadoInicial(textEstadoINI.getText());
		} catch (AutomatoException e) {
			automato = null;
		}
		automato.definirEstadosFinais(textEstadoFIM.getText());	
		try {
			automato.validarEntrada(textInput.getText());
		} catch (AutomatoException e) {
			automato = null;
		}
	}
	
	public void EditarJanela(){
		setContentPane(new JPanel());
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setMinimumSize(new Dimension(500,500));
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
		table.setValueAt("        E/A", 0, 0);

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
				if(automato.isValido())
					JOptionPane.showMessageDialog(null, "Seu autômato é válido!");
				else
					JOptionPane.showMessageDialog(null, "Seu autômato é inválido!");
				montarTabela.setEnabled(true);
			} else if(e.getSource() == montarTabela){
				SetarValores();
				mostrarTabela();
				montarTabela.setEnabled(false);
				verificar.setEnabled(true);
				DesabilitarCampos();
			} else if(e.getSource() == editar){
				habilitarCampos();
				verificar.setEnabled(false);
			}
	}

}
