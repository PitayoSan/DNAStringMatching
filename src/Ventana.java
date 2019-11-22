import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

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
	}
}

class Panel extends JPanel{
	private JButton btnMst,
					btnNot,
					btnTaq;
	
	
	private JScrollPane scrollPane;
	private String texto;

	private JTextArea pane;
	private StyledDocument document;
	private Style style;
	
	public Panel(JFrame frame) {
		super();
		this.setPreferredSize(new Dimension(1200,810));
		this.setBackground(Color.darkGray.darker());
		this.setLayout(null);
		crearBotones();
		
		
		
		
	}
	
	public void pintaNegro(String texto, Color color) {
		StyleConstants.setForeground(style, Color.red);
		try { document.insertString(document.getLength(), "BLAH ",style); }
        catch (BadLocationException e){}
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
		this.btnMst.setVisible(true);
		this.btnNot.setVisible(true);
		this.btnTaq.setVisible(true);
		this.pane.setVisible(true);
		scrollPane.setVisible(true);
		
		//this.scrollPane.setVisible(true);
		
		
		g.setColor(Color.WHITE);
		g.fillRect(200, 20, 900, 750);
		
		g.setColor(Color.red);
		
	}
	
	private void crearBotones() {
		this.btnMst = new JButton("MstII");
		this.btnMst.setFont(new Font("Arial",Font.PLAIN,55));
		this.btnMst.setForeground(Color.white);
		this.btnMst.setBounds(0, 0 , 170, 70);
		this.btnMst.setBackground(null);
		this.btnMst.setBorderPainted(false);
		this.add(this.btnMst);
		this.btnMst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("MstII");
				repaint();
			}
		});
		
		this.btnNot = new JButton("NotI");
		this.btnNot.setFont(new Font("Arial",Font.PLAIN,55));
		this.btnNot.setForeground(Color.white);
		this.btnNot.setBounds(0, 70 , 170, 70);
		this.btnNot.setBackground(null);
		this.btnNot.setBorderPainted(false);
		this.add(this.btnNot);
		this.btnNot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("NotI");
				repaint();
			}
		});
		
		this.btnTaq = new JButton("TaqI");
		this.btnTaq.setFont(new Font("Arial",Font.PLAIN,55));
		this.btnTaq.setForeground(Color.white);
		this.btnTaq.setBounds(0, 140 , 170, 70);
		this.btnTaq.setBackground(null);
		this.btnTaq.setBorderPainted(false);
		this.add(this.btnTaq);
		this.btnTaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("TaqI");
				repaint();
			}
		});
		
		String cadena ="holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaholaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		pane = new JTextArea(cadena);
		JTextPane panel = new JTextPane();
		panel.setText(cadena);
		pane.setWrapStyleWord(true);
		pane.setLineWrap(true);
		document = panel.getStyledDocument();
		 Style style = panel.addStyle("I'm a Style", null);
	        StyleConstants.setForeground(style, Color.red);

	        try { document.insertString(document.getLength(), "BLAH ",style); }
	        catch (BadLocationException e){}
//		document = pane.getStyledDocument();
//		style = pane.addStyle("I'm a Style", null);
//		pintaNegro("asdfadfs", Color.red);
//		frame.getContentPane().add(pane);
//		
		pane.setEditable(false);
		
		scrollPane = new JScrollPane(pane,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(200, 20, 900, 750);
		this.add(scrollPane);
		
	}
}
