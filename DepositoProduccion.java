import java.util.List;
import java.util.ArrayList;

public class DepositoProduccion {
    private int capDepProd;
    private List<Producto> productos = new ArrayList<>();

    public DepositoProduccion(int capDepProd) {
        this.capDepProd = capDepProd;
    }

    public int getCapDepProd() {
        return capDepProd;
    }

    public synchronized void guardarProducto(Producto p){
        if (capDepProd>0){
            System.out.println("Se guardo un producto tipo " + p.getTipo());
            this.productos.add(p);
            this.capDepProd --;
        }else{
            try {
                System.out.println("un productor esta esperando");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Producto sacarProducto(){
        /*TODO: revisar problema de lista vacía */
        Producto p = productos.get(0);
        productos.remove(0);
        this.capDepProd ++;
        notify();
        return p;
    }

    public synchronized Boolean hayProductos(){
        if (this.productos.size() > 0){
            return true;
        }else{
            return false;
        }
    }
    
}
