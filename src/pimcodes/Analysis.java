package pimcodes;

import java.util.Arrays;

/*
 * @author carlos
 */
public class Analysis {

    static ImageAccess run(ImageAccess img) {
            xGravityCenter(img);
        return img;
    }

    private static double[] area(ImageAccess input) {
        //returns the area of objects in a binary image
        int nx = input.getWidth();
        var ny = input.getHeight();
        int n = (int) input.getMaximum();
        double[] area = new double[n];
        
        for (int i = 0; i < nx; ++i) 
            for (int j = 0; j < ny; ++j) {
                int k = (int) input.getPixel(i, j);
                if (k > 0.0)
                    area[k-1]++;           
            }
        
        System.out.println("Result: \n  "+Arrays.toString(area)+"\n");
        return area;
     }
    
    public static double[] xGravityCenter(ImageAccess input) {
        int nx = input.getWidth();
        int ny = input.getHeight();
        int n = (int)input.getMaximum();
        
        double[] xgc = new double[n];
        int[] cont = new int[n];
        
        for (int i = 0; i < nx; ++i) 
        for (int j = 0; j < ny; ++j) {
                int val = (int)input.getPixel(i, j);
                if (val > 0){    
                    xgc[val - 1] = xgc[val - 1] + (double)i;    
                    cont[val - 1]++;
                }                
            }
        
        for(int i = 0; i < n; ++i){
            if (cont[i]>0)
                xgc[i] = xgc[i] / (double)cont[i];
            }
        
        System.out.println("Result: \n  "+Arrays.toString(xgc)+"\n");
        
        return xgc;
    }
    
}
