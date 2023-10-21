import java.util.ArrayList;
import java.util.List;
import tiposPack.Gasto;

public class Analise {
    private List<Gasto> listaDeGastos;
    private double faturamento;
    private String titulo;
    private double diferenca;
    public double totalAtivo;
    public double totalPassivo;
    
    
    public Analise(double faturamento, String titulo) {
        this.titulo = titulo;
        this.faturamento = faturamento;
        listaDeGastos = new ArrayList<>();

    }
    void registrarGasto() {

    }

    void excluirGasto() {

    }

    void calcularAtivo() {

    }

    void calcularPassivo() {

    }

    void calcularPatLiq() {

    }
    double getDiferenca(){
        return this.diferenca;
    }

    String getTitulo(){
        return this.titulo;
    }

    double getFaturamento() {
        return this.faturamento;
    }

    List<Gasto> getListaDeGastos() {
        return listaDeGastos;
    }
}
