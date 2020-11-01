import algorithms.sha256.Sha256;
import at.favre.lib.bytes.Bytes;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static algorithms.utils.Utils.bytesToHexString;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class Sha256Test {
    // HASHING - hash(byte[])

    @Test
    public void testHashEmpty() {
        byte[] b = {};
        byte[] expected = Bytes.parseHex("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855").array();


        assertArrayEquals(expected, Sha256.hash(b));
    }

    @Test
    public void testHashRegular() {
        byte[] b = "Hello world!".getBytes(StandardCharsets.US_ASCII);
        byte[] expected = Bytes.parseHex("c0535e4be2b79ffd93291305436bf889314e4a3faec05ecffcbb7df31ad9e51a").array();

        System.out.println(bytesToHexString(Sha256.hash("Hello world!".getBytes(StandardCharsets.US_ASCII))));
        assertArrayEquals(expected, Sha256.hash(b));
    }

    @Test
    public void testHashLong() {
        byte[] b = ("Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Proin pulvinar turpis purus, sit amet dapibus magna commodo "
                + "quis metus.").getBytes(StandardCharsets.US_ASCII);
        byte[] expected = Bytes.parseHex("60497604d2f6b4df42cea5efb8956f587f81a4ad66fa1b65d9e085224d255036").array();

        assertArrayEquals(expected, Sha256.hash(b));
    }

    @Test
    public void testHashRawBytes() {
        byte[] b = new byte[256];
        for (int i = 0; i < b.length; ++i) {
            b[i] = (byte) i;
        }

        byte[] expected = Bytes.parseHex("40aff2e9d2d8922e47afd4648e6967497158785fbd1da870e7110266bf944880").array();

        assertArrayEquals(expected, Sha256.hash(b));
    }

    @Test
    public void testHash55() {
        byte[] b = new byte[55];
        Arrays.fill(b, (byte) 'a');

        byte[] expected = Bytes.parseHex("9f4390f8d30c2dd92ec9f095b65e2b9ae9b0a925a5258e241c9f1e910f734318").array();

        assertArrayEquals(expected, Sha256.hash(b));
    }

    // PADDING

    @Test
    public void testPaddedLengthDivisibleBy512() {
        for (int length = 0; length <= 128; ++length) {
            byte[] b = new byte[length];

            int[] padded = Sha256.pad(b);
            int paddedLengthBits = padded.length * Integer.BYTES * 8;

            assertEquals(0, paddedLengthBits % 512, String.format("%d not padded to 512 bits", length));
        }
    }

    @Test
    public void testPaddedMessageHas1Bit() {
        byte[] b = new byte[64];

        int[] padded = Sha256.pad(b);

        assertEquals(0b1000_0000_0000_0000_0000_0000_0000_0000, padded[b.length / Integer.BYTES]);
    }
}