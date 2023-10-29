package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route(value = "", layout = MainLayout.class)
@StyleSheet("./estilos/home.css")
public class HomeView extends HorizontalLayout{
    public HomeView() {
        VerticalLayout container = new VerticalLayout();
        container.addClassName("container");
        container.getElement().getStyle().set("width", "50%");
        container.getElement().getStyle().set("margin-left", "25%");
        H1 titulo = new H1("Analisador de Gastos");
        titulo.addClassName("titulo");
        Paragraph textoAnalisador = new Paragraph("O Analisador de Gastos é uma aplicação intuitiva e eficiente projetada para simplificar o processo de registro, categorização e análise de despesas financeiras pessoais ou comerciais. Essa ferramenta versátil oferece recursos essenciais para auxiliar os usuários a manter o controle de seus gastos, categorizá-los e gerar relatórios detalhados em formato CSV.");
        Paragraph textoInfo = new Paragraph("Para mais informações consulte a aba de documentação.");
        Paragraph textoIniciar = new Paragraph("Clique em iniciar para começar a gerenciar seus gastos.");
        Button iniciar = new Button("Iniciar");
        iniciar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        iniciar.getElement().getStyle().set("align-self", "center");
        iniciar.addClickListener(e -> {
            UI.getCurrent().navigate("Inicio");
        });
        container.add(titulo, textoAnalisador, textoInfo, textoIniciar, iniciar);
        add(container);
    }
}