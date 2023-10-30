package com.example.application.views.inicio;

import com.example.application.Analisador.Analise;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Inicio")
@Route(value = "Inicio", layout = MainLayout.class)
@StyleSheet("./estilos/inicio.css")
public class InicioView extends VerticalLayout {

    public InicioView() {
        VerticalLayout container = new VerticalLayout();
        container.addClassName("container");
        container.getElement().getStyle().set("width", "50%");
        container.getElement().getStyle().set("margin-left", "25%");

        Image logo = new Image("./images/Logo.png", "Analisador de Gastos");
        logo.setWidth("calc(99.9% - 30rem)");

        FormLayout formLayout = new FormLayout();
        formLayout = criaForm(formLayout);
        
        container.add(logo, formLayout);
          
        add(container);
    }

    private FormLayout criaForm(FormLayout formLayout){

        TextField titulo = new TextField("Titulo:");
        titulo.addClassName("input-form");
        titulo.setClearButtonVisible(true);
        titulo.setTooltipText("Insira o titulo do projeto.");
    
        TextField faturamento = new TextField("Faturamento:");
        faturamento.addClassName("input-form");
        faturamento.setClearButtonVisible(true);
        faturamento.setTooltipText("informe o faturamento em numero");
        faturamento.setPrefixComponent(new Span("R$:"));
        // Configurar um padrão regex para permitir apenas números inteiros positivos
        faturamento.setPattern("^\\$?\\d+(?:\\.\\d{1,2})?$");

        // Adicionar uma mensagem de erro personalizada
        faturamento.setErrorMessage("Somente numeros, separados por '.' e com no maximo duas casas decimais. Ex: 244.56");

        Button continuar = new Button("Continuar");
        continuar.addClassName("botao");
        continuar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        continuar.addClickListener(e -> {
            // Obter os valores dos campos
            String valorTitulo = titulo.getValue();
            String valorFaturamento = faturamento.getValue();
            double faturamentoValor;
            // Validar os dados
            if (isValid(valorTitulo, valorFaturamento)) {
                faturamentoValor = Double.parseDouble(valorFaturamento);
                Analise analise = new Analise(faturamentoValor, valorTitulo);
                // guarda na sessão o objeto analise
                VaadinSession.getCurrent().setAttribute("analiseAtual", analise);
                // ir para a pagina de gastos
                UI.getCurrent().navigate("Gastos");
            }
            // Caso contrário, a validação falhou e você pode exibir uma mensagem de erro ao usuário
        });

        formLayout.getElement().getStyle().set("display", "flex"); 
        formLayout.getElement().getStyle().set("justify-content", "center"); 
        formLayout.add(titulo, faturamento, continuar);
        formLayout.setResponsiveSteps(
            // Use one column by default
            new ResponsiveStep("0", 1));
        return formLayout;
    }
    private boolean isValid(String titulo, String faturamento) {
        // Implemente a lógica de validação dos campos aqui
        // Você pode usar regex, conversões de tipo, ou qualquer outra lógica necessária
        // Retorne true se os dados forem válidos, caso contrário, retorne false
        return true; // ou false, dependendo da validação
    }
}
