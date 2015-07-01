import java.awt.image.BufferedImage;

public class DistanceTransform {

	public static void doTransform(BufferedImage image, Distance distance) {
		int[][] matrix = ImageUtils.getTrueBinaryImage(image);
		performDistanceTransform(matrix, image.getWidth(), image.getHeight(),
				distance);
		ImageUtils.printMatrix(matrix, image.getWidth(), image.getHeight());
		ImageUtils.setImageFromMatrix(image, matrix, image.getWidth(), image.getHeight());
	}

	private static void performDistanceTransform(int[][] matrix, int w, int h,
			Distance distance) {
		int counter = 0;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (matrix[i][j] < 1) {
					// Background pixel
					continue;
				} else {
					matrix[i][j] = getClosestBackgroundPixel(i, j, matrix, w,
							h, distance);
					System.out.println(++counter);
				}
			}
		}
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
}
