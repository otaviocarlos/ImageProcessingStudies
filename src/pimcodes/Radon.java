package pimcodes;
import ij.*;

public class Radon {

    static ImageAccess run(ImageAccess img) {
        return transformRadon(img,45);
    }

    private static ImageAccess transformRadon(ImageAccess input, int nbAngles) {
        int size = input.getWidth();
        
        ImageAccess sino = new ImageAccess(nbAngles, size);
        double cos, sin;
        double center = ((double)size-1.0)/2.0;
        double radius2 = center*center;
        double stepAngle = Math.PI/(double)nbAngles;
        double mc, nc, x, y, angle, v;
        double colsino[] = new double[size];
        double mean = input.getMean();
        input.subtract(mean);
        
        double[][] array = input.getArrayPixels();
        
        for (int k=0; k<nbAngles; k++) {
            angle = (double)k * stepAngle - Math.PI/2;
            cos = Math.cos(angle);
            sin = Math.sin(angle);
            for (int m=0; m<size; m++) {
                colsino[m] = 0.0;
                for (int n=0; n<size; n++) {
                    mc = (double)m - center;
                    nc = (double)n - center;
                    if (mc*mc + nc*nc < radius2) {
                            x = center + mc * cos - nc * sin;
                            y = center + mc * sin + nc * cos;
                            v = getInterpolatedPixel2D(array, x, y);
                            colsino[m] = colsino[m] + v;
                    }
                }
            }
            sino.putColumn(k, colsino);
        }
        
        
        return sino;
    }
    
    public static double getInterpolatedPixel2D(double array[][], double x, double y)
{
	int i = floor(x);
	int j = floor(y);
	double dx = x - (double)i;
	double dy = y - (double)j;
	double v00 = array[i][j];
	double v10 = array[i+1][j];
	double v01 = array[i][j+1];
	double v11 = array[i+1][j+1];
	double v = (dx*(v11*dy-v10*(dy-1.0)) - (dx-1.0)*(v01*dy-v00*(dy-1.0)));
	return v;
}
    
private static int floor(final double d) 
{
	if (d >= 0.0) 
		return (int)d;
	else {
		final int iAdd = (int)d - 1;
		return ((int)(d - (double)iAdd) + iAdd);
	}
}
    
}
