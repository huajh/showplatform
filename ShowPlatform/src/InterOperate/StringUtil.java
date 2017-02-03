package InterOperate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import sun.security.krb5.internal.tools.Ktab;


public class StringUtil {
	
	public static boolean validateNull(String string)
	{
		if(string == null||string.length()==0)
		{
			return true;
		}else {
			return false;
		}
	}
	
	public static String changeNull(String source,String target)
	{
		if(source == null ||source.length()==0
				||source.equalsIgnoreCase("null"))
		{
			return target;
		}
		else {
			return source;
		}
	}
	
	public static String filterHtml(String input)
	{
		if(input == null)
		{
			return null;
		}
		if(input.length()==0)
		{
			return input;
		}
		input = input.replaceAll("&", "&amp;");
		input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");
		input = input.replaceAll(" ", "&nbsp;");
		input = input.replaceAll("'", "&#39;");
		input = input.replaceAll("\"", "&quot;");
		return input.replaceAll("\n", "<br>");
		
	}
	
    public static boolean isEmail(String email){    
        // 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线
        // 2、(\\w+\\.)表示域名. 因为新浪邮箱域名是sina.com.cn
        //       所以后面{1,3}表示可以出现一次或两次或者三次.
        String regular = "\\w+@(\\w+\\.){1,3}\\w+";
        Pattern pattern = Pattern.compile(regular);
        boolean flag = false;
        if( email != null ){       
            Matcher matcher = pattern.matcher(email);
            flag = matcher.matches();      
        }     
        return flag;
     } 
    
    public static String toUTF8String(String s)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	for(int i=0;i<s.length();i++)
    	{
    		char c= s.charAt(i);
    		if(c>=0 && c<=255)
    		{
    			stringBuffer.append(c);
    		}else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
					b = new byte[0];
				}
				for(int j=0;j<b.length;j++)
				{
					int k = b[j];
					if(k<0)
					{
						k+=256;
					}
					stringBuffer.append("%"+Integer.toHexString(k).toUpperCase());					
				}
			}
    	}
    	 return stringBuffer.toString();
    }
   
}





















