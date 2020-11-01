package algorithms.utils;

public final class Arrays {
    private Arrays() {
    }

    public static void fill(byte[] var0, byte var1) {
        for (int var2 = 0; var2 < var0.length; ++var2) {
            var0[var2] = var1;
        }

    }
}
