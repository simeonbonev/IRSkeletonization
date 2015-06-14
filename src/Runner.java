import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Runner extends JFrame implements ActionListener{
	private BufferedImage originalImage;
	private ImageIcon originalImageIcon;
	private JLabel originalJLabel;
	
	private BufferedImage editedImage;
	private ImageIcon editedImageIcon;
	private JLabel editedJLabel;
	
	private JButton processButton;
	
	Runner(String stringUrl) throws IOException {
		URL url = new URL(stringUrl);
		setLayout(new FlowLayout());
		originalImage = ImageIO.read(url);
		originalImageIcon = new ImageIcon(originalImage);
		originalJLabel = new JLabel(originalImageIcon);
		add(originalJLabel);
		
		editedImage = ImageIO.read(url);
		editedImageIcon = new ImageIcon(editedImage);
		editedJLabel = new JLabel(editedImageIcon);
		add(editedJLabel);
		
		processButton = new JButton("Process");
		processButton.addActionListener(this);
		add(processButton);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setTitle("Image Recognition Skeletonization");
		
		
	}

	public static void main(String[] args) {
		Runner gui;
		try {
			gui = new Runner("http://dsc.carsifu.com/wp-content/uploads/2014/12/Merc-CapaCity-L-main01.jpg");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		toGrayscale();
		repaint();
	}

	private void toGrayscale() {
		for (int i = 0; i < editedImage.getWidth(); i++) {
			for (int j = 0; j < editedImage.getHeight(); j++) {
				Color currentPixelColor = new Color(editedImage.getRGB(i, j));
				int newValue = (int)Math.round((currentPixelColor.getRed() + currentPixelColor.getGreen() + currentPixelColor.getBlue())/3.0);
				Color newColor = new Color(newValue, newValue, newValue);
				editedImage.setRGB(i, j, newColor.getRGB());
			}
		}
	}

		
}
