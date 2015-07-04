import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

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
	private ButtonGroup buttonGroup;
	private JButton chooseFileButton;
	private JFileChooser jFileChooser;
	private JButton randomButton;

	Runner(String stringUrl) throws IOException {
		setPreferredSize(new Dimension(800, 500));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("skeletonization.png"));
		getContentPane().setBackground(Color.WHITE);
		// URL url = new URL(stringUrl);
		//
		// originalImage = ImageIO.read(url);
		// originalImageIcon = new ImageIcon(originalImage);
		//
		// editedImage = ImageIO.read(url);
		// editedImageIcon = new ImageIcon(editedImage);
		//
		// currentMatrix = new
		// int[editedImage.getWidth()][editedImage.getHeight()];

		processButton = new JButton("Process");
		processButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		processButton.setMargin(new Insets(10, 10, 10, 10));
		processButton.setForeground(Color.WHITE);
		processButton.setOpaque(true);
		processButton.setBackground(new Color(204, 0, 51));
		processButton.setBorder(new EmptyBorder(7, 20, 7, 20));
		processButton.addActionListener(this);

		saveButton = new JButton("Save");
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

		blurButton = new JButton("Blur");
		blurButton.setBorder(new EmptyBorder(7, 32, 7, 32));
		blurButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blurButton.setForeground(Color.WHITE);
		blurButton.setBackground(new Color(255, 153, 0));
		blurButton.setOpaque(true);
		blurButton.addActionListener(this);

		rdbtnManhattan = new JRadioButton("Manhattan");

		rdbtnEuclidean = new JRadioButton("Euclidean");
		rdbtnEuclidean.setSelected(true);

		rdbtnChebyshov = new JRadioButton("Chebyshov");

		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnChebyshov);
		buttonGroup.add(rdbtnEuclidean);
		buttonGroup.add(rdbtnManhattan);

		desaturateButton = new JButton("Desaturate");
		desaturateButton.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		dTransformButton.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
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

		chooseFileButton = new JButton("Choose file...");
		chooseFileButton.addActionListener(this);
		jFileChooser = new JFileChooser();

		randomButton = new JButton("Random");
		randomButton.addActionListener(this);

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
																				rdbtnChebyshov)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				chooseFileButton)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				saveButton,
																				GroupLayout.PREFERRED_SIZE,
																				93,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				randomButton))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								originalJLabel,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addGroup(
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
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								editedJLabel,
																								GroupLayout.PREFERRED_SIZE,
																								401,
																								GroupLayout.PREFERRED_SIZE))))
										.addGap(14)));
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
																processButton))
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
																rdbtnChebyshov)
														.addComponent(
																chooseFileButton)
														.addComponent(
																saveButton)
														.addComponent(
																randomButton))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																editedJLabel,
																GroupLayout.DEFAULT_SIZE,
																395,
																Short.MAX_VALUE)
														.addComponent(
																originalJLabel,
																GroupLayout.DEFAULT_SIZE,
																395,
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
			Distance d = null;
			if (rdbtnChebyshov.isSelected()) {
				d = new Chebyshov();
			} else if (rdbtnEuclidean.isSelected()) {
				d = new Euclidean();
			} else {
				d = new Manhattan();
			}

			System.out.println(d.getClass().getSimpleName());
			DistanceTransform.doTransform(editedImage, d, this);

		} else if (e.getSource() == desaturateButton) {
			toGrayscale();
		} else if (e.getSource() == invertButton) {
			ImageUtils.invert(editedImage);
		} else if (e.getSource() == dTransformButton) {
			DistanceTransform.justDistanceTransform(editedImage, currentMatrix,
					new Euclidean(), this);
		} else if (e.getSource() == erodeButton) {
			DistanceTransform.justErode(editedImage, currentMatrix,
					new Euclidean(), this);
		} else if (e.getSource() == saveButton) {
			int result = jFileChooser.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File imageFile = jFileChooser.getSelectedFile();
				saveImage(imageFile);
			}
		} else if (e.getSource() == binarizeButton) {
			binarize();
		} else if (e.getSource() == blurButton) {
			Filters.blurImage(
					new BufferedImage(editedImage.getColorModel(), editedImage
							.copyData(null),
							editedImage.isAlphaPremultiplied(), null),
					editedImage);
		} else if (e.getSource() == randomButton) {
			BufferedImage newImage = ImageUtils.generateRandom();
			ColorModel cm = newImage.getColorModel();
			boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
			WritableRaster raster = newImage.copyData(null);
			editedImage = new BufferedImage(cm, raster, isAlphaPremultiplied,
					null);
			ColorModel cm1 = newImage.getColorModel();
			boolean isAlphaPremultiplied1 = cm.isAlphaPremultiplied();
			WritableRaster raster1 = newImage.copyData(null);
			originalImage = new BufferedImage(cm1, raster1, isAlphaPremultiplied1,
					null);
			editedImageIcon = new ImageIcon(editedImage);
			originalImageIcon = new ImageIcon(originalImage);
			editedJLabel.setIcon(editedImageIcon);
			originalJLabel.setIcon(originalImageIcon);
			
			currentMatrix = new int[editedImage.getWidth()][editedImage
			                        						.getHeight()];
			
		} else if (e.getSource() == chooseFileButton) {
			int result = jFileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File imageFile = jFileChooser.getSelectedFile();
				try {
					originalImage = ImageIO.read(imageFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				originalImageIcon = new ImageIcon(originalImage);
				originalJLabel.setIcon(originalImageIcon);
				
				try {
					editedImage = ImageIO.read(imageFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				editedImageIcon = new ImageIcon(editedImage);
				editedJLabel.setIcon(editedImageIcon);

				currentMatrix = new int[editedImage.getWidth()][editedImage
						.getHeight()];
			}
		}
		repaint();
	}

	private void binarize() {
		Binarizator binarizator = new Binarizator(editedImage);
		binarizator.binarize();
	}

	private void saveImage(File imageFile) {
		// File outputFile = new File("IRSkeletonization_"
		// + System.currentTimeMillis() + ".jpg");
		try {
			ImageIO.write(editedImage, "jpg", imageFile);
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
