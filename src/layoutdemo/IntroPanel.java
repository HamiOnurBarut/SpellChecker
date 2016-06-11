/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layoutdemo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*			Date: 07.12.2015
 *
 * GUI						*/

import java.awt.*;
import javax.swing.*;

public class IntroPanel extends JPanel
{
	public IntroPanel()
	{
		setLayout ( new FlowLayout() );
		setBackground (Color.cyan);

		JLabel l1 = new JLabel ( "Welcome to SpellerBag. Please go to next section to enter your text and spell it." );
		JLabel l2 = new JLabel ("");

		add (l1);
		add (l2);
	}
}