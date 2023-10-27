package Analisador;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.FileReader;

import Analisador.tiposPack.Gasto;
import Analisador.tiposPack.Classificacao;

public class Analise {
    private List<Gasto> listaDeGastos = new ArrayList<>();
    private double faturamento;
    private String titulo;
    public double totalAtivo;
    public double totalPassivo;
    public double patLiq;
    private int currentId;

    public Analise(double faturamento, String titulo) {
        this.titulo = titulo;
        this.faturamento = faturamento;
    }


    public void registrarGasto(String nome, double valor, int tipo, int codCategoria, int subCategoria) {
        this.currentId = this.currentId + 1;
        Gasto novoGasto = new Gasto(nome, this.currentId, valor, tipo, codCategoria, subCategoria);
        listaDeGastos.add(novoGasto);
        somarAtivo(novoGasto);
        somarPassivo(novoGasto);
        System.out.println("Gasto '" + novoGasto.getNome() + "' adicionado!");
    }

    public void excluirGasto(int id) { // método para encontrar e remover uma instância da gasto dentro da
                                       // listaDeGastos utilizando a variável "id" da classe Gasto
        Gasto gastoToRemove = null;
        for (Gasto gasto : listaDeGastos) {
            if (gasto.getId() == (id)) {
                gastoToRemove = gasto;
                subtrairAtivo(gastoToRemove);
                subtrairPassivo(gastoToRemove);
                System.out.println("Gasto '" + gasto.getNome() + "' removido");
                break;
            }
        }
        if (gastoToRemove != null) {
            listaDeGastos.remove(gastoToRemove);
        }
    }

    private void somarAtivo(Gasto novoGasto) {
        if (novoGasto.getTipo() == 0) { // Verifica se o novo gasto é ativo
            totalAtivo += novoGasto.valor; // Soma o valor do novo gasto ao total ativo
        } // Se for um passivo, retira do total de ativo

    }

    private void subtrairAtivo(Gasto gastoToRemove) {
        if (gastoToRemove.getTipo() == 0) { // Verifica se o novo gasto é ativo
            totalAtivo -= gastoToRemove.valor; // Subtrai o valor do gasto que está sendo removido do total ativo
        }
    }

    private void somarPassivo(Gasto novoGasto) {
        if (novoGasto.getTipo() == 1) {
            totalPassivo += novoGasto.valor;
        }
    }

    private void subtrairPassivo(Gasto gastoToRemove) {
        if (gastoToRemove.getTipo() == 1) {
            totalPassivo -= gastoToRemove.valor;
        }
    }

    public double getPatrimonioLiquido() {
        this.patLiq = totalAtivo - totalPassivo;
        return this.patLiq;
    }

    public void exportador() {

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(this.titulo + ".csv"), StandardCharsets.UTF_8))) {
            writer.write("Nome, Valor, Tipo, Classificação\n");

            for (Gasto gasto : listaDeGastos) {
                writer.write(gasto.getNome() + ", " + gasto.getValor() + ", "
                        + (gasto.getTipo() == 0 ? "Ativo" : "Passivo") + ", " + gasto.getClassificacao() + "\n");
            }
            writer.write("Fim dos gastos!\n");
            String valorFormatado = String.format(
                    "\n Total de Ativos: %s, Total de Passivos: %s, Patrimônio Líquido: %s \n",
                    formatarValor(getTotalAtivo()), formatarValor(getTotalPassivo()), formatarValor(getPatrimonioLiquido()));
            writer.write(valorFormatado);
            System.out.println("Analise exportada!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int mapearTipo(String tipo) {
        if ("Ativo".equalsIgnoreCase(tipo)) {
            return 1;
        } else if ("Passivo".equalsIgnoreCase(tipo)) {
            return 0;
        } else {
            // Lidar com outros casos ou erros, se necessário
            return -1; // Por exemplo, retornar -1 para um caso inválido
        }
    }

    public Classificacao numerarClassificacao(String nomeCategoria, String nomeSubCategoria) {
        Classificacao classificacao = new Classificacao(0, 0);

        switch (nomeCategoria) {
            case "Desembolso":
                classificacao.codCategoria = 0;
                break;
            case "Perda":
                classificacao.codCategoria = 1;
                break;
            case "Despesa":
                classificacao.codCategoria = 2;
                break;
            case "Investimento":
                classificacao.codCategoria = 3;
                break;
            case "Custo":
                classificacao.codCategoria = 4;
                if ("Comercial".equals(nomeSubCategoria)) {
                    classificacao.subCategoria = 1;
                } else if ("Industrial".equals(nomeSubCategoria)) {
                    classificacao.subCategoria = 2;
                }
                break;
            // Trate outros casos ou erros, se necessário
        }

        return classificacao;
    }

    public Analise importarDados(String nomeDoArquivo, Analise analise) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDoArquivo))) {
            String line;
            // Ler a primeira linha com os cabeçalhos, se houver
            if ((line = reader.readLine()) != null) {
                // Supondo que os cabeçalhos sejam: "Nome, Valor, Tipo, Classificação"
                // Você pode processar essa linha, mas pode não ser necessária
            }

            // Analise analise2 = new Analise(0.0, "Análise Importada", new ArrayList<>());

            while ((line = reader.readLine()) != null) {
                if (line.equals("Fim dos gastos!")) {
                    break; // Parar a importação
                }

                String[] dados = line.split(",");
                String nome = dados[0];
                double valor = Double.parseDouble(dados[1]);
                String tipoString = "Ativo"; // ou "Passivo"
                int tipo = mapearTipo(tipoString);
                String classificacao = dados[3]; // Classificação completa

                // Separe a classificação em categoria e subcategoria
                String[] partesClassificacao = classificacao.split(" ");
                String nomeCategoria = partesClassificacao[0].trim(); // Categoria
                String nomeSubCategoria = partesClassificacao.length > 1 ? partesClassificacao[1].trim() : null; // Subcategoria,
                                                                                                                 // se
                                                                                                                 // existir

                // Chame o método para converter os nomes das categorias em objetos
                // Classificacao
                Classificacao classificacaoObj = numerarClassificacao(nomeCategoria, nomeSubCategoria);

                // Adicione o gasto à análise
                analise.registrarGasto(nome, valor, tipo, classificacaoObj.codCategoria, classificacaoObj.subCategoria);
                System.out.println("Gasto adicionado à análise: " + nome);

            }
            return analise;
            // Resto do código
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Trate os erros apropriadamente
        }
    }

    // Necessário para usar "." invés de "," na formatação
    private String formatarValor(double valor) {
        DecimalFormat df = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.US));
        return df.format(valor);
    }

    // Getters e Setters
    public String getTitulo() {
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

    public String getListaDeGastosString() {
        StringBuilder sb = new StringBuilder();
        for (Gasto gasto : listaDeGastos) {
            sb.append(gasto.leitor()).append("\n");
        }
        return sb.toString();
    }
}
