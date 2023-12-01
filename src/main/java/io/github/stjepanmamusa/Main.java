package io.github.stjepanmamusa;


import io.github.stjepanmamusa.service.CatService;

public class Main {
    private static final CatService okCatService = new CatService();
    private static final CatService nokCatService = new CatService("http://google.com/");

    public static void main(String[] args) {

        callCatService(okCatService);
        System.err.println("Repeating calls with an unhealthy service");
        callCatService(nokCatService);

    }

    private static void callCatService(CatService service) {
        System.out.println("Getting a single cat fact:");
        System.out.println(service.getSingleCatFact());
        System.out.println("Getting a few cat facts:");
        service.getAFewCatFacts().forEach(fact -> System.out.printf("CAT FACT:\n%s\n", fact));
    }
}