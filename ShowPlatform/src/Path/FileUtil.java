package Path;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

public class FileUtil {
	
	/**
	 * // 目录不存在的情况下，创建目录，return true,
	 *   否则 false
	 * @param url
	 * @return
	 */
	static public boolean MkDirs(String url)
	{
        File fp = new File(url);		        
        // 创建目录  
        if (!fp.exists()) {		        	
            fp.mkdirs();// 目录不存在的情况下，创建目录。
            return true;
        }
        return false;
	}

    /* 
     * 生成随机文件名 
     */  
    static public String getRandomFilename(){
    	
        String RandomFilename = "";  
        Random rand = new Random();//生成随机数  
        int random = rand.nextInt();  
          
        Calendar calCurrent = Calendar.getInstance();  
        int intDay = calCurrent.get(Calendar.DATE);  
        int intMonth = calCurrent.get(Calendar.MONTH) + 1;  
        int intYear = calCurrent.get(Calendar.YEAR);  
        String now = String.valueOf(intYear) + "_" + String.valueOf(intMonth) + "_" +  
            String.valueOf(intDay) + "_";  
          
        RandomFilename = now + String.valueOf(random > 0 ? random : ( -1) * random);  
          
        return RandomFilename;  
    }  
    
    /**
     * 
     * 
     * @param src
     * @param dest
     */
    
	static public void MoveFile(String src ,String dest)
	{
		File from = new File(src);
		if(!from.exists())
		{
			return;
		}
		FileUtil.MkDirs(dest);		
		File[] files = from.listFiles();			
		for(int i= 0;i<files.length;i++)
		{				
			File to = new File(dest+files[i].getName());			
			files[i].renameTo(to);			
		}	
		
	}
}
