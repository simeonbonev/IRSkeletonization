import java.awt.image.BufferedImage;
import java.util.Arrays;


public class MAT {
	private static final int RADIUS_START = 1;

	public static int[][] getMedialAxis(final int[][] image) {
		int radius = RADIUS_START;

		final int[][] ballMap = new int[image.length][image[0].length];
		for (int i = 0; i < ballMap.length; ++i) {
			ballMap[i] = Arrays.copyOf(image[i], image[i].length);
		}

		boolean canInscribeBall = true;
		Distance dist = new Chebyshov();
		while (canInscribeBall) {
			canInscribeBall = false;
			for (int y = 1; y < image.length - 1; ++y) {
				for (int x = 1; x < image[y].length - 1; ++x) {
					if (canInscribeBall(image, x, y, radius)) {
						canInscribeBall = true;
						ballMap[y][x] = radius;
						for (int i = 1; i < ballMap.length - 1; ++i) {
							for (int j = 1; j < ballMap[i].length - 1; ++j) {
								final int radiusDelta = ballMap[y][x]
										- ballMap[i][j];
								final int distance = (int) dist.getDistance(x, y,
										j, i);
								if (radiusDelta >= distance && i != y && j != x) {
									ballMap[i][j] = 0;
								}
							}
						}
					}
				}
			}
			++radius;
		}
		return ballMap;
	}
	
	private static boolean canInscribeBall(int[][] image, int x, int y, int radius) {
		int startX = x - radius + 1;
		int endX = x + radius - 1;
		int startY = y - radius + 1;
		int endY = y + radius - 1;
		for (int i = startY; i <= endY; ++i) {
			for (int j = startX; j <= endX; ++j) {
				if (i < 0 || j < 0 || i >= image.length || j >= image[i].length
						|| image[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static void doTransform(BufferedImage image) {
		int[][] matrix = ImageUtils.getTrueBinaryImage(image);
		int[][] result = getMedialAxis(matrix);
		ImageUtils.printMatrix(result, image.getWidth(), image.getHeight());
		ImageUtils.setImageFromMatrix(image, result, image.getWidth(), image.getHeight());
	}


}
