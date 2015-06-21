import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Filters {
	public static void blurImage(BufferedImage src, BufferedImage destination) {
		float[] matrix = { 0.111f, 0.111f, 0.111f, 0.111f, 0.111f, 0.111f,
				0.111f, 0.111f, 0.111f, };
		Kernel kernel = new Kernel(3, 3, matrix);
		BufferedImageOp bufferedImageOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		bufferedImageOp.filter(src, destination);
	}
}
