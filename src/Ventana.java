import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.panel = new Panel(this);
		this.add(panel);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Ventana();
		System.out.println("fin");
	}
}

class Panel extends JPanel{
	private JButton btnMst,
					btnBuscar,
					btnTaq;
	
	JComboBox<String> bookList;
// -----------------------------------------
	private StyledDocument document;
	private Style style;
	
	private JTextPane textPane;
	private JScrollPane	scrollPane2;
	
// -----------------------------------------	
	
	public Panel(JFrame frame) {
		super();
		
		this.setPreferredSize(new Dimension(1200,810));
		this.setBackground(Color.darkGray.darker());
		this.setLayout(null);
		String[] array = {"hola","no","v","si"};
		crearBotones(array);
//		style = textPane.addStyle("Main Style", null);
		pintaNegro("hola jeje ",false);
//		pintaNegro("negro ",false);
//		pintaNegro("esto va en rojo",true);
	}
	
	public void pintaNegro(String texto, boolean flag) {
		style = textPane.addStyle("Main Style", null);
		if (flag) {
			StyleConstants.setForeground(style, Color.red);
			try { document.insertString(document.getLength(), texto,style); }
	        catch (BadLocationException e){}
		}
		else {
			StyleConstants.setForeground(style, Color.black);
			try { document.insertString(document.getLength(), texto,style); }
	        catch (BadLocationException e){}
		}
	}
	
	
	
//	public void paintBlackText(String textito) {
//		
//		
//		StyleContext context = new StyleContext();
//		// build a style
//		Style style = context.addStyle("test", null);
//		// set some style properties
//		StyleConstants.setForeground(style, Color.BLUE);
//		// add some data to the document
//		try {
//			document.insertString(0, textito, style);
//		} catch (BadLocationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
	
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
		scrollPane2.setVisible(false);
		bookList.setVisible(true);
//		popupMenu.setVisible(true);
		
		//this.scrollPane.setVisible(true);
		
		
//		g.setColor(Color.WHITE);
//		g.fillRect(200, 20, 900, 750);
//		
//		g.setColor(Color.red);
		
	}
	
	private void crearBotones(String[] array) {
		
		
		bookList = new JComboBox<String>(array);
		this.add(bookList);
		bookList.setBounds(10, 10 , 200, 50);
		
		this.btnBuscar = new JButton("Buscar");
		this.btnBuscar.setFont(new Font("Arial",Font.PLAIN,35));
		this.btnBuscar.setForeground(Color.white);
		this.btnBuscar.setBounds(0, 70 , 170, 70);
		this.btnBuscar.setBackground(null);
		this.btnBuscar.setBorderPainted(false);
		this.add(this.btnBuscar);
		this.btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Enzima seleccionada: "+ bookList.getItemAt(bookList.getSelectedIndex()));
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
		
		
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		/*
		textPane.setText("This is demo text1. This is demo text2. This is demo text3."
		         + "This is demo text4.This is demo text5. This is demo text6. "
		         + "This is demo text7. This is demo text8. This is demo text9. "
		         + "This is demo text10. This is demo text11. This is demo text12."
		         + "This is demo text13. This is demo text13. This is demo text14."
		         + "This is demo text15. This is demo text13. This is demo text16."
		         + " This is demo text17. This is demo text13. This is demo text18."
		         + " This is demo text19.This is demo text13.This is demo text20.");
		*/
		scrollPane2 = new JScrollPane(textPane);
		scrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setBounds(200, 20, 900, 750);
		this.add(scrollPane2);
		
		document = textPane.getStyledDocument();
//		style = textPane.addStyle("Main Style", null);
		
		
		
	}
}
