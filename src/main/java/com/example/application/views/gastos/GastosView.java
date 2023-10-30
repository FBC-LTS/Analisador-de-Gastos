package com.example.application.views.gastos;

import com.example.application.Analisador.Analise;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

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

        return formLayout;
    }


}
