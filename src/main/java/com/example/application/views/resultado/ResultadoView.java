package com.example.application.views.resultado;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.application.Analisador.Analise;
import com.example.application.Analisador.tiposPack.Gasto;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Analisador de Gastos")
@Route(value = "Resultado", layout = MainLayout.class)
@StyleSheet("./estilos/resultado.css")
public class ResultadoView extends VerticalLayout {
    Analise analise = (Analise) VaadinSession.getCurrent().getAttribute("analiseAtual");

    public ResultadoView() {
        if (this.analise == null) {
            UI.getCurrent().navigate("");
        } else {
            VerticalLayout containerPricipal = new VerticalLayout();
            VerticalLayout containerBotoes = new VerticalLayout();
            containerPricipal.addClassName("container-resultados");
            containerBotoes.addClassName("container-botoes");
            containerPricipal = resultados(containerPricipal);
            containerBotoes = containerBotoes(containerBotoes);

            add(containerPricipal, containerBotoes);
        }

    }

    private VerticalLayout containerBotoes(VerticalLayout container) {
        Button cancelar = new Button("Voltar Inicio");
        cancelar.addClassName("botoes-fim");
        cancelar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelar.addClickListener(e -> {
            VaadinSession.getCurrent().setAttribute("analiseAtual", null);
            UI.getCurrent().navigate("");
        });

        String csvString = this.analise.csvString();
        
        // Crie um StreamResource a partir dos bytes do arquivo CSV
        String titulo = this.analise.getTitulo();
        String nomeArquivo = titulo.strip().replaceAll(" ", "_") + ".csv";
        
        StreamResource resource = new StreamResource(nomeArquivo, () -> {
                byte[] csvBytes;
                try {
                    csvBytes = csvString.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // Lidere com a exceção, se necessário.
                    csvBytes = csvString.getBytes(); // Use a codificação padrão se UTF-8 não estiver disponível.
                }
                return new ByteArrayInputStream(csvBytes);
        });

        Anchor baixar = new Anchor(resource, "Baixar em CSV");
        baixar.setClassName("botoes-fim");
        baixar.setId("baixar");
        
        container.add(cancelar, baixar);
        
        return container;
    }

    private VerticalLayout resultados(VerticalLayout containerPricipal) {
        H1 tituloAnalise = new H1(this.analise.getTitulo());

        Div horizontalRule = new Div();
        horizontalRule.addClassName("horizontal-rule");
        horizontalRule.setId("rule1");

        HorizontalLayout cabecalhos = new HorizontalLayout();
        cabecalhos.setClassName("cabecalhos-resultado");

        Span ativos = new Span("Total de ativos:");
        ativos.addClassName("negrito");
        Span valorAtivos = new Span(formatarValor(this.analise.totalAtivo));
        valorAtivos.addClassName("valor");
        Div divAtivos = new Div();
        divAtivos.add(ativos, valorAtivos);

        Span passivos = new Span("Total de passivos:");
        passivos.addClassName("negrito");
        Span valorPassivos = new Span(formatarValor(this.analise.totalPassivo));
        valorPassivos.addClassName("valor");
        Div divPassivos = new Div();
        divPassivos.add(passivos, valorPassivos);

        Span patrimonioLiquido = new Span("Total de Patrimônio líquido:");
        patrimonioLiquido.addClassName("negrito");
        Span valorPatrimonioLiquido = new Span(formatarValor(this.analise.getPatrimonioLiquido()));
        valorPatrimonioLiquido.addClassName("valor");
        Div divPatrimonioLiquido = new Div();
        divPatrimonioLiquido.add(patrimonioLiquido, valorPatrimonioLiquido);

        Span faturamento = new Span("Total de faturamento:");
        faturamento.addClassName("negrito");
        Span valorFaturamento = new Span(formatarValor(this.analise.getFaturamento()));
        valorFaturamento.addClassName("valor");
        Div divFaturamento = new Div();
        divFaturamento.add(faturamento, valorFaturamento);

        Div horizontalRule2 = new Div();
        horizontalRule2.addClassName("horizontal-rule");
        horizontalRule2.setId("rule2");

        cabecalhos.add(divFaturamento, divAtivos, divPassivos, divPatrimonioLiquido);

        Div cabecalhoGastos = new Div();
        Span nomeCabecalho = new Span("Nome");
        nomeCabecalho.addClassName("coluna");
        Span tipoCabecalho = new Span("Tipo");
        tipoCabecalho.addClassName("coluna");
        Span classificacaoCabecalho = new Span("Classificacao");
        classificacaoCabecalho.addClassName("coluna");
        Span valorCabecalho = new Span("Valor");
        valorCabecalho.addClassName("coluna");
        cabecalhoGastos.add(nomeCabecalho, tipoCabecalho, classificacaoCabecalho, valorCabecalho);
        cabecalhoGastos.setId("cabecalho-gastos");

        Div scrollableContainer = new Div();
        scrollableContainer.getStyle().set("overflow", "auto");
        scrollableContainer.setHeight("25rem");
        scrollableContainer.addClassName("scroller-resultado");

        for (Gasto gastoAtual : this.analise.getListaDeGastos()) {

            HorizontalLayout linhaGasto = new HorizontalLayout();

            Span nome = new Span(gastoAtual.getNome());
            nome.setClassName("gastos-resultado");
            Span tipo = new Span(gastoAtual.getTipoString());
            tipo.setClassName("gastos-resultado");
            Span classificacao = new Span(gastoAtual.getClassificacao());
            classificacao.setClassName("gastos-resultado");
            Span valor = new Span(formatarValor(gastoAtual.getValor()));
            valor.setClassName("gastos-resultado valor-resultado");

            linhaGasto.addClassName("linhas-resultado-gasto");
            linhaGasto.add(nome, tipo, classificacao, valor);

            scrollableContainer.add(linhaGasto);

        }
        containerPricipal.add(tituloAnalise, horizontalRule, cabecalhos, horizontalRule2,cabecalhoGastos, scrollableContainer);
        return containerPricipal;
    }
    private String formatarValor(BigDecimal valor) {
        BigDecimal valorFormatado = valor.setScale(2, RoundingMode.HALF_UP);
        return valorFormatado.toString();
    }

}
