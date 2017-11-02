package fdi.injection.test.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SharedObject implements Serializable, IsSerializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Valor="Soy yo ";
	String Valor2= " la que sigue aqui";
	
	public SharedObject() {
		// TODO Auto-generated constructor stub
	}
	
	public String getValor() {
		return Valor;
	}
	
	public String getValor2() {
		return Valor2;
	}
	
	public void setValor(String valor) {
		Valor = valor;
	}
	
	public void setValor2(String valor2) {
		Valor2 = valor2;
	}
	
}
