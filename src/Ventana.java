import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;

public class Ventana extends JFrame{
	private Panel panel;
	
	public Ventana(){
		super();
		this.panel = new Panel();
		this.add(panel);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Ventana();
	}
}

class Panel extends JPanel{
	private JButton btnBuscar;
	String[] nombreEnzimas, secuencias;
	
	DNAStringMatching dsm;
	
	JComboBox<String> listaEnzimas;
// -----------------------------------------
	private StyledDocument document;
	
	private JTextPane textPane;
	private JScrollPane	scrollPane2;
	SimpleAttributeSet keyWord;
	
	private int index;
	
// -----------------------------------------	
	
	public Panel() {
		super();
		
		this.setPreferredSize(new Dimension(1200,810));
		this.setBackground(Color.darkGray.darker());
		this.setLayout(null);
		dsm = new DNAStringMatching();
		
		dsm.ingresaTexto();
		dsm.procesarArchivo();
		dsm.crearTablas();
		
		nombreEnzimas = obtenerEnzimas(dsm.getEnzima());
		keyWord = new SimpleAttributeSet();
	    StyleConstants.setForeground(keyWord, Color.RED);
	    StyleConstants.setBold(keyWord, true);
		crearBotones(nombreEnzimas);
		
//		pintaLetras("hola jeje ",false);
//		pintaLetras("negro ",false);
//		pintaLetras("esto va en rojo",true);
	}
	
	public void pintaLetras(String texto, boolean flag) {
		if (flag) {
			try { document.insertString(document.getLength(), texto, keyWord); }
	        catch (BadLocationException e){}
		}
		else {
			try { document.insertString(document.getLength(), texto, null); }
	        catch (BadLocationException e){}
		}
	}
	
	private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }
	
	
	
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.btnBuscar.setVisible(true);
//		this.pane.setVisible(false);
//		scrollPane.setVisible(false);
		scrollPane2.setVisible(true);
		listaEnzimas.setVisible(true);
//		popupMenu.setVisible(true);
		
		//this.scrollPane.setVisible(true);
		
		
//		g.setColor(Color.WHITE);
//		g.fillRect(200, 20, 900, 750);
//		
//		g.setColor(Color.red);
		
	}
	
	private void crearBotones(String[] enzimas) {
		
		listaEnzimas = new JComboBox<String>(enzimas);
		this.add(listaEnzimas);
		listaEnzimas.setBounds(10, 10 , 200, 50);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		
		//textPane.setText("This is demo text1. This is demo text2. This is demo text3. This is demo text4.This is demo text5. This is demo text6. This is demo text7. This is demo text8. This is demo text9.  This is demo text10. This is demo text11. This is demo text12. This is demo text13. This is demo text13. This is demo text14. This is demo text15. This is demo text13. This is demo text16. This is demo text17. This is demo text13. This is demo text18. This is demo text19.This is demo text13.This is demo text20.\n ");
		
		
		
//		textPane.setText(prueba);
		
		scrollPane2 = new JScrollPane(textPane);
		scrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setBounds(250, 20, 900, 750);
		this.add(scrollPane2);
		
		document = textPane.getStyledDocument();
		
		this.btnBuscar = new JButton("Buscar");
		this.btnBuscar.setFont(new Font("Arial",Font.PLAIN,35));
		this.btnBuscar.setForeground(Color.white);
		this.btnBuscar.setBounds(0, 70 , 170, 70);
		this.btnBuscar.setBackground(null);
		this.btnBuscar.setBorderPainted(false);
		this.add(this.btnBuscar);
		this.btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("");
				index = listaEnzimas.getSelectedIndex();
				System.out.println("Enzima seleccionada: "+ listaEnzimas.getItemAt(index));
				dsm.busquedaGeneral(listaEnzimas.getSelectedIndex());
				String prueba = dsm.getAdn();
				ArrayList<Integer> indexes = dsm.getIndexes();
//				for(Integer arr: indexes) {
//						System.out.println(arr);
//					
//				}
				int tamanoSecuencia = secuencias[index].length();
				int pos = 0;
//				System.out.println("length: "+prueba.length());
				for (int i = 0; i<prueba.length()-1 ;i++) {
//					System.out.println(i);
					if (i%50 == 0){
						pintaLetras(" ",false);
					}
					if(!(pos == indexes.size())) {
						if (i == indexes.get(pos)+1) {
//							System.out.println("entro!");
							for(int j = 0; j < tamanoSecuencia; j++) {
								pintaLetras(Character.toString(prueba.charAt(i)),true);
								i++;
							}
							pos++;
						}
					}
					
//					System.out.println(Character.toString(prueba.charAt(i)));
					
					pintaLetras(Character.toString(prueba.charAt(i)),false);
				}
				repaint();
				
				
				
				
				
			}
		});
		
//		document = panel.getStyledDocument();
//		 Style style = panel.addStyle("I'm a Style", null);
//	        StyleConstants.setForeground(style, Color.red);
//
//	        try { document.insertString(document.getLength(), "BLAH ",style); }
//	        catch (BadLocationException e){}
//		document = pane.getStyledDocument();
//		style = pane.addStyle("I'm a Style", null);
//		pintaNegro("asdfadfs", Color.red);
//		frame.getContentPane().add(pane);
		
		
		
		
		
		
		
//		style = textPane.addStyle("Main Style", null);
	}
	
	private String[] obtenerEnzimas(ArrayList<String[]> enzimas){
		String[] nombres = new String[enzimas.size()];
		this.secuencias = new String[enzimas.size()];
		int pos = 0;
		for(String[] enzima: enzimas) {
			this.secuencias[pos] = enzima[1];
			nombres[pos++] = enzima[0];
		}
		return nombres;
	}
}
