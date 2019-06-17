package pimcodes;
public class Morfo {
    
    static public ImageAccess run(ImageAccess input){
        input = doErosion(input);
        return input;
    }
    
    static public ImageAccess doDilation(ImageAccess img) {
		int nx = img.getWidth();
		int ny = img.getHeight();
		ImageAccess out = new ImageAccess(nx, ny);
		double arr[] = new double[9];
		double max;
		
		for (int x=0; x<nx; x++) 
		for (int y=0; y<ny; y++) {
			img.getPattern(x, y, arr, ImageAccess.PATTERN_SQUARE_3x3);
			max = arr[0];
			for (int k=1; k<9; k++) {
				if (arr[k] > max) {
					max = arr[k];
				}
			}
			out.putPixel(x, y, max);
		}
		return out;
	}

	static public ImageAccess doErosion(ImageAccess img) {
		int nx = img.getWidth();
		int ny = img.getHeight();
		ImageAccess out = new ImageAccess(nx, ny);
		double arr[] = new double[9];
		double min;
		
		for (int x=0; x<nx; x++) 
		for (int y=0; y<ny; y++) {
			img.getPattern(x, y, arr, ImageAccess.PATTERN_SQUARE_3x3);
			min = arr[0];
			for (int k=1; k<9; k++) {
				if (arr[k] < min) {
					min = arr[k];
				}
			}
			out.putPixel(x, y, min);
		}
		return out;
	}

	static public ImageAccess doOpen(ImageAccess img) {
        img = doErosion(img);
        img = doDilation(img);
        return img;
	}

	static public ImageAccess doClose(ImageAccess img) {
        img = doDilation(img);
        img = doErosion(img);
        return img;
	}
        
}
