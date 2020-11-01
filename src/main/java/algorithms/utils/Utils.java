package algorithms.utils;

public class Utils {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private static final int sizeOfIntInHalfBytes = 8;

    private static final int numberOfBitsInAHalfByte = 4;

    private static final int halfByte = 0x0F;

    private static final char[] hexDigits = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    public static String bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];

        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }

        return String.valueOf(hexChars);
    }

    public static int mod(int number, int modNumber) {
        int result = number % modNumber;
        while (result < 0) {
            result = result + modNumber;
        }
        return result;
    }

    public static byte[] intToHexBytesLE(int number) {
        byte[] NinBE = hexStringToByteArray(decToHex(number));
        byte[] NinLE = new byte[NinBE.length];
        for (int i = 0; i < NinBE.length; i++) {
            NinLE[i] = NinBE[NinBE.length - i - 1];
        }

        return NinLE;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String decToHex(int dec) {
        StringBuilder hexBuilder = new StringBuilder(sizeOfIntInHalfBytes);
        hexBuilder.setLength(sizeOfIntInHalfBytes);
        for (int i = sizeOfIntInHalfBytes - 1; i >= 0; --i) {
            int j = dec & halfByte;
            hexBuilder.setCharAt(i, hexDigits[j]);
            dec >>= numberOfBitsInAHalfByte;
        }
        return hexBuilder.toString();
    }

    public static byte[] XOR(byte[] firstArray, int[] secondArray) {
        byte[] thirdArray = new byte[firstArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            thirdArray[i] = (byte) (firstArray[i] ^ secondArray[i]);
        }
        return thirdArray;
    }

    public static byte[] XOR(byte[] firstArray, byte[] secondArray) {
        byte[] thirdArray = new byte[firstArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            thirdArray[i] = (byte) (firstArray[i] ^ secondArray[i]);
        }
        return thirdArray;
    }
}
