package edu.vanderbilt.cs.live3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GeoHashTest {

    private DefaultGeoDBConfiguration configuration = new DefaultGeoDBConfiguration();
    private GeoHashFactory hashFactory = configuration.getHashFactory();

    @Test
    void test2DHashAllZeroPrecision10() {
        assertEquals("0000000000", geohashString(-90, -180, 10));
    }

    @Test
    void testNorthHashAllZeroPrecision10() {
        assertEquals("0000000010", geohashNorthString(-90, -180, 10));
    }

    @Test
    void testSouthHashAllZeroPrecision10() {
        assertEquals("1010101010", geohashSouthString(-90, -180, 10));
    }

    @Test
    void testEastHashAllZeroPrecision10() {
        assertEquals("0000000001", geohashEastString(-90, -180, 10));
    }

    @Test
    void testWestHashAllZeroPrecision10() {
        assertEquals("0101010101", geohashWestString(-90, -180, 10));
    }

    @Test
    void test2DHashAlternate01Precision10() {
        assertEquals("0101010101", geohashString(-90, 180, 10));
    }

    @Test
    void testNorthHashAlternate01Precision10() {
        assertEquals("0101010111", geohashNorthString(-90, 180, 10));
    }

    @Test
    void testSouthHashAlternate01Precision10() {
        assertEquals("1111111111", geohashSouthString(-90, 180, 10));
    }

    @Test
    void testEastHashAlternate01Precision10() {
        assertEquals("0000000000", geohashEastString(-90, 180, 10));
    }

    @Test
    void testWestHashAlternate01Precision10() {
        assertEquals("0101010100", geohashWestString(-90, 180, 10));
    }

    @Test
    void test2DHashAlternate01Precision11() {
        assertEquals("01010101010", geohashString(-90, 180, 11));
    }

    @Test
    void testNorthHashAlternate01Precision11() {
        assertEquals("01010101011", geohashNorthString(-90, 180, 11));
    }

    @Test
    void testSouthHashAlternate01Precision11() {
        assertEquals("11111111111", geohashSouthString(-90, 180, 11));
    }

    @Test
    void testEastHashAlternate01Precision11() {
        assertEquals("00000000000", geohashEastString(-90, 180, 11));
    }

    @Test
    void testWestHashAlternate01Precision11() {
        assertEquals("01010101000", geohashWestString(-90, 180, 11));
    }

    @Test
    void testConsistency() {
        assertEquals("01010101010", geohashString(-90, 180, 11));
    }

    @Test
    void testNorthHashConsistency() {
        assertEquals("01010101011", geohashNorthString(-90, 180, 11));
    }

    @Test
    void testSouthHashConsistency() {
        assertEquals("11111111111", geohashSouthString(-90, 180, 11));
    }

    @Test
    void testEastConsistency() {
        assertEquals("00000000000", geohashEastString(-90, 180, 11));
    }

    @Test
    void testWestConsistency() {
        assertEquals("01010101000", geohashWestString(-90, 180, 11));
    }

    @Test
    void testAltTrailing1sPrecision10() {
        assertEquals("1010101011", geohashString(90, -158.5, 10));
    }

    @Test
    void testNorthHashAltTrailing1sPrecision10() {
        assertEquals("0000000001", geohashNorthString(90, -158.5, 10));
    }

    @Test
    void testSouthHashAltTrailing1sPrecision10() {
        assertEquals("1010101001", geohashSouthString(90, -158.5, 10));
    }

    @Test
    void testEastHashAltTrailing1sPrecision10() {
        assertEquals("1010101110", geohashEastString(90, -158.5, 10));
    }

    @Test
    void testWestHashAltTrailing1sPrecision10() {
        assertEquals("1010101010", geohashWestString(90, -158.5, 10));
    }

    @Test
    void testAltTrailing1sPrecision11() {
        assertEquals("10101010111", geohashString(90, -158.5, 11));
    }

    @Test
    void testNorthHashAltTrailing1sPrecision11() {
        assertEquals("00000000010", geohashNorthString(90, -158.5, 11));
    }

    @Test
    void testSouthHashAltTrailing1sPrecision11() {
        assertEquals("10101010110", geohashSouthString(90, -158.5, 11));
    }

    @Test
    void testEastHashAltTrailing1sPrecision11() {
        assertEquals("10101011101", geohashEastString(90, -158.5, 11));
    }

    @Test
    void testWestHashAltTrailing1sPrecision11() {
        assertEquals("10101010101", geohashWestString(90, -158.5, 11));
    }

    @Test
    void testAltTrailing1sPrecision14() {
        assertEquals("10101010111111", geohashString(90, -158.5, 14));
    }

    @Test
    void testNorthHashAltTrailing1sPrecision14() {
        assertEquals("00000000010101", geohashNorthString(90, -158.5, 14));
    }

    @Test
    void testSouthHashAltTrailing1sPrecision14() {
        assertEquals("10101010111101", geohashSouthString(90, -158.5, 14));
    }

    @Test
    void testEastHashAltTrailing1sPrecision14() {
        assertEquals("10101011101010", geohashEastString(90, -158.5, 14));
    }

    @Test
    void testWestHashAltTrailing1sPrecision14() {
        assertEquals("10101010111110", geohashWestString(90, -158.5, 14));
    }

    @Test
    void testAll1sPrecision14() {
        assertEquals("11111111111111", geohashString(90, 180, 14));
    }

    @Test
    void testNorthHashAll1sPrecision14() {
        assertEquals("01010101010101", geohashNorthString(90, 180, 14));
    }

    @Test
    void testSouthHashAll1sPrecision14() {
        assertEquals("11111111111101", geohashSouthString(90, 180, 14));
    }

    @Test
    void testEastHashAll1sPrecision14() {
        assertEquals("10101010101010", geohashEastString(90, 180, 14));
    }

    @Test
    void testWestHashAll1sPrecision14() {
        assertEquals("11111111111110", geohashWestString(90, 180, 14));
    }

    @Test
    void testPrefix() {
        String actual = hashFactory
            .with(90, -158.5, 14)
            .prefix(7)
            .toString();
        String expected = "1010101";
        assertEquals(actual, expected);
    }

    private String geohashString(double lat, double lng, int precision) {
        return hashFactory
            .with(lat, lng, precision)
            .toString();
    }

    private String geohashNorthString(double lat, double lng, int precision) {
        return hashFactory
            .with(lat, lng, precision)
            .northNeighbor()
            .toString();
    }

    private String geohashSouthString(double lat, double lng, int precision) {
        return hashFactory
            .with(lat, lng, precision)
            .southNeighbor()
            .toString();
    }

    private String geohashWestString(double lat, double lng, int precision) {
        return hashFactory
            .with(lat, lng, precision)
            .westNeighbor()
            .toString();
    }

    private String geohashEastString(double lat, double lng, int precision) {
        return hashFactory
            .with(lat, lng, precision)
            .eastNeighbor()
            .toString();
    }

}
