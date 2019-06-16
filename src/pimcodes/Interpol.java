package pimcodes;


import pimcodes.ImageAccess;

/**
 *
 * @author carlos
 */
public class Interpol {
    
    public static ImageAccess run(ImageAccess img){
        // img = resize(img,"NN",400,400); 
        img = resize(img,400,400); 
        return img;
    }
    
    private static double getInterpolatedPixelNearestNeighbor(ImageAccess image,double x, double y) {
	// double arr[][] = new double[2][2];
	int i = (int)Math.round(x);
	int j = (int)Math.round(y);
        double v = image.getPixel(i,j);

	return v;
    }
    
    private static double getInterpolatedPixelLinear(ImageAccess image, double x, double y) {
        double arr[][] = new double[2][2];
        int i = (int)Math.floor(x);
        int j = (int)Math.floor(y);
        image.getNeighborhood(i, j, arr);
        double v = getSampleLinearSpline(x-i, y-j, arr);
        return v;
    }
    
    static private double getSampleLinearSpline(double x, double y, double neighbor[][]) {
        double xw[] = getLinearSpline(x);
        double yw[] = getLinearSpline(y);
        double sum = 0.0;
        for (int j=0; j<2; j++) {
                for (int i=0; i<2; i++) {
                        sum = sum + neighbor[i][j] * yw[j] * xw[i];
                }
        }
        return sum;
    }
    /**
	* Computes the linear spline basis function at a position t.
	*
	* @param	t argument between 0 and 1.
	* @return	2 sampled values of the linear B-spline (B1[t], B1[t-1]).
	*/
	static private double[] getLinearSpline(double t) {
            double v[] = new double[2];

            if (t < 0.0 || t > 1.0) {
                    throw new ArrayStoreException(
                            "Argument t for linear B-spline outside of expected range."); 
            }

            v[0] = 1.0 - t;
            v[1] = t;
            return v;
        }
    
  
    
    static public ImageAccess resize(ImageAccess input, int mx, int my){
        
        ImageAccess out = new ImageAccess(mx,my);
        int nx = input.getHeight();
        int ny = input.getWidth();
        int centerx = nx/2;
        int centery = ny/2;
        double scalex = (double)nx/(double)mx;
        double scaley = (double)ny/(double)my;
        double value = 0.0;
        
        for(int xo=0;xo<mx;xo++)
            for(int yo=0;yo<mx;yo++){
                
                double dx = xo-mx/2; //dist(xo, centerX of new image)
		double dy = yo-my/2;
		double ox = centerx + dx*scalex; //getting dx position relative to the original image
		double oy = centery + dy*scaley;
                
                value = getInterpolatedPixelNearestNeighbor(input, ox, oy);
                
                out.putPixel(xo, yo, value);
            }
        
        return out;
    }
}
