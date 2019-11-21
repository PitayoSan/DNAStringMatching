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
import javax.swing.text.BadLocationException;
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
	private JButton btnMst,
					btnNot,
					btnTaq;
	
	private JTextPane pane;
	private JScrollPane scrollPane;
	private String texto;
	
	public Panel() {
		super();
		this.setPreferredSize(new Dimension(1200,810));
		this.setBackground(Color.darkGray.darker());
		this.setLayout(null);
		crearBotones();
		
		
		pane = new JTextPane();
		pane.setEditable(false);
		scrollPane = new JScrollPane(pane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(300, 200, 300, 200);
		scrollPane.setVisible(true);
		this.add(scrollPane);
		texto = "";
		paintText('a');
		
		
		
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
	
	
	public void paintText(char textito) {
		boolean flag = true;
		pane.setText(this.texto + textito);
		
		StyledDocument doc = pane.getStyledDocument();
        //Style style = pane.addStyle("I'm a Style", null);
        
        appendToPane(pane, "My Name is Too Good.\n", Color.RED);
        getContentPane().add();
        /*
        StyleConstants.setForeground(style, Color.red);
        
		try { doc.insertString(doc.getLength(), textito +"",style); }
		catch (BadLocationException e){
			System.out.println("error");
		}
        */
        
        //char[]texto= textito.toCharArray();
        
        
        /*
        for(int i=0;i<texto.length;i++) {
        		if(flag) {
        			StyleConstants.setForeground(style, Color.red);
        			try { doc.insertString(doc.getLength(), texto[i]+"",style); }
        			catch (BadLocationException e){}
        		}
        		else {
        			StyleConstants.setForeground(style, Color.black);
        			try { doc.insertString(doc.getLength(), texto[i]+"",style); }
        			catch (BadLocationException e){}
        		}
        }
        */
        
	}
	
	
	public void paintBlackText(char character) {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.btnMst.setVisible(true);
		this.btnNot.setVisible(true);
		this.btnTaq.setVisible(true);
		this.pane.setVisible(true);
		this.scrollPane.setVisible(true);
		
		
		g.setColor(Color.WHITE);
		g.fillRect(200, 20, 900, 750);
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
	}
}
