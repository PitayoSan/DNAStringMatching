import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	public Panel() {
		super();
		this.setPreferredSize(new Dimension(1200,810));
		this.setBackground(Color.darkGray.darker());
		this.setLayout(null);
		crearBotones();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.btnMst.setVisible(true);
		this.btnNot.setVisible(true);
		this.btnTaq.setVisible(true);
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
