package validacao;

import exception.ValidacaoException;

public class Validacao {

	public static void validaString(String s) throws ValidacaoException{
		if(s == null) {
			throw new NullPointerException();
		}
		if(s.equals("")) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void validaString(String s, String msg) throws ValidacaoException{
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
	
	public static void validaInt(int i, String msg) throws ValidacaoException {
		if(i < 0) {
			throw new IllegalArgumentException(msg);
		}
	}

	public static void validaObj(Object obj) throws ValidacaoException {
		if(obj == null) {
			throw new NullPointerException();
		}
	}
	
	public static void validaObj(Object obj, String msg) throws ValidacaoException {
		if(obj == null) {
			throw new NullPointerException(msg);
		}
	}
}
