package br.com.geradorsql.arquitetura;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.Objects;

public class UteisSql {

	/**
	 * Rotina de inclusao no {@link PreparedStatement} o valor do atributo em determinada
	 * posição informada no parametro index.
	 * 
	 * @param valor
	 * @param type
	 * @param index
	 * @param preparedStatement
	 * @throws SQLException
	 */
	public static <P extends Object> void setValuePreparedStatement(P valor, int index, PreparedStatement preparedStatement) throws SQLException {
		if (Objects.isNull(valor)) {
			preparedStatement.setNull(index, Types.NULL);
			return;
		}

		if (valor instanceof String) {
			preparedStatement.setString(index, (String) valor);
			return;
		}

		if (valor instanceof Integer) {
			preparedStatement.setInt(index, (Integer) valor);
			return;
		}

		if (valor instanceof Long) {
			preparedStatement.setLong(index, (Long) valor);
			return;
		}

		if (valor instanceof Float) {
			preparedStatement.setFloat(index, (Float) valor);
			return;
		}

		if (valor instanceof BigDecimal) {
			preparedStatement.setBigDecimal(index, (BigDecimal) valor);
			return;
		}

		if (valor instanceof java.sql.Date) {
			preparedStatement.setDate(index, (java.sql.Date) valor);
			return;
		}

		if (valor instanceof java.sql.Timestamp) {
			preparedStatement.setTimestamp(index, (java.sql.Timestamp) valor);
			return;
		}

		if (valor instanceof Date) {
			preparedStatement.setTimestamp(index, getDataJDBCTimestamp((Date) valor));
			return;
		}

		if (valor instanceof Boolean) {
			preparedStatement.setBoolean(index, (Boolean) valor);
			return;
		}

		if (valor instanceof Double) {
			preparedStatement.setDouble(index, (Double) valor);
			return;
		}

		if (valor instanceof Enum<?>) {
			if (((Enum<?>) valor) != null && ((Enum<?>) valor).name() != null) {
				preparedStatement.setString(index, ((Enum<?>) valor).name());
			} else {
				preparedStatement.setNull(index, Types.NULL);
			}
			return;
		}

	}

	/**
	 * Metodo que realiza a conversão da data para o formato do banco de dados.
	 * 
	 * @param dataConverter
	 * @return
	 */
	public static java.sql.Date getDataJDBC(java.util.Date dataConverter) {
		if (dataConverter == null) {
			return null;
		}
		return new java.sql.Date(dataConverter.getTime());
	}

	/**
	 * Metodo que realiza a conversão da data para o formato do banco de dados.
	 * 
	 * @param dataConverter
	 * @return
	 */
	public static java.sql.Timestamp getDataJDBCTimestamp(java.util.Date dataConverter) {
		if (dataConverter == null) {
			return null;
		}
		return new java.sql.Timestamp(dataConverter.getTime());
	}
}
