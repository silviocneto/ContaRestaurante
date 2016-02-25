package silvio.com.bar5;

/**
 * Created by Silvio on 2/23/2016.
 */
public class Objeto_conta {
    private int idpedido;
    private int idinteg;
    private String IntegName;

    public Objeto_conta(){}

    public Objeto_conta(int idpedido, int idinteg, String IntegName){
        this.idpedido = idpedido;
        this.idinteg = idinteg;
        this.IntegName = IntegName;
    }

    public void setIdpedido(int idpedido){
            this.idpedido = idpedido;
        }

    public int getIdpedido(){
            return idpedido;
        }

    public void setIdinteg(int idinteg){
            this.idinteg = idinteg;
        }

    public int getIdinteg(){
            return idinteg;
        }

    public void setIntegName(String name){ this.IntegName = name;}

    @Override
    public String toString() {
        /*return "Item{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
                */
        return "Pedido "+ idpedido +"    " + "IdInteg " + IntegName;

    }
}
