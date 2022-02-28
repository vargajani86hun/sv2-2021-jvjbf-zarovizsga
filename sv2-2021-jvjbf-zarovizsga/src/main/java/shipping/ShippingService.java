package shipping;

import java.util.*;

public class ShippingService {
    private List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable packageToShipping) {
        packages.add(packageToShipping);
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int minWeight) {
        List<Transportable> results = new ArrayList<>();
        for (Transportable actual : packages) {
            if (actual.isBreakable() == breakable && actual.getWeight() >= minWeight) {
                results.add(actual);
            }
        }
        return results;
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> results = new LinkedHashMap<>();
        for (Transportable actual: packages) {
            updateResultMap(results, actual);
        }
        return results;
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        return packages.stream().filter(p -> !p.getDestinationCountry().equals("Hungary"))
                .map(InternationalPackage.class::cast)
                .sorted(Comparator.comparingInt(InternationalPackage::getDistance))
                .map(Transportable.class::cast)
                .toList();
    }

    public List<Transportable> getPackages() {
        return Collections.unmodifiableList(packages);
    }

    private void updateResultMap(Map<String, Integer> results, Transportable actual) {
        String country = actual.getDestinationCountry();
        if (!results.containsKey(country)) {
            results.put(country, 1);
        } else {
            results.put(country, results.get(country) + 1);
        }
    }
}
