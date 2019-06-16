package pimcodes;

import ij.ImagePlus;
import ij.io.Opener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PIMcodes {
    
    public static void main(String[] args) {
        
        ImagePlus oImg;
        ImageAccess img;
        
        oImg = new Opener().openImage(""); // a .tif image path here
        img = new ImageAccess(oImg.getProcessor());
        
        img.show("original");
        // results viewing only
        // run the test you want here using CLASSNAME.run(img);
        // edit the run() method in a class you want to test
        img = Interpol.run(img);      
        // --------------------------
        
        img.show("result");
    }
    
   
    
}
