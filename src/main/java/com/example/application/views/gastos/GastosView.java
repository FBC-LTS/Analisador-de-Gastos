package com.example.application.views.gastos;

import com.example.application.Analisador.Analise;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Gastos")
@Route(value = "Gastos", layout = MainLayout.class)
@StyleSheet("./estilos/gastos.css")
public class GastosView extends VerticalLayout {

    public GastosView() {
        // inicio a analise pegando ela da sessão
        Analise analise = (Analise) VaadinSession.getCurrent().getAttribute("analiseAtual");
        if (analise == null){
            UI.getCurrent().navigate("");
        }else{
            HorizontalLayout containerFormulario = new HorizontalLayout();
            containerFormulario.addClassName("container");
            VerticalLayout containerResultado = new VerticalLayout();
            containerResultado.addClassName("container");
            HorizontalLayout containerBotoes = new HorizontalLayout();
            containerBotoes.addClassName("container");

            FormLayout formLayout = new FormLayout();
            formLayout = formulario(formLayout);
            containerFormulario.add(formLayout);
            
            add(containerFormulario, containerResultado, containerBotoes);
        
        } 
    }

    private FormLayout formulario(FormLayout formLayout){

        TextField nomeGasto = new TextField("Nome:");
        nomeGasto.addClassName("input-form");
        nomeGasto.setClearButtonVisible(true);
        nomeGasto.setTooltipText("Insira o nome do gasto.");
        
        ComboBox<String> tipo = new ComboBox<>("Tipo");
        tipo.setItems("ativo", "passivo");

        ComboBox<String> classificacao = new ComboBox<>("Classificação");
        classificacao.setItems("Perda", "Retornável", "Relacionado a produção?");

        ComboBox<String> subClassificacao = new ComboBox<>("Sub-classificação:");
        subClassificacao.setItems("Indiretamente", "Diretamente comercial", "Diretamente industrial");
        subClassificacao.setVisible(false);
        
        classificacao.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            if ("Relacionado a produção?".equals(selectedValue)) {
                subClassificacao.setVisible(true); // Torna a ComboBox subClassificacao visível
            } else {
                subClassificacao.setVisible(false); // Torna a ComboBox subClassificacao invisível
            }
        });

        TextField preco = new TextField("Preço:");
        preco.addClassName("input-form");
        preco.setClearButtonVisible(true);
        preco.setTooltipText("informe o preço/ valor do gasto em numero, com '.' separando as casas decimais");
        preco.setPrefixComponent(new Span("R$:"));

        Button salvar = new Button("Salvar");
        salvar.addClassName("botao");
        salvar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        formLayout.add(nomeGasto, tipo, classificacao, subClassificacao, preco, salvar);
        return formLayout;
    }
    

}
