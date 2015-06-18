import java.awt.Color;
import java.awt.image.BufferedImage;

public class Binarizator {
	private BufferedImage image;
	private int total;
	private int[] histogram = new int[256];

	public Binarizator(BufferedImage image) {
		this.image = image;
		this.total = image.getHeight() * image.getWidth();
		for (int i = 0; i < this.image.getWidth(); i++) {
			for (int j = 0; j < this.image.getHeight(); j++) {
				Color currentPixelColor = new Color(image.getRGB(i, j));
				int newValue = (int) Math.round((currentPixelColor.getRed()
						+ currentPixelColor.getGreen() + currentPixelColor
						.getBlue()) / 3.0);
				histogram[newValue]++;
			}
		}
	}

	public double otsu() {
		double sum = 0;
		for (int i = 1; i < 256; i++) {
			sum += i * histogram[i];
		}

		double sumBackground = 0;
		double probBackground = 0;
		double probForeground = 0;

		double meanBackground = 0;
		double meanForeground = 0;

		double maxVariance = 0;

		double currentVariance = 0;
		double threshold1 = 0;
		double threshold2 = 0;

		for (int i = 0; i < 256; i++) {
			probBackground += histogram[i];
			if (probBackground == 0) {
				continue;
			}
			probForeground = total - probBackground;
			if (probForeground == 0) {
				break;
			}

			sumBackground += i * histogram[i];
			meanBackground = sumBackground / probBackground;
			meanForeground = (sum - sumBackground) / probForeground;

			currentVariance = probBackground * probForeground
					* (meanBackground - meanForeground)
					* (meanBackground - meanForeground);

			if (currentVariance >= maxVariance) {
				threshold1 = i;
				if (currentVariance > maxVariance) {
					threshold2 = i;
				}
				maxVariance = currentVariance;
			}
		}

		return (threshold1 + threshold2) / 2.0;
	}

	public void binarize() {
		double threshold = otsu();
		
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color currentPixelColor = new Color(image.getRGB(i, j));
				int currentIntensity = (int) Math.round((currentPixelColor.getRed()
						+ currentPixelColor.getGreen() + currentPixelColor
						.getBlue()) / 3.0);
				int newIntensity = currentIntensity > threshold ? 255 : 0;
				image.setRGB(i, j, (new Color(newIntensity, newIntensity, newIntensity)).getRGB());
			}
		}

	}
}
