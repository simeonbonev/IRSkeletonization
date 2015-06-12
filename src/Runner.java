import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Runner {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.mkyong.com/image/mypic.jpg");
			Image original = ImageIO.read(url);
			Image image = ImageIO.read(url);
			
			JFrame frame = new JFrame("IRSkeletonization");
			frame.setSize(300, 300);
			
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			
			JButton button = new JButton();
			button.setText("Process");
			
			JLabel label = new JLabel(new ImageIcon(original));

			frame.add(button);
	        frame.add(label);
	        frame.setLocationRelativeTo(null);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        frame.setVisible(true);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
