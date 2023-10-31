package com.example.application.views.resultado;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
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
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Resultado")
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
        baixar.setId("baixar");
        
        container.add(cancelar, baixar);
        
        return container;
    }

    private VerticalLayout resultados(VerticalLayout containerPricipal) {
        H1 tituloAnalise = new H1(this.analise.getTitulo());

        Div horizontalRule = new Div();
        horizontalRule.addClassName("horizontal-rule");

        HorizontalLayout cabecalhos = new HorizontalLayout();

        Span ativos = new Span("Total de ativos:");
        ativos.addClassName("negrito");
        Span valorAtivos = new Span(String.valueOf(this.analise.totalAtivo));
        Div divAtivos = new Div();
        divAtivos.add(ativos, valorAtivos);

        Span passivos = new Span("Total de passivos:");
        passivos.addClassName("negrito");
        Span valorPassivos = new Span(String.valueOf(this.analise.totalPassivo));
        Div divPassivos = new Div();
        divPassivos.add(passivos, valorPassivos);

        Span patrimonioLiquido = new Span("Total de Patrimônio líquido:");
        patrimonioLiquido.addClassName("negrito");
        Span valorPatrimonioLiquido = new Span(String.valueOf(this.analise.getPatrimonioLiquido()));
        Div divPatrimonioLiquido = new Div();
        divPatrimonioLiquido.add(patrimonioLiquido, valorPatrimonioLiquido);

        Span faturamento = new Span("Total de faturamento:");
        faturamento.addClassName("negrito");
        Span valorFaturamento = new Span(String.valueOf(this.analise.getFaturamento()));
        Div divFaturamento = new Div();
        divFaturamento.add(faturamento, valorFaturamento);

        Div horizontalRule2 = new Div();
        horizontalRule2.addClassName("horizontal-rule");

        cabecalhos.add(divFaturamento, divAtivos, divPassivos, divPatrimonioLiquido);

        Div scrollableContainer = new Div();
        scrollableContainer.getStyle().set("overflow", "auto");
        scrollableContainer.setHeight("25rem");
        scrollableContainer.addClassName("scroller");

        for (Gasto gastoAtual : this.analise.getListaDeGastos()) {

            HorizontalLayout linhaGasto = new HorizontalLayout();

            Span nome = new Span(gastoAtual.getNome());
            nome.setClassName("gastos");
            Span tipo = new Span(gastoAtual.getTipoString());
            tipo.setClassName("gastos");
            Span classificacao = new Span(gastoAtual.getClassificacao());
            classificacao.setClassName("gastos");
            Span valor = new Span(String.valueOf(gastoAtual.getValor()));
            valor.setClassName("gastos");

            linhaGasto.addClassName("linhas-gasto");
            linhaGasto.add(nome, tipo, classificacao, valor);

            scrollableContainer.add(linhaGasto);

        }
        containerPricipal.add(tituloAnalise, horizontalRule, cabecalhos, horizontalRule2, scrollableContainer);
        return containerPricipal;
    }

}
