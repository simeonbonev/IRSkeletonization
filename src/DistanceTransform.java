import java.awt.image.BufferedImage;

public class DistanceTransform {

	public static void doTransform(BufferedImage image, Distance distance, Runner runner) {
		int[][] matrix = ImageUtils.getTrueBinaryImage(image);
		performDistanceTransform(matrix, image.getWidth(), image.getHeight(),
				distance, runner);
		ImageUtils.setImageFromMatrix(image, matrix, image.getWidth(), image.getHeight());
		performErosion(matrix, image.getWidth(), image.getHeight(), distance, runner);
		ImageUtils.setImageFromMatrix(image, matrix, image.getWidth(), image.getHeight());
	}

	private static void performErosion(int[][] matrix, int width, int height, Distance distance, Runner runner) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (matrix[x][y] > 0) {
					for (int i = 0; i < width; i++) {
						for (int j = 0; j < height; j++) {
							int deltaRadius = matrix[x][y] - matrix[i][j];
							int dist = (int) distance.getDistance(x, y, i, j);
							if (deltaRadius >= dist && x!=i && y!=j) {
								matrix[i][j] = 0;
							}
						}
					}
				}
			}
		}
		System.out.println("Done with Erosion.");
	}

	private static void performDistanceTransform(int[][] matrix, int w, int h,
			Distance distance, Runner runner) {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (matrix[i][j] > 0) {
					// Foreground pixel
					matrix[i][j] = getClosestBackgroundPixel(i, j, matrix, w,
							h, distance);
				}
			}
		}
		System.out.println("Done with Distance Transform");
	}

	private static int getClosestBackgroundPixel(int i, int j, int[][] matrix,
			int w, int h, Distance distance) {
		int minDist = Integer.MAX_VALUE;
		for (int k = 0; k < w; k++) {
			for (int l = 0; l < h; l++) {
				if ((k == i && l == j) || matrix[k][l] > 0) {
					continue;
				}
				if (matrix[k][l] == 0) {
					int currentDist = (int) Math.round(distance.getDistance(i,
							j, k, l));
					if (currentDist > 0 && currentDist < minDist) {
						minDist = currentDist;
					}
				}
			}
		}
		return minDist;
	}

	public static void justDistanceTransform(BufferedImage image, int[][] matrix,
			Distance distance, Runner runner) {
		int[][] currentMatrix = ImageUtils.getTrueBinaryImage(image);
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				matrix[i][j] = currentMatrix[i][j];
			}
		}
		performDistanceTransform(matrix, image.getWidth(), image.getHeight(),
				distance, runner);		
		ImageUtils.setImageFromMatrix(image, matrix, image.getWidth(), image.getHeight());
	}

	public static void justErode(BufferedImage image, int[][] matrix, Distance distance, Runner runner) {
		performErosion(matrix, image.getWidth(), image.getHeight(), distance, runner);
		ImageUtils.setImageFromMatrix(image, matrix, image.getWidth(), image.getHeight());		
	}
}
