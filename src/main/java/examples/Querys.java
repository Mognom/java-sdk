package examples;

import ecommerce.Ecommerce;
import ecommerce.responses.Card;
import ecommerce.responses.Query;
import ecommerce.responses.Transaction;

public class Querys {

    public static void main(String[] args) {
        String path = "config.properties";
        Ecommerce ecommerce = new Ecommerce(path);

        // region * Consultar tarjeta
        Card cardResp = ecommerce.card("2977e78d1e3e4c9fa6b70");
        if (cardResp == null) {
            System.out.println("Fallo al consultar la tarjeta, Error al conectar con el servicio");
        } else if (cardResp.getCode() != 0) {
            System.out.println("Fallo al realizar la operación, Error: " + cardResp.getDescription());
        } else {
            System.out.println("Consulta procesada correctamente.");
        }
        // endregion

        // region * Consultar operación por id
        Query query = ecommerce.query("000097485106184565651", null);
        if (query == null) {
            System.out.println("Fallo al consultar la tarjeta, Error al conectar con el servicio");
        } else if (query.getCode() != 0) {
            System.out.println("Fallo al realizar la operación, Error: " + query.getDescription());
        } else {
            System.out.println("Consulta procesada correctamente.");
        }

        for (Transaction item : query.getTransactions()) {
            System.out.println("Transaction: " + item.getChannelName() + "," + item.getMethodName() + "," + item.getDescription());
        }
        // endregion

        // region * Consultar operación por ticket
        Query query2 = ecommerce.query(null, "435890fb684443628152fb7ba998d1d0");
        if (query2 == null) {
            System.out.println("Fallo al consultar la tarjeta, Error al conectar con el servicio");
        } else if (query2.getCode() != 0) {
            System.out.println("Fallo al realizar la operación, Error: " + query2.getDescription());
        } else {
            System.out.println("Consulta procesada correctamente.");
        }

        for (Transaction item : query2.getTransactions()) {
            System.out.println(item.getChannelName() + "," + item.getMethodName() + "," + item.getDescription());
        }
        // endregion
    }
}