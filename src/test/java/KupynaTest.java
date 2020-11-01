import algorithms.kupyna.hash.KupynaProcess;

import algorithms.sha256.Sha256;
import at.favre.lib.bytes.BinaryToTextEncoding;
import at.favre.lib.bytes.Bytes;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class KupynaTest {


    @Test
    public void testKupyna() throws UnsupportedEncodingException, DecoderException {
        KupynaProcess kupynaProcess = new KupynaProcess();
        String expected="CD5101D1CCDF0D1D1F4ADA56E888CD724CA1A0838A3521E7131D4FB78D0F5EB6";
        assertEquals(expected, kupynaProcess.generateHashByKupyna(""));
    }
}
