import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
				Color c = new Color(rgbNewValue, rgbNewValue, rgbNewValue);
				image.setRGB(i, j, c.getRGB());
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
}
