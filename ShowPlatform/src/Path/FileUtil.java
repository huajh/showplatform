package Path;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

public class FileUtil {
	
	/**
	 * // Ŀ¼�����ڵ�����£�����Ŀ¼��return true,
	 *   ���� false
	 * @param url
	 * @return
	 */
	static public boolean MkDirs(String url)
	{
        File fp = new File(url);		        
        // ����Ŀ¼  
        if (!fp.exists()) {		        	
            fp.mkdirs();// Ŀ¼�����ڵ�����£�����Ŀ¼��
            return true;
        }
        return false;
	}

    /* 
     * ��������ļ��� 
     */  
    static public String getRandomFilename(){
    	
        String RandomFilename = "";  
        Random rand = new Random();//���������  
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
