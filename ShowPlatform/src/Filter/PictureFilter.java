package Filter;

import java.io.FileFilter;
import java.io.File;

public class PictureFilter implements FileFilter{   	
	@Override  
	public boolean accept(File pathname) {		
		String filename = pathname.getName().toLowerCase();   	        
		if(filename.endsWith(".jpg")||filename.endsWith(".jpeg")||filename.endsWith(".png")||filename.endsWith(".gif")){   	          
			return true;   	         
		}else{   	           
			return false;   	        
		}   	    
	}  
 }
