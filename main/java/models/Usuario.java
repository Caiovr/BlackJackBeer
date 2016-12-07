
package models;

import java.util.ArrayList;


public class Usuario {
    
    final private String nome;
    final private String cpf;
    private String email;
    private String contato;
    private float saldo;
    ArrayList<Produto> comprados;
    private Cartao cartao;

    public Usuario(String nome, String cpf, String contato){
        saldo = 0;
        this.nome = nome;
        this.contato = contato;
        this.cpf = cpf;
        comprados = new ArrayList<Produto>();
        this.cartao = null;
    }
    
    public Usuario(String nome, String cpf, String contato, String email){
        saldo = 0;
        this.nome = nome;
        this.contato = contato;
        this.cpf = cpf;
        this.email = email;
        comprados = new ArrayList<Produto>();
        this.cartao = null;

    }
    
    private boolean validar(Produto produto,int quant){
        //if(this.cartao != null){
            if(quant*(produto.getPreco()) > this.saldo){
                return false;
            }else
                return true;
    }
    
    public boolean comprar(Produto produto,int quant){
        if(validar(produto,quant)){
            this.saldo-=produto.getPreco();
            comprados.add(produto);
            return true;
        }//else
            //cadastrarCartao();
        return false;
    }
    
    public void retirar(String codigo){
        int i=0;
        for(i = 0 ; i<this.comprados.size(); i++){
            if(this.comprados.get(i).getCodigo().equals(codigo)){
               this.comprados.get(i).setSituacao();
            }
        }
    }
    
    public void cancelar(Produto produto){
        this.comprados.remove(produto);
    }
    
}
