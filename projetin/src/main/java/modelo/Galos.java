package modelo;

public class Galos {

    private int idGalo;
    private String raca;
    private String name;
    private int power;
    private int life;
    private String senha;  // Senha em texto simples
    private String senhaCriptografada;  // Senha criptografada

    public int getIdGalo() {
        return idGalo;
    }

    public void setIdGalo(int idGalo) {
        this.idGalo = idGalo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }
}
