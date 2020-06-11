package br.com.geradorsql;

import java.lang.reflect.Field;

import br.com.geradorsql.anotacao.Coluna;
import br.com.geradorsql.anotacao.Tabela;

public class GeradorSQL {

	public String gerarInsert(Object obj) throws Throwable {
		Tabela tabela = obj.getClass().getAnnotation(Tabela.class);

		if (tabela != null) {
			String nomeTabela = tabela.nome();

			// Obtém os atributos da classe via reflection
			Field[] fields = getFields(obj.getClass());

			StringBuilder sql = new StringBuilder("INSERT INTO ").append(nomeTabela).append(" (");
			StringBuilder camposInterrogacao = new StringBuilder();

			int contador = 1;
			for (Field field : fields) {
				// como os atributos são private, setamos ele como visible
				field.setAccessible(true);
				Coluna coluna = field.getAnnotation(Coluna.class);

				// Se o atributo tem a anotação
				if (coluna != null) {
					// Verifica se está vazio pra usar o nome do próprio atributo
					String nomeColuna = coluna.nome().equals("") ? field.getName() : coluna.nome().toLowerCase();
					sql.append(nomeColuna);
					if (contador != fields.length) {
						sql.append(", ");
						camposInterrogacao.append("?, ");
					} else {
						camposInterrogacao.append("?");
						sql.append(") VALUES (").append(camposInterrogacao.toString()).append(");");
					}
				}
				contador++;
			}

			return sql.toString();
		}
		return "";
	}

	/**
	 * Método recursivo para obter os atributos da class e da superclasse
	 * 
	 * @param Class<?> c
	 * @return Field[]
	 */
	public Field[] getFields(Class<?> c) {
		// Se tem superclasse
		if (c.getSuperclass() != null) {
			// Chama o próprio método para pegar os
			// atributos da superclasse
			Field[] superClassFields = getFields(c.getSuperclass());
			// Pega os atributos da própria classe
			Field[] thisFields = c.getDeclaredFields();

			// array com todos os atributos
			Field[] allFields = new Field[superClassFields.length + thisFields.length];

			// Copia os atributos da superclasse
			System.arraycopy(superClassFields, 0, allFields, 0, superClassFields.length);
			// Copia os atributos da classe atual
			System.arraycopy(thisFields, 0, allFields, superClassFields.length, thisFields.length);

			return allFields;
		} else {
			return c.getDeclaredFields();
		}
	}
}
