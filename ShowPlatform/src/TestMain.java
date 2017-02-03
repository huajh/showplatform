import java.text.SimpleDateFormat;
import java.util.Date;

import JavaBeans.Components.News;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	String name = FileUtil.getRandomFilename();
	//	System.out.println(name);
		Date date = new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println(df.format(date));
		
//		News news = new News(1);
//		System.out.println(news.getContestID());
//		news.visited();
//		news.load();
//		System.out.println(news.getContestID());		
	//	news.Save();
	

	}

}
