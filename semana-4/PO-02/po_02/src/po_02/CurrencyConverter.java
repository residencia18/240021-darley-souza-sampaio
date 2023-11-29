package po_02;

public class CurrencyConverter {
    public static double convertRealToDollar(double value) {
        double realToDollarRate = 0.21;
        return value * realToDollarRate;
    }

    public static double convertDollarToReal(double value) {
        double dollarToRealRate = 4.87;
        return value * dollarToRealRate;
    }

    public static double convertEuroToReal(double value) {
        double euroToRealRate = 5.36;
        return value * euroToRealRate;
    }

    public static double convertRealToEuro(double value) {
        double realToEuroRate = 0.19;
        return value * realToEuroRate;
    }

    public static double convertDollarToEuro(double value) {
        double dollarToEuroRate = 0.91;
        return value * dollarToEuroRate;
    }

    public static double convertEuroToDollar(double value) {
        double euroToDollarRate = 1.10;
        return value * euroToDollarRate;
    }
}
