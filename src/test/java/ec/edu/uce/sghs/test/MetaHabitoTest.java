package ec.edu.uce.sghs.test;

import ec.edu.uce.sghs.dominio.MetaHabito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MetaHabitoTest {

    private MetaHabito meta;
    private List<MetaHabito> metas;

    @BeforeEach
    void preparar() {
        meta = new MetaHabito(1, 10, 3, 5);
        metas = new ArrayList<>();
    }

    @Test
    void crearConConstructorCompleto() {
        assertNotNull(meta);
        assertEquals(1, meta.getIdMeta());
        assertEquals(10, meta.getIdHabito());
        assertEquals(3, meta.getVecesPorSemana());
        assertEquals(5, meta.getDiasProgramados());
        assertEquals(0, meta.getDiasCumplidos());
        assertFalse(meta.isCumplida());
        assertEquals("no cumplida", meta.getEstado());
    }

    @Test
    void completarConstructorVacioConSetters() {
        MetaHabito nueva = new MetaHabito();

        nueva.setIdMeta(2);
        nueva.setIdHabito(20);
        nueva.setDiasProgramados(4);
        nueva.setVecesPorSemana(2);
        nueva.setDiasCumplidos(1);

        assertEquals(2, nueva.getIdMeta());
        assertEquals(20, nueva.getIdHabito());
        assertEquals(4, nueva.getDiasProgramados());
        assertEquals(2, nueva.getVecesPorSemana());
        assertEquals(1, nueva.getDiasCumplidos());
    }

    @Test
    void completarConstructorDeDosParametros() {
        MetaHabito nueva = new MetaHabito(2, 3);

        nueva.setIdHabito(20);
        nueva.setDiasProgramados(5);
        nueva.guardar(metas);

        assertEquals(1, metas.size());
        assertEquals(3, metas.get(0).getVecesPorSemana());
    }

    @Test
    void aceptarLimitesDeFrecuencia() {
        MetaHabito minima = new MetaHabito(1, 10, 1, 1);
        MetaHabito maxima = new MetaHabito(2, 20, 7, 7);

        assertEquals(1, minima.getVecesPorSemana());
        assertEquals(7, maxima.getVecesPorSemana());
    }

    @Test
    void rechazarIdentificadoresInvalidos() {
        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setIdMeta(0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setIdHabito(-1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setIdHabito(null)
        );
    }

    @Test
    void rechazarFrecuenciaVaciaOFueraDelRango() {
        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setVecesPorSemana(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setVecesPorSemana(0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setVecesPorSemana(8)
        );

        assertEquals(3, meta.getVecesPorSemana());
    }

    @Test
    void rechazarFrecuenciaMayorQueDiasProgramados() {
        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setVecesPorSemana(6)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new MetaHabito(1, 10, 4, 3)
        );
    }

    @Test
    void rechazarDiasProgramadosInvalidos() {
        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setDiasProgramados(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setDiasProgramados(0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setDiasProgramados(8)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setDiasProgramados(2)
        );

        assertEquals(5, meta.getDiasProgramados());
    }

    @Test
    void calcularEstadoDeLaMeta() {
        meta.actualizarCumplimiento(2);
        assertFalse(meta.isCumplida());

        meta.actualizarCumplimiento(3);
        assertTrue(meta.isCumplida());
        assertEquals("cumplida", meta.getEstado());

        meta.setDiasCumplidos(5);
        assertTrue(meta.isCumplida());

        meta.setDiasCumplidos(1);
        assertFalse(meta.isCumplida());
    }

    @Test
    void rechazarDiasCumplidosInvalidos() {
        assertThrows(
                IllegalArgumentException.class,
                () -> meta.setDiasCumplidos(-1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> meta.actualizarCumplimiento(6)
        );

        assertEquals(0, meta.getDiasCumplidos());
    }

    @Test
    void guardarMeta() {
        meta.guardar(metas);

        assertFalse(metas.isEmpty());
        assertEquals(1, metas.size());
        assertEquals(1, metas.get(0).getIdMeta());
        assertEquals(10, metas.get(0).getIdHabito());
    }

    @Test
    void guardarCopiaIndependiente() {
        meta.guardar(metas);
        meta.setVecesPorSemana(4);

        assertEquals(3, metas.get(0).getVecesPorSemana());
        assertEquals(4, meta.getVecesPorSemana());
    }

    @Test
    void rechazarMetaSinHabitoSeleccionado() {
        MetaHabito incompleta = new MetaHabito(2, 3);
        incompleta.setDiasProgramados(5);

        assertThrows(
                IllegalArgumentException.class,
                () -> incompleta.guardar(metas)
        );

        assertTrue(metas.isEmpty());
    }

    @Test
    void rechazarMetaIncompleta() {
        MetaHabito incompleta = new MetaHabito();

        assertThrows(
                IllegalArgumentException.class,
                () -> incompleta.guardar(metas)
        );

        assertTrue(metas.isEmpty());
    }

    @Test
    void rechazarIdentificadorRepetido() {
        meta.guardar(metas);
        MetaHabito repetida = new MetaHabito(1, 20, 2, 4);

        assertThrows(
                IllegalArgumentException.class,
                () -> repetida.guardar(metas)
        );

        assertEquals(1, metas.size());
    }

    @Test
    void rechazarSegundaMetaParaElMismoHabito() {
        meta.guardar(metas);
        MetaHabito repetida = new MetaHabito(2, 10, 2, 5);

        assertThrows(
                IllegalArgumentException.class,
                () -> repetida.guardar(metas)
        );

        assertEquals(1, metas.size());
    }

    @Test
    void rechazarListaNulaOConElementosNulos() {
        assertThrows(
                IllegalArgumentException.class,
                () -> meta.guardar(null)
        );

        metas.add(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> meta.guardar(metas)
        );

        assertEquals(1, metas.size());
    }
}
