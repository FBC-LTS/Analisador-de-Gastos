package com.example.application.views.gastos;

import com.example.application.Analisador.Analise;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
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

        TextField gasto = new TextField("Nome:");
        gasto.addClassName("input-form");
        gasto.setClearButtonVisible(true);
        gasto.setTooltipText("Insira o nome do gasto.");
        
        ComboBox<String> tipo = new ComboBox<>("Tipo");
        tipo.setItems("ativo", "passivo");

        formLayout.add(gasto,tipo);
        return formLayout;
    }
    

}
