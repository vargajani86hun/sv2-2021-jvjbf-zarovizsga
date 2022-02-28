package shipping;

public class InternationalPackage implements Transportable {

    public static final int BASE_PRICE = 1200;
    public static final int BREAKABLE_MULTIPLIER = 2;
    public static final int PRICE_PER_KM = 10;

    private int weight;
    private boolean breakable;
    private String destinationCountry;
    private int distance;

    public InternationalPackage(int weight, boolean breakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int calculateShippingPrice() {
        if (breakable) {
            return BASE_PRICE * BREAKABLE_MULTIPLIER + PRICE_PER_KM * distance;
        } else {
            return BASE_PRICE + PRICE_PER_KM * distance;
        }
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }
}
