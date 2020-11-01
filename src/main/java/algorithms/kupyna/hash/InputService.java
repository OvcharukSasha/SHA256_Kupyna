package algorithms.kupyna.hash;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import static algorithms.utils.Utils.*;

public class InputService {

    private static final int L_FOR_KUPYNA_256 = 512;

    public static byte[] generateInputMessage(String message) {
//        System.out.println("Input message in HEX: " + bytesToHex(message.getBytes(UTF_8)));

        int N = message.getBytes().length * 8;
        int d = mod(-N - 97, L_FOR_KUPYNA_256);


        int bytesToFirstAdd = (d - 7) / 8;
        int bytesToSecondAdd = intToHexBytesLE(N).length;
        int bytesToThirdAdd = 64 - (message.getBytes().length % 64) - 1 - bytesToFirstAdd - bytesToSecondAdd;

        ByteBuf messageByBlocks = Unpooled.buffer(0);
        messageByBlocks.writeBytes(message.getBytes());
        messageByBlocks.writeByte(128);
        messageByBlocks.writeBytes(new byte[bytesToFirstAdd]);
        messageByBlocks.writeBytes(intToHexBytesLE(N));
        messageByBlocks.writeBytes(new byte[bytesToThirdAdd]);


        return messageByBlocks.array();
    }
}
