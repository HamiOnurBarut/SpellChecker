/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layoutdemo;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.text.*;
import javax.swing.text.Highlighter.HighlightPainter;

public class SpellPanel extends ImagePanel
{
    public JTextArea t1, t2;
    public JButton b1;
    public JComboBox c1;
    public String[] words;
    public int index, p0;
    
	public SpellPanel()
	{
                super("sc.png");
                setLayout(null);

		JLabel l1 = new JLabel ( "Enter the text here:" );
		JLabel l2 = new JLabel ( "Correct Version:" );
		
		t1 = new JTextArea (5, 40);
                Font font = new Font("Verdana", Font.BOLD, 13);
                t1.setFont(font);
                t1.setForeground(Color.BLUE);
		c1 = new JComboBox();
                c1.setMaximumRowCount(6);
                c1.setEnabled(false);
                c1.addItem("");
		b1 = new JButton ("Spell it!");
		t2 = new JTextArea (5, 40);
                t1.setLineWrap(true);
                t2.setLineWrap(true);
                final Highlighter hilit = t1.getHighlighter();
                final HighlightPainter painterR = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
                final HighlightPainter painterG = new DefaultHighlighter.DefaultHighlightPainter(Color.green);
                final CsProje dictionary = new CsProje();
                
		b1.addActionListener ( new ActionListener() 
		{
			public void actionPerformed (ActionEvent e)
			{
                            if(b1.getText() == "Spell it!"){
                                if(t1.getText() != null){
                                    t2.setText("");
                                    b1.setText("CHANGE");
                                    t1.setEnabled(false);
                                    words = t1.getText().toLowerCase().split(" ");
                                    c1.setSelectedItem("");
                                    p0 = 0;
                                }
                            }
                            if(b1.getText() == "CHANGE" || b1.getText() == "NEXT"){
                                try{
                                    if(index != words.length){
                                        t2.append(c1.getSelectedItem() + " ");
                                        String[] suggestions = dictionary.checkWord(words[index]);
                                        
                                        if(suggestions.length != 0){
                                            int p1 = p0 + words[index].length();
                                            hilit.addHighlight(p0, p1, painterR);
                                            c1.setVisible(true);
                                            c1.setEnabled(true);
                                            c1.removeAllItems();
                                            c1.addItem(words[index]);
                                            for(String str: suggestions)
                                                c1.addItem(str);
                                            b1.setText("CHANGE");
                                        }
                                        else{
                                            c1.setEnabled(false);
                                            c1.removeAllItems();
                                            c1.addItem(words[index]);
                                            b1.setText("NEXT");
                                            int p1 = p0 + words[index].length();
                                            hilit.addHighlight(p0, p1, painterG);
                                        }
                                        p0 += words[index].length() + 1;
                                        index++;
                                    }
                                    else{
                                        t2.append((String)c1.getSelectedItem());
                                        b1.setText("Spell it!");
                                        c1.removeAllItems();
                                        c1.addItem("");
                                        c1.setEnabled(false);
                                        t1.setText(null);
                                        t1.setEnabled(true);
                                        index = 0;
                                    }
                                        
                                }catch(Exception ex){};
                            }
                            else{
                                
                            }
                        }
                });
                
		l1.setBounds(10, 10, 150, 10);
                t1.setBounds(10, 30, 400, 100);
                b1.setBounds(10, 140, 100, 30);
                c1.setBounds(160, 140, 100, 30);
                l2.setBounds(50, 85, 150, 10);
                t2.setBounds(10, 180, 400, 100);
                      
                add(l1);
		add(t1);
                add (b1);
                add (c1);
                add (t2);
                add (l2);
	}
}