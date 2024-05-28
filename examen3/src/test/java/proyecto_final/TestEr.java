package proyecto_final;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.hibernate.gui.App;

class TestEr {

private static final String MOVIL_REGEX = "^[67]\\d{8}$";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Configuración inicial antes de todas las pruebas");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Pruebas finalizadas");
	}

	@BeforeEach
	void setUp() throws Exception {
		 System.out.println("Preparando la Prueba Actual");
	}
	
	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Realizando Pruaba Actual");
	}

	@Test
    void testComprobarExpReg_CasoExitoso() {
        String palabra = "612456789";
        boolean resultado = App.comprobarExpReg(palabra, MOVIL_REGEX);
        assertTrue(resultado);
    }

    @Test
    void testComprobarExpReg_CasoFallido() {
        String palabra = " 95175315414";
        boolean resultado = App.comprobarExpReg(palabra, MOVIL_REGEX);
        assertFalse(resultado);
    }
}
