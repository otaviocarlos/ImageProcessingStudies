package pimcodes;




public class Centroid {

    static ImageAccess run(ImageAccess img) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
	* Compute the gravity center (X).
	*/
	public static double[] xGravityCenter(ImageAccess in) {
        //double[] xpos = TeacherCode.xGravityCenter(in); // Comment this line to run your own code
        int nx = in.getWidth();
        int ny = in.getHeight();
        int max = (int)in.getMaximum();
        double[] arrayDouble = new double[max];
        
        int[] arrayInt = new int[max];
            for (int x = 0; x < nx; x++) {
                for (int y = 0; y < ny; y++){
                int p = (int)in.getPixel(x, y);
                if (p > 0){
                    arrayDouble[(p - 1)] += x;
                    arrayInt[(p - 1)] += 1;
                }
            }
        }
        for (int x = 0; x < max; x++){
            if (arrayInt[x] > 0){
                arrayDouble[x] /= arrayInt[x];
            }
        }
    return arrayDouble;
  }

	/**
	* Compute the gravity center (Y).
	*/
    public static double[] yGravityCenter(ImageAccess in) {
        //double[] ypos = TeacherCode.yGravityCenter(in); // Comment this line to run your own code	
        int nx = in.getWidth();
        int ny = in.getHeight();
        int max = (int)in.getMaximum();
        double[] arrayDouble = new double[max];
        int[] arrayInt = new int[max];
            for (int x = 0; x < nx; x++) {
                for (int y = 0; y < ny; y++){
                    int p = (int)in.getPixel(x, y);
                    if (p > 0){
                        arrayDouble[(p - 1)] += y;
                        arrayInt[(p - 1)] += 1;
                    }
                }
            }
            for (int y = 0; y < max; y++){
                if (arrayInt[y] > 0){
                     arrayDouble[y] /= arrayInt[y];
                }
            }
    return arrayDouble;
  }

	/**
	* Compute perimeter of objects.
	*/
    public static double[] perimeter(ImageAccess in) {
		//double[] peri = TeacherCode.perimeter(in); // Comment this line to run your own code
    	int nx = in.getWidth();
    	int ny = in.getHeight();
    	int max = (int)in.getMaximum();
    	double[] arrayDouble = new double[max];
    	for (int x = 0; x < nx; x++) {
            for (int y = 0; y < ny; y++){
                int p = (int)in.getPixel(x, y);
                if ((p > 0) && ((in.getPixel(x - 1, y) == 0.0D) || (in.getPixel(x + 1, y) == 0.0D) || (in.getPixel(x, y - 1) == 0.0D) || (in.getPixel(x, y + 1) == 0.0D))){
                    arrayDouble[(p - 1)] += 1.0D;
                }
            }
    	}
    return arrayDouble;
  }
    
}
