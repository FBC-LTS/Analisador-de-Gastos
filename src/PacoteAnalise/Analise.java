package PacoteAnalise;
import java.lang.reflect.Array;

public class Analise {
    private Array listaDeGastos[];
    private double faturamento;
    private String titulo;
    public double ativo;
    public double passivo;
    private double totalAtivo;
    private double totalPassivo;
    private double diferenca;
    private double patLiq;
    
    public void registrarGasto() {

    }

    public void excluirGasto() {

    }

    public double getTotalAtivo() {
        return this.totalAtivo;
    }
    public double getTotalPassivo() {
        return this.totalPassivo;
    }
    public double calcularAtivo(double outroAtivo) {
        return this.totalAtivo;
    }

    public void calcularPassivo() {

    }

    public void calcularPatLiq(double totalPassivo, double totalAtivo) {
        this.patLiq =- this.totalAtivo - this.totalPassivo;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public double getFaturamento() {
        return this.faturamento;
    }

    public void getListaDeGastos() {

    }
}
