import java.util.List;

import warehouse.*;

public class Example {

    public static void main(String[] args) throws InvalidSupplier, MultipleDelivery{
//        Warehouse m = new Warehouse();
//        
//        Product banane = m.newProduct("BNN","Banane");
//        banane.setQuantity(33);
//        Product kiwi = m.newProduct("KWI","Kiwi");
//        kiwi.setQuantity(44);
//        
//        Supplier chiquita = m.newSupplier("CQT", "Chiquita");
//        Supplier delmonte = m.newSupplier("DMT", "Del Monte");
//        
//        chiquita.newSupply(banane);
//        chiquita.newSupply(kiwi);
//        
//        delmonte.newSupply(banane);
//        
//        Order ord1 = m.issueOrder(banane,67,chiquita);
//        Order ord2 = m.issueOrder(banane,100,delmonte);
//
//        ord1.setDelivered();
//        
//        System.out.println(ord2.toString());
//
//        System.out.println(m.ordersByProduct());
//        System.out.println(m.orderNBySupplier());
//        
        Warehouse wh;
        Order ord1 ;
        Order ord2 ;
        Order ord3 ;
        Order ord4 ;
        Order ord5 ;
        wh = new Warehouse();
        
        Product banane = wh.newProduct("BNN","Banane");
        banane.setQuantity(33);
        Product kiwi = wh.newProduct("KWI","Kiwi");
        kiwi.setQuantity(44);
        Product mele = wh.newProduct("APL","Apples");
        mele.setQuantity(80);

        Supplier chiquita = wh.newSupplier("CQT", "Chiquita");
        Supplier delmonte = wh.newSupplier("DMT", "Del Monte");
        Supplier valVenosta = wh.newSupplier("VVN", "Val Venosta");
        Supplier melinda = wh.newSupplier("MLD", "Melinda");
        
        
        chiquita.newSupply(banane);
        delmonte.newSupply(kiwi);
        delmonte.newSupply(banane);
        valVenosta.newSupply(mele);
        melinda.newSupply(mele);
        
         ord1 = wh.issueOrder(banane,67,chiquita);
         ord2 = wh.issueOrder(banane,100,delmonte);
         ord3 = wh.issueOrder(mele,150,valVenosta);
         ord4 = wh.issueOrder(kiwi,67,delmonte);
         ord5 = wh.issueOrder(mele,50,melinda);

         ord2.setDelivered();
         ord4.setDelivered();
         ord3.setDelivered();
         ord5.setDelivered();
         
        List<String> dbp = wh.countDeliveredByProduct();

        
        System.out.println(dbp);
        
        String apples = dbp.get(0); // 2 Apples orders
        System.out.println(apples);
        String second = dbp.get(1); // 1 Banana or 1 Kiwi
        System.out.println(second);
    }
}
