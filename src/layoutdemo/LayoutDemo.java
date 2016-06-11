/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layoutdemo;

/**
 * @(#)LayoutDemo.java
 *
 * LayoutDemo application
 *
 * @author 
 * @version 1.00 2015/12/24
 */
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**		Date: 07.12.2015
 *
 * GUI						*/
import javax.swing.*;
import java.awt.*;

public class LayoutDemo
{
	public static void main ( String[] args )
	{
		JFrame frame = new JFrame ( "SpellerBAG" );
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(450, 400));
                frame.setResizable(false);
		JTabbedPane tp = new JTabbedPane();

		tp.addTab ( "Intro", new IntroPanel() );
		tp.addTab ( "Spell", new SpellPanel() );
		tp.addTab ( "Help", new HelpPanel() );

		frame.getContentPane().add(tp);
		frame.pack();
		frame.setVisible(true);
	}
}