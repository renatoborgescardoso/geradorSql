package br.com.geradorsql;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

import br.com.geradorsql.arquitetura.UteisSql;
import br.com.geradorsql.arquitetura.UtilReflexao;

public class Repositorio {

	private GeradorSQL geradorSQL = new GeradorSQL();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Metodo que realiza a geração do sql de insert.
	 * 
	 * @param obj
	 * @throws Throwable
	 */
	public void insert(final Object obj) throws Throwable {
		final String sql = geradorSQL.gerarInsert(obj);

		int codigo = jdbcTemplate.query(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				Field[] fiels = geradorSQL.getFields(obj.getClass());
				int contador = 1;
				for (Field field : fiels) {					
					UteisSql.setValuePreparedStatement(field, contador, preparedStatement);
					contador++;
				}
				return preparedStatement;
			}
		}, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getInt("codigo");
				}
				return null;
			}
		});
		UtilReflexao.invocarMetodo(obj, "setCodigo", codigo);
	}
}