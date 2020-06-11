package br.com.geradorsql.arquitetura;

import java.lang.reflect.Method;

public class UtilReflexao {

	public static Object invocarMetodo(Object objetoDoMetodo, String nomeDoMetodo, Object... parametro) {
		try {
			Method metodo = objetoDoMetodo.getClass().getMethod(nomeDoMetodo, getClassesDosParametros(parametro));
			return metodo.invoke(objetoDoMetodo, parametro);
		} catch (Exception e) {
			return null;
		}
	}

	public static Class<?>[] getClassesDosParametros(Object... parametrosObj) {
		Class<?>[] parametros = null;
		if (parametrosObj != null) {
			parametros = new Class[parametrosObj.length];
			for (int i = 0; i < parametrosObj.length; i++) {
				parametros[i] = parametrosObj[i].getClass();
			}
		}
		return parametros;
	}
}
