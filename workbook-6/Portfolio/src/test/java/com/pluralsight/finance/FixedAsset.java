package com.pluralsight.finance;

public class FixedAsset {

    public class fixedAsset implements Valuable {
        private String name;
        private double value;

        public fixedAsset(String name, double value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public double getValue() {
            return value;
        }
    }


}
