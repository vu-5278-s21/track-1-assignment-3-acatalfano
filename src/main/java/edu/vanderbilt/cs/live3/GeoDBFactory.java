package edu.vanderbilt.cs.live3;

public class GeoDBFactory {

    public GeoDB newDatabase(GeoHashFactory factory, int bitsOfPrecision) {
        return new TreeGeoDB(factory, bitsOfPrecision);
    }

}
