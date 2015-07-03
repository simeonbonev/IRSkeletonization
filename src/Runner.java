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
import javax.swing.JOptionPane;

import java.awt.GridLayout;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class Runner extends JFrame implements ActionListener {
	private BufferedImage originalImage;
	private ImageIcon originalImageIcon;
	private JLabel originalJLabel;
	private int[][] currentMatrix;

	private BufferedImage editedImage;
	private ImageIcon editedImageIcon;
	private JLabel editedJLabel;

	private JButton processButton;
	private JButton saveButton;
	private JButton binarizeButton;
	private JButton blurButton;
	private JButton desaturateButton;
	private JButton invertButton;
	private JButton dTransformButton;
	private JButton erodeButton;
	private JRadioButton rdbtnManhattan;
	private JRadioButton rdbtnEuclidean;
	private JRadioButton rdbtnChebyshov;

	Runner(String stringUrl) throws IOException {
		setPreferredSize(new Dimension(800, 500));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("skeletonization.png"));
		getContentPane().setBackground(Color.WHITE);
		URL url = new URL(stringUrl);

		originalImage = ImageIO.read(url);
		originalImageIcon = new ImageIcon(originalImage);

		editedImage = ImageIO.read(url);
		editedImageIcon = new ImageIcon(editedImage);
		
		currentMatrix = new int[editedImage.getWidth()][editedImage.getHeight()];

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
		binarizeButton
				.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		binarizeButton.setBorder(new EmptyBorder(7, 20, 7, 20));
		binarizeButton.setBackground(new Color(30, 144, 255));
		binarizeButton.setForeground(Color.WHITE);
		binarizeButton.addActionListener(this);
		originalJLabel = new JLabel(originalImageIcon);
		originalJLabel.setText("");
		editedJLabel = new JLabel(editedImageIcon);
		editedJLabel.setText("");
		editedJLabel.setToolTipText("Edited image");

		blurButton = new JButton("Blur");
		blurButton.setBorder(new EmptyBorder(7, 32, 7, 32));
		blurButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blurButton.setForeground(Color.WHITE);
		blurButton.setBackground(new Color(255, 153, 0));
		blurButton.setOpaque(true);
		blurButton.addActionListener(this);

		rdbtnManhattan = new JRadioButton("Manhattan");
		rdbtnManhattan.setSelected(true);

		rdbtnEuclidean = new JRadioButton("Euclidean");

		rdbtnChebyshov = new JRadioButton("Chebyshov");

		desaturateButton = new JButton("Desaturate");
		desaturateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		desaturateButton.setForeground(Color.WHITE);
		desaturateButton.setBorder(new EmptyBorder(7, 10, 7, 10));
		desaturateButton.setBackground(Color.GRAY);
		desaturateButton.setOpaque(true);
		desaturateButton.addActionListener(this);

		invertButton = new JButton("Invert");
		invertButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		invertButton.setBorder(new EmptyBorder(7, 27, 7, 27));
		invertButton.setBackground(new Color(250, 128, 114));
		invertButton.setForeground(Color.WHITE);
		invertButton.setOpaque(true);
		invertButton.addActionListener(this);

		dTransformButton = new JButton("D Transform");
		dTransformButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dTransformButton.setForeground(Color.WHITE);
		dTransformButton.setBackground(new Color(123, 104, 238));
		dTransformButton.setBorder(new EmptyBorder(7, 10, 7, 10));
		dTransformButton.setOpaque(true);
		dTransformButton.addActionListener(this);

		erodeButton = new JButton("Erode");
		erodeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		erodeButton.setForeground(Color.WHITE);
		erodeButton.setBorder(new EmptyBorder(7, 30, 7, 30));
		erodeButton.setBackground(new Color(255, 215, 0));
		erodeButton.setOpaque(true);
		erodeButton.addActionListener(this);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				rdbtnManhattan)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				rdbtnEuclidean)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				rdbtnChebyshov))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								originalJLabel,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addGroup(
																								Alignment.LEADING,
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												desaturateButton)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												blurButton)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												binarizeButton)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												invertButton)))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												dTransformButton)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												erodeButton)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												processButton,
																												GroupLayout.PREFERRED_SIZE,
																												106,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												saveButton,
																												GroupLayout.PREFERRED_SIZE,
																												93,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								editedJLabel,
																								GroupLayout.PREFERRED_SIZE,
																								401,
																								GroupLayout.PREFERRED_SIZE))))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																desaturateButton)
														.addComponent(
																blurButton)
														.addComponent(
																binarizeButton)
														.addComponent(
																invertButton)
														.addComponent(
																dTransformButton)
														.addComponent(
																erodeButton)
														.addComponent(
																processButton)
														.addComponent(
																saveButton))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																rdbtnManhattan)
														.addComponent(
																rdbtnEuclidean)
														.addComponent(
																rdbtnChebyshov))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																editedJLabel,
																GroupLayout.DEFAULT_SIZE,
																401,
																Short.MAX_VALUE)
														.addComponent(
																originalJLabel,
																GroupLayout.DEFAULT_SIZE,
																401,
																Short.MAX_VALUE))
										.addContainerGap()));
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
					"http://bubbleletters.org/free-letter-downloads/green/wlm-cloudly-letters/256/bubble-letter-s.jpg");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == processButton) {
			toGrayscale();
			Filters.blurImage(
					new BufferedImage(editedImage.getColorModel(), editedImage
							.copyData(null),
							editedImage.isAlphaPremultiplied(), null),
					editedImage);
			binarize();
			ImageUtils.invert(editedImage);
			DistanceTransform.doTransform(editedImage, new Euclidean());
			// MAT.doTransform(editedImage);
			try {
				ImageIO.write(editedImage, "jpg", new File("InJFRAME.jpg"));
			} catch (IOException ioe) {
				// TODO Auto-generated catch block
				ioe.printStackTrace();
			}
		} else if (e.getSource() == desaturateButton) {
			toGrayscale();
		} else if (e.getSource() == invertButton) {
			ImageUtils.invert(editedImage);
		} else if (e.getSource() == dTransformButton){
			DistanceTransform.justDistanceTransform(editedImage, currentMatrix, new Euclidean());
		} else if (e.getSource() == erodeButton) {
			DistanceTransform.justErode(editedImage, currentMatrix, new Euclidean());
		} else if (e.getSource() == saveButton) {
			saveImage();
		} else if (e.getSource() == binarizeButton) {
			binarize();
		} else if (e.getSource() == blurButton) {
			Filters.blurImage(
					new BufferedImage(editedImage.getColorModel(), editedImage
							.copyData(null),
							editedImage.isAlphaPremultiplied(), null),
					editedImage);
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
		JOptionPane.showMessageDialog(this, "Image saved.");
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
