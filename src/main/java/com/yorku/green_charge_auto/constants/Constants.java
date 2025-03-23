package com.yorku.green_charge_auto.constants;

import java.util.Map;

public class Constants {

    public static Map<province, Double> proviceTaxRate = Map.ofEntries(
            Map.entry(province.ON, 0.13),
            Map.entry(province.AB, 0.05),
            Map.entry(province.BC, 0.12),
            Map.entry(province.MB, 0.12),
            Map.entry(province.NB, 0.15),
            Map.entry(province.NL, 0.15),
            Map.entry(province.PE, 0.15),
            Map.entry(province.QC, 0.15),
            Map.entry(province.SK, 0.11),
            Map.entry(province.NS, 0.14),
            Map.entry(province.NU, 0.05),
            Map.entry(province.YT, 0.05),
            Map.entry(province.NT, 0.05)
    );
}
