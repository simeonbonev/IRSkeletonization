import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageUtils {
	public static int[][] getTrueBinaryImage(BufferedImage image) {
		int[][] result = new int[image.getWidth()][image.getHeight()];
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				if ((new Color(image.getRGB(i, j)).getRed() > 128)) {
					result[i][j] = 1;
				} else {
					result[i][j] = 0;
				}
			}
		}
		return result;
	}

	public static void invert(BufferedImage image) {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color c = new Color(image.getRGB(i, j));
				Color x = new Color(255 - c.getRed(), 255 - c.getGreen(),
						255 - c.getBlue());
				image.setRGB(i, j, x.getRGB());
			}
		}
	}

	public static void setImageFromMatrix(BufferedImage image, int[][] matrix,
			int width, int height) {
		int maximumIntensityInTheMatrix = getMaximumIntensity(matrix, width,
				height);
		double coefficient = 256.0 / (maximumIntensityInTheMatrix + 1);
		System.out.println("Max intensity :" + maximumIntensityInTheMatrix);
		System.out.println(coefficient);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgbNewValue = (int) Math.round(coefficient * matrix[i][j]);
				int rgb = rgbNewValue;
				rgb = (rgb << 8) + rgbNewValue;
				rgb = (rgb << 8) + rgbNewValue;
				image.setRGB(i, j, rgb);
			}
		}
	}

	private static int getMaximumIntensity(int[][] matrix, int width, int height) {
		int currentMax = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (matrix[i][j] > currentMax) {
					currentMax = matrix[i][j];
				}
			}
		}
		return currentMax > 255 ? 255 : currentMax;
	}

	public static void printMatrix(int[][] matrix, int width, int height) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(matrix[j][i]);
			}
			System.out.println();
		}
	}

	public static BufferedImage generateRandom() {
		BufferedImage result = new BufferedImage(150, 150,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 150; i++) {
			for (int j = 0; j < 150; j++) {
				result.setRGB(i, j, Color.WHITE.getRGB());
			}
		}
		Graphics2D g2d = result.createGraphics();
		String[] stringArray = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
				"v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "0" };
		Color[] colorArray = { Color.red, Color.blue, Color.green, Color.gray,
				Color.cyan, Color.pink, Color.magenta, Color.orange,
				Color.yellow };
		Random rand = new Random();
		int chosenColor = rand.nextInt(colorArray.length - 1);
		int chosenChar = rand.nextInt(stringArray.length - 1);
		g2d.setPaint(colorArray[chosenColor]);
		g2d.setFont(new Font("Helvetica", Font.BOLD, 100));
		String s = stringArray[chosenChar];
		FontMetrics fm = g2d.getFontMetrics();
		int x = result.getWidth() - fm.stringWidth(s) - 35;
		int y = fm.getHeight() + 5;
		g2d.drawString(s, x, y);
		g2d.dispose();

		return result;
	}
}
