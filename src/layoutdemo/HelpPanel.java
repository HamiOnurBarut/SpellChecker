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

/**			Date: 07.12.2015
 *
 * GUI						*/

import java.awt.*;
import javax.swing.*;

public class HelpPanel extends JPanel
{
	public HelpPanel()
	{
		setLayout ( new FlowLayout() );
		setBackground (Color.yellow);

		JLabel l1 = new JLabel ( "Hello, welcome to our application.\n\nHelp will be here after a little time later." );

		add (l1);
	}
}