package com.example.application.views.gastos;

import com.example.application.Analisador.Analise;
import com.example.application.Analisador.tiposPack.Gasto;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
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
    VerticalLayout containerResultado = new VerticalLayout();
    VerticalLayout containerBotoes = new VerticalLayout();
    Integer totalGastos = 0;
    Div scrollableContainer = new Div();
    
    
    public GastosView() {
        
        // inicio a analise pegando ela da sessão
        
        if (this.analise == null){
            UI.getCurrent().navigate("");
        }else{
            VerticalLayout containerFormulario = new VerticalLayout();
            containerFormulario.addClassName("container");
            containerFormulario.addClassName("registro-gasto");
            
            
            FormLayout formLayout = new FormLayout();
            formLayout = formulario(formLayout);
            formLayout.setResponsiveSteps(
                new ResponsiveStep("0", 6)
                );
            formLayout.setId("registra-gasto");
            
            containerFormulario.add(formLayout);
                
            this.containerResultado.addClassName("container");
            this.containerResultado.setVisible(false);
            cabecalho();
            

            
            this.containerBotoes.addClassName("container");
            this.containerBotoes.setVisible(true);
            this.containerBotoes.setId("container-botoes");
            containterBotoes();

            add(containerFormulario, this.containerResultado, this.containerBotoes);
        
        } 
    }
    private void containterBotoes() {
        Button cancelar = new Button("Cancelar");
        cancelar.addClassName("botoes-fim");
        cancelar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        cancelar.addClickListener(e -> {
            VaadinSession.getCurrent().setAttribute("analiseAtual", null);
            UI.getCurrent().navigate("");
        });

        Button finalizar = new Button("Finalizar");
        finalizar.addClassName("botoes-fim");
        finalizar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        finalizar.addClickListener(e -> {
            if(this.analise.getTotalDeItensGastos() > 1){
                VaadinSession.getCurrent().setAttribute("analiseAtual", this.analise);
                UI.getCurrent().navigate("Resultado");
            }
        });

        this.containerBotoes.add(cancelar, finalizar);
    }
    private void cabecalho(){
        HorizontalLayout cabecalho = new HorizontalLayout();
        Span nome = new Span("Nome");
        Span tipo = new Span("Tipo");
        Span classificacao = new Span("Classificacao");
        Span valor = new Span("Valor");
        cabecalho.add(nome, tipo, classificacao, valor);
        cabecalho.setId("cabecalho");
        Div horizontalRule = new Div();
        horizontalRule.addClassName("horizontal-rule");
        this.containerResultado.add(cabecalho, horizontalRule);
        scrollableContainer.getStyle().set("overflow", "auto");
        scrollableContainer.setHeight("25rem"); 
        scrollableContainer.addClassName("scroller");
        this.containerResultado.add(this.scrollableContainer);

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
                this.totalGastos = this.totalGastos + 1;
                previwGastos();
            }
            System.out.println(this.analise.getListaDeGastosString());
        });
        formLayout.getElement().getStyle().set("justify-content", "center");  
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

    private void previwGastos() {
        if(!this.containerResultado.isVisible()){
            this.containerResultado.setVisible(true);
        }
        
        HorizontalLayout linhaGasto = new HorizontalLayout();

        Gasto gastoAtual = this.analise.getUltimoGasto();
        
        Span nome = new Span(gastoAtual.getNome());
        nome.setClassName("gastos");
        Span tipo = new Span(gastoAtual.getTipoString());
        tipo.setClassName("gastos");
        Span classificacao = new Span(gastoAtual.getClassificacao());
        classificacao.setClassName("gastos");
        Span valor = new Span(String.valueOf(gastoAtual.getValor()));
        valor.setClassName("gastos");
        Button excluir = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
        excluir.addClassName("botao");
        excluir.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR);
        excluir.setTooltipText("Apagar esse gasto");
        excluir.addClickListener(e -> {
            // Remove a linhaGasto do layout pai
            this.scrollableContainer.remove(linhaGasto);

            // Além disso, você pode chamar a função para remover da classe
            this.analise.excluirGasto(gastoAtual.getId());

            if(this.analise.getTotalDeItensGastos() == 0){
                this.containerResultado.setVisible(false);
            }
        });
        
        linhaGasto.addClassName("linhas-gasto");
        linhaGasto.add(nome, tipo, classificacao, valor, excluir);
        this.scrollableContainer.add(linhaGasto);
    }

    
}
