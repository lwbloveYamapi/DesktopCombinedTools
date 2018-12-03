package com.edu.app;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class  Main
{
	public Main(){
		//º”‘ÿÕº∆¨
		ImageIcon icon1=new ImageIcon("b.png");
		JLabel label=new JLabel(icon1);

		label.setBounds(0,0,icon1.getIconWidth(),icon1.getIconHeight());

		JFrame frame=new JFrame();
		frame.setTitle("π§æﬂ");
		frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		JPanel j=(JPanel)frame.getContentPane();
		j.setOpaque(false);

		JPanel panel=new JPanel();
		JButton jb=new JButton();
		JButton jb2=new JButton();
		JButton jb3=new JButton();
	
		jb.setBorder(null);
		jb2.setBorder(null);
		jb3.setBorder(null);
		jb.setBounds(0, 0, 45, 45);
		jb2.setBounds(0, 0, 45, 45);
		jb3.setBounds(0, 0, 45, 45);

		ImageIcon icon = new ImageIcon("p.png"); 
		icon.getImage();
		Image temp = icon.getImage().getScaledInstance(jb.getWidth(),  
				jb.getHeight(), Image.SCALE_DEFAULT);  
		icon = new ImageIcon(temp);
		jb.setIcon(icon);

		icon = new ImageIcon("l.png"); 
		icon.getImage();
		temp = icon.getImage().getScaledInstance(jb3.getWidth(),  
				jb3.getHeight(), Image.SCALE_DEFAULT);  
		icon = new ImageIcon(temp);
		jb3.setIcon(icon);

		icon = new ImageIcon("c.png"); 
		icon.getImage();
		temp = icon.getImage().getScaledInstance(jb2.getWidth(),  
				jb2.getHeight(), Image.SCALE_DEFAULT);  
		icon = new ImageIcon(temp);
		jb2.setIcon(icon);
		panel.add(jb3);
		panel.add(jb);
		panel.add(jb2);
		panel.setOpaque(false);
		panel.setOpaque(false);
		frame.add(panel);
		frame.setSize(400,300);
		frame.setVisible(true);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false); 
				com.edu.Main.main(null);
			}          
		});

		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false); 
				com.edu.book.Main.main(null);
			}
		});

		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false); 
				com.edu.cal.Main.main(null);
			}
		});

	}
	public static void main(String[] args) 
	{
		new Main();
	}
}
