package ecommerce;

import catalogs.Currency;

public class Amount {
    int amount;
    String currency;

    public Amount(String amount, String currency) {
        Currency currencies = new Currency();

        String[] result = ((String[]) currencies.find(currency));
        if (result == null) {
            throw new java.lang.RuntimeException("Value of currency is incorrect.");
        }

        if (amount.matches("^([0-9]+\\.[0-9]{" + (Integer.parseInt(result[2])) + "})")) {
            this.amount = Integer.parseInt(amount.replace(".", ""));
        } else if (amount.matches("^([0-9]+)$")) {
            this.amount = Integer.parseInt(amount);
        } else {
            throw new java.lang.RuntimeException("Value of " + amount + " is incorrect.");
        }

        if (this.amount <= 0) {
            throw new java.lang.RuntimeException("Value of amount is incorrect.");
        }

        this.currency = currency;
    }

    public Amount(int amount, String currency) {
        Currency currencies = new Currency();

        if (amount <= 0) {
            throw new java.lang.RuntimeException("Value of amount is incorrect.");
        }

        Object result = currencies.find(currency);
        if (result == null) {
            throw new java.lang.RuntimeException("Value of currency is incorrect.");
        }

        this.amount = amount;
        this.currency = currency;
    }
}
