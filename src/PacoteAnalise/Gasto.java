package PacoteAnalise;
public class Gasto {
    public String nome;
    public int codCategoria;
    public int subCategoria;

    public Gasto(String name, int codCat, int subCat) {
        this.setNome(name);
        this.setCodCategoria(codCat);
        this.setSubCategoria(subCat);
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String name) {
        this.nome = name;
    }
    public int getCodCategoria() {
        return this.codCategoria;
    }
    public void setCodCategoria (int codCat) {
        this.codCategoria = codCat;
    }
    public int getSubCategoria () {
        return this.subCategoria;
    }
    public void setSubCategoria (int subCat) {
        this.subCategoria = subCat;
    }
}