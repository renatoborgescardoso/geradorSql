package br.com.renato.geradorsql;

import java.time.LocalDateTime;

import br.com.geradorsql.Repositorio;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
    	try {
    		Repositorio repositorio = new Repositorio();
	    	Pessoa obj = new Pessoa("Nelson", "Rua tal", 28, LocalDateTime.now());
	    	repositorio.insert(obj);
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }
}
