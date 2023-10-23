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
    public double patLiq;
    
    public Analise(double faturamento, String titulo) {
        this.titulo = titulo;
        this.faturamento = faturamento;
        listaDeGastos = new ArrayList<>();

    }

    void registrarGasto(String nome, double valor, int tipo, int codCategoria, int subCategoria) {
        Gasto novoGasto = new Gasto(nome, valor, tipo, codCategoria, subCategoria);
                if (novoGasto.getNome().equals(nome)) {
            System.out.println("Um gasto com o mesmo nome já existe, nome duplicados não são aceitos.");
            return;
            }
        listaDeGastos.add(novoGasto);
    }
    void excluirGasto(String nome) {
        // método para encontrar e remover uma instância da gasto dentro da listaDeGastos utilizando a variável "nome" da classe Gasto
        Gasto gastoToRemove = null;
        for (Gasto gasto : listaDeGastos) {
            if (gasto.getNome().equals(nome)) {
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
    public double getDiferenca(){
        return this.diferenca;
    }
    public void setDiferenca(double diferenca) {
        this.diferenca = diferenca;
    }
    public String getTitulo(){
        return this.titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public double getFaturamento() {
        return this.faturamento;
    }
    public void setFaturamento(double faturamento) {
        this.faturamento = faturamento;
    }
    public List<Gasto> getListaDeGastos() {
        return listaDeGastos;
    }
    public void setListaDeGastos(List<Gasto> listaDeGastos) {
        this.listaDeGastos = listaDeGastos;
    }
    public double getTotalAtivo() {
        return totalAtivo;
    }
    public void setTotalAtivo(double totalAtivo) {
        this.totalAtivo = totalAtivo;
    }
    public double getTotalPassivo() {
        return totalPassivo;
    }
    public void setTotalPassivo(double totalPassivo) {
        this.totalPassivo = totalPassivo;
    }
    public double getPatLiq() {
        return patLiq;
    }
    public void setPatLiq(double patLiq) {
        this.patLiq = patLiq;
    }
}
