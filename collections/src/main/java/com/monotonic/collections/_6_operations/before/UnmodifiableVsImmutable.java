package com.monotonic.collections._6_operations.before;

import java.util.*;

public class UnmodifiableVsImmutable
{
    public static void main(String[] args)
    {
        var mutableCountryToPopulation = new HashMap<>();
        mutableCountryToPopulation.put("UK", 67);
        mutableCountryToPopulation.put("USA", 328);
        mutableCountryToPopulation.put("Wessex", null);

        var unmodifiable = Collections.unmodifiableMap(mutableCountryToPopulation);
        var copied = Map.copyOf(mutableCountryToPopulation);
        var mutableCopy = new HashMap<>(mutableCountryToPopulation);

        System.out.println("mutableCountyPopulation = " + mutableCountryToPopulation);
        System.out.println("unmodifiable = " + unmodifiable);
        System.out.println("copied = " + copied);

//        var countryToPop =  Map.of("UK", 67, "USA", 328, "Wessex", null);
//        System.out.println(countryToPop);
//        countryToPop.put("Germany", 83);
    }
}
