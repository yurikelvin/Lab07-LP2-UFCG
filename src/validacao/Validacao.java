package validacao;

public class Validacao {

	public static void validaString(String s) throws Exception{
		if(s == null) {
			throw new NullPointerException();
		}
		if(s.equals("")) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void validaString(String s, String msg) throws Exception{
		if(s == null) {
			throw new NullPointerException(msg);
		}
		if(s.equals("")) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	public static void validaInt(int i) throws Exception{
		if(i < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void validaInt(int i, String msg) throws Exception {
		if(i < 0) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void validaObj(Object obj) throws Exception {
		if(obj == null) {
			throw new NullPointerException();
		}
	}
	
	public static void validaObj(Object obj, String msg) throws Exception {
		if(obj == null) {
			throw new NullPointerException(msg);
		}
	}
}
