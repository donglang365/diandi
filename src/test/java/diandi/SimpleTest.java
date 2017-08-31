package diandi;

public class SimpleTest {
	public static void main(String[] args) {
		String content = "高亮";
		String str ="此处高亮啦啦啦高亮亮了亮了";
		System.out.println(str.indexOf("高亮"));
		System.out.println(content.substring(1, content.length()));
		long i = 0;
		Object s = i;
		if(s == null) {
			System.out.println(s);
		}
	}

}
