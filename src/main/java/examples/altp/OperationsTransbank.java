package examples.altp;

import org.json.JSONObject;

import sipay.Amount;
import sipay.altp.Transbank;
import sipay.body.transbank.WebpayMethods;
import sipay.responses.Response;
import sipay.responses.altp.GenericMethods;

public class OperationsTransbank {
	public static void main(String[] args) {
        String path = "config.properties";
        Transbank transbank = new Transbank(path);

        JSONObject payload = new JSONObject();
        JSONObject notify = new JSONObject();

        // El campo order tiene que ser único
        payload.put("order", "prueba-order-sdk-transbank");

        notify.put("result", "https://www.sipay.es");
        payload.put("notify", notify);
        payload.put("policyData", new JSONObject());

        Amount amount = new Amount("10000", "CLP");

        WebpayMethods expressCheckout = new WebpayMethods(payload);
        // Request ALTP methods
        GenericMethods methods = transbank.webpayMethods(expressCheckout, amount);
        
        if (methods == null) {
            System.out.println("Failure in operation. Error connecting to the service");
        } else if (methods.getCode() != 0) {
            System.out.println("Failure in operation. Error:" + methods.getDescription());
        } else {
            System.out.println("Success getting ALTP methods");
        }
        // Get redirect URL
        String webpayUri = transbank.getWebpayMethod(methods);
        System.out.println("Redirect client browser to this URL: " + webpayUri);

        // Final result of the transaction will be sent to the notify result URL


        // PERFORM A WEBPAY REFUND
        String authorizationCode = "1213";
        String buyOrder = "prueba-order-sdk-transbank";
        Amount refundAmount = new Amount("5000", "CLP");

        Response res = transbank.refundWebpay(authorizationCode, buyOrder, amount, refundAmount);
        if (res == null) {
            System.out.println("Failure in operation. Error connecting to the service");
        } else if (res.getCode() != 0) {
            System.out.println("Failure in operation. Error:" + res.getDescription());
        } else {
            System.out.println("Success performing webpay refund");
        }
	}
}
