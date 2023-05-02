package model;


import java.io.Serializable;

public class CategoriaBean implements Serializable{

    private static final long serialVersionUID = 1L;

    //Variabili
    private String ID_Categoria;
    private String Nome;

    //Costruttore
    public CategoriaBean() {
        this.ID_Categoria = "";
        this.Nome = "";
    }

    //MetodiGet&Set
    public String getID_Categoria() {
        return ID_Categoria;
    }

    public void setID_Categoria(String ID_Categoria) {
        this.ID_Categoria = ID_Categoria;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}


