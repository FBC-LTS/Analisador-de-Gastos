import java.util.ArrayList;
import java.util.List;
import tiposPack.Gasto;


public class Analise {
    private List<Gasto> listaDeGastos;
    private double faturamento;
    private String titulo;
    public double totalAtivo;
    public double totalPassivo;
    public double patLiq;
    private int currentId;
    
    public Analise(double faturamento, String titulo) {
        this.titulo = titulo;
        this.faturamento = faturamento;
        listaDeGastos = new ArrayList<>();

    }

    void registrarGasto(String nome, double valor, int tipo, int codCategoria, int subCategoria) {
        this.currentId = this.currentId+1;
        Gasto novoGasto = new Gasto(nome, this.currentId, valor, tipo, codCategoria, subCategoria);
        listaDeGastos.add(novoGasto);
    }
    void excluirGasto(int id) {
        // método para encontrar e remover uma instância da gasto dentro da listaDeGastos utilizando a variável "id" da classe Gasto
        Gasto gastoToRemove = null;
        for (Gasto gasto : listaDeGastos) {
            if (gasto.getId()==(this.currentId)) {
                gastoToRemove = gasto;
                break;
            }
        }
        if (gastoToRemove != null) {
            listaDeGastos.remove(gastoToRemove);
        }
    }

    void calcularAtivo() {
        totalAtivo = 0.0; // Inicializa o totalAtivo com zero

        for (Gasto gasto : listaDeGastos) {
            // Chama o método getTipo para obter o tipo de cada gasto
            int tipoDoGasto = gasto.getTipo();

            // Faça o que for necessário com o tipo do gasto
            // Por exemplo, você pode somar os gastos ativos
            if (tipoDoGasto == 1) { // 1 representa gasto ativo
                totalAtivo += gasto.valor;
            }
        }
    }

    void calcularPassivo() {
        totalPassivo = 0.0; // Inicializa o totalPassivo com zero

        for (Gasto gasto : listaDeGastos) {
            // Chama o método getTipo para obter o tipo de cada gasto
            int tipoDoGasto = gasto.getTipo();

            // Faça o que for necessário com o tipo do gasto
            // Por exemplo, você pode somar os gastos passivos
            if (tipoDoGasto == 2) { // 2 representa gasto passivo
                totalPassivo += gasto.valor;
            }
        }
    }

    public void calcularPatLiq() {
        patLiq = totalAtivo - totalAtivo;
    }
    // Getters e Setters 
    public String getTitulo(){
        return this.titulo;
    }
    public double getFaturamento() {
        return this.faturamento;
    }
    public List<Gasto> getListaDeGastos() {
        return listaDeGastos;
    }
    public double getTotalAtivo() {
        return totalAtivo;
    }
    public double getTotalPassivo() {
        return totalPassivo;
    }
    public double getPatLiq() {
        return patLiq;
    }
}
