import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Dimension;

public class Runner extends JFrame implements ActionListener {
	private BufferedImage originalImage;
	private ImageIcon originalImageIcon;
	private JLabel originalJLabel;

	private BufferedImage editedImage;
	private ImageIcon editedImageIcon;
	private JLabel editedJLabel;

	private JButton processButton;
	private JButton saveButton;
	private JButton binarizeButton;

	Runner(String stringUrl) throws IOException {
		setPreferredSize(new Dimension(800, 500));
		getContentPane().setBackground(Color.WHITE);
		URL url = new URL(stringUrl);

		originalImage = ImageIO.read(url);
		originalImageIcon = new ImageIcon(originalImage);

		editedImage = ImageIO.read(url);
		editedImageIcon = new ImageIcon(editedImage);

		processButton = new JButton("Process");
		processButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		processButton.setMargin(new Insets(10, 10, 10, 10));
		processButton.setForeground(Color.WHITE);
		processButton.setOpaque(true);
		processButton.setBackground(new Color(204, 0, 51));
		processButton.setBorder(new EmptyBorder(7, 20, 7, 20));
		processButton.addActionListener(this);

		saveButton = new JButton("Save");
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveButton.setMargin(new Insets(10, 10, 10, 10));
		saveButton.setForeground(Color.WHITE);
		saveButton.setBackground(new Color(51, 153, 102));
		saveButton.setOpaque(true);
		saveButton.setBorder(new EmptyBorder(7, 20, 7, 20));
		saveButton.addActionListener(this);

		binarizeButton = new JButton("Binarize");
		binarizeButton.setMargin(new Insets(10, 10, 10, 10));
		binarizeButton.setOpaque(true);
		binarizeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		binarizeButton.setBorder(new EmptyBorder(7, 20, 7, 20));
		binarizeButton.setBackground(new Color(30, 144, 255));
		binarizeButton.setForeground(Color.WHITE);
		binarizeButton.addActionListener(this);
		originalJLabel = new JLabel(originalImageIcon);
		originalJLabel.setText("Original image");
		editedJLabel = new JLabel(editedImageIcon);
		editedJLabel.setText("Edited image");
		editedJLabel.setToolTipText("Edited image");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(binarizeButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(processButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(originalJLabel, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(editedJLabel, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(450))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(binarizeButton)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(saveButton)
							.addComponent(processButton)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(originalJLabel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(editedJLabel, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setTitle("Image Recognition Skeletonization");

	}

	public static void main(String[] args) {
		Runner gui;
		try {
			gui = new Runner(
					"http://us.123rf.com/450wm/kroomjai/kroomjai1110/kroomjai111000022/10797656-number-1-sunflower-isolate-on-white-background.jpg");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == processButton) {
			toGrayscale();
			binarize();
		} else if (e.getSource() == saveButton) {
			saveImage();
		} else if (e.getSource() == binarizeButton) {
			binarize();
		}
		repaint();
	}

	private void binarize() {
		Binarizator binarizator = new Binarizator(editedImage);
		binarizator.binarize();
	}

	private void saveImage() {
		File outputFile = new File("IRSkeletonization_"
				+ System.currentTimeMillis() + ".jpg");
		try {
			ImageIO.write(editedImage, "jpg", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void toGrayscale() {
		for (int i = 0; i < editedImage.getWidth(); i++) {
			for (int j = 0; j < editedImage.getHeight(); j++) {
				Color currentPixelColor = new Color(editedImage.getRGB(i, j));
				int newValue = (int) Math.round((currentPixelColor.getRed()
						+ currentPixelColor.getGreen() + currentPixelColor
						.getBlue()) / 3.0);
				Color newColor = new Color(newValue, newValue, newValue);
				editedImage.setRGB(i, j, newColor.getRGB());
			}
		}
	}

}
