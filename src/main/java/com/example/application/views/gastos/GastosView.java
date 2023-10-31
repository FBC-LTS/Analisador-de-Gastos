package com.example.application.views.gastos;

import com.example.application.Analisador.Analise;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
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
    Analise analise = (Analise) VaadinSession.getCurrent().getAttribute("analiseAtual");
    
    public GastosView() {
        
        // inicio a analise pegando ela da sessão
        
        if (this.analise == null){
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
            formLayout.setResponsiveSteps(
                new ResponsiveStep("0", 6)
            );
            formLayout.setId("registra-gasto");
            
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
        classificacao.setItems("Desembolso", "Perda", "Retornável", "Relacionado a produção?");

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
        salvar.addClickListener(e -> {
            String valorNome = nomeGasto.getValue();
            String valorTipo = tipo.getValue();
            String valorClassificacao = classificacao.getValue();
            String valorSubClassificacao = subClassificacao.getValue();
            String valorPreco = preco.getValue();

            Boolean valida = validarGastos(valorNome, valorTipo, valorClassificacao, valorSubClassificacao, valorPreco);
            if (valida) {
                nomeGasto.clear(); 
                tipo.clear(); 
                classificacao.clear(); 
                subClassificacao.clear(); 
                preco.clear();
            }
            System.out.println(this.analise.getListaDeGastosString());
        });
        formLayout.getElement().getStyle().set("justify-content", "space-between");  
        formLayout.add(nomeGasto, tipo, classificacao, subClassificacao, preco, salvar);
        return formLayout;
    }
    
    
    private void registrarGastos(String valorNome, int valorTipo, int valorClassificacao, int valorSubClassificacao, double valorPreco){

        this.analise.registrarGasto(valorNome, valorPreco, valorTipo, valorClassificacao, valorSubClassificacao);
    
    }

    private Boolean validarGastos(String valorNome, String valorTipo, String valorClassificacao, String valorSubClassificacao, String valorPreco){
        Boolean valida = true;
        
        int intTipo = 0; 
        int intClassificacao = 0; 
        int intSubClassificacao = 0;
        Double doublePreco;

        // verificar o tamanho do nome do gasto
        if (valorNome.length() < 3){
            valida = false;
        }

        // atribuindo o valor de acordo com o selecionado, caso não selecionado os parametros serão invalidos
        if (valorTipo.toLowerCase() == "ativo"){
            intTipo = 1;
        }else if (valorTipo.toLowerCase() == "passivo"){
            intTipo = 0;
        }else {
            valida = false;
        }

        // verifica casos nulos 
        
        if (valorClassificacao == "Relacionado a produção?" && valorSubClassificacao == null){
            valida = false;
        }


        if (valorClassificacao == "Desembolso"){
            intClassificacao = 0;
        }else if (valorClassificacao == "Perda"){
            intClassificacao = 1;
        }else if (valorClassificacao == "Retornável"){
            intClassificacao = 3;
        }else if (valorClassificacao == "Relacionado a produção?"){
            if (valorSubClassificacao == "Indiretamente"){
                intClassificacao = 2;
            }else if(valorSubClassificacao == "Diretamente comercial"){
                intClassificacao = 4;
                intSubClassificacao = 1;
            }else if(valorSubClassificacao == "Diretamente industrial"){
                intClassificacao = 4;
                intSubClassificacao = 2;
            }
        }else{
            valida = false;
        }
        
        doublePreco =  Double.parseDouble(valorPreco);
        if (valida){
            registrarGastos(valorNome, intTipo, intClassificacao, intSubClassificacao, doublePreco);
        }
        return valida;
    }

    
}
