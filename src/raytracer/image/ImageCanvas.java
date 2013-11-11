package raytracer.image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public final class ImageCanvas extends Canvas {
	private Dimension size;
	private BufferedImage image;

	public ImageCanvas(Dimension size) {
		this.size = size;
		image = new BufferedImage((int)size.getWidth(), (int)size.getHeight(),
				BufferedImage.TYPE_INT_RGB);
	}
	
	public ImageCanvas(int width, int height) {
		this(new Dimension(width, height));
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public void setSize(Dimension size) {
		this.size = size;
		image = new BufferedImage((int)size.getWidth(), (int)size.getHeight(),
				BufferedImage.TYPE_INT_RGB);
	}

	public void paint(final Graphics g) {
		super.paint(g);
		final WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		Object dataRed = colorModel.getDataElements(Color.red.getRGB(), null);
		Object dataBlack = colorModel.getDataElements(Color.black.getRGB(),
				null);
		for (int x = 0; x < size.width; x++) {
			for (int y = 0; y < size.height; y++) {
				raster.setDataElements(x, y, dataBlack);
			}
		}
		int x = 0;
		for (int y = 0; y < size.height; y++) {
			raster.setDataElements(x, y, dataRed);
			x++;
			if (x >= size.width) {
				break;
			}
		}
		g.drawImage(image, 0, 0, null);
	}
}