package algorithms.kupyna.hash;

import static algorithms.kupyna.hash.InputService.generateInputMessage;
import static algorithms.kupyna.hash.TPlusCircleService.generateMessageAfterTPlusCircle;
import static algorithms.kupyna.hash.TPlusService.generateMessageAfterTPlus;
import static algorithms.utils.Utils.bytesToHexString;
import static algorithms.utils.Utils.XOR;

public class KupynaProcess {

    private static final int[] META_256 = new int[]{
            0x40, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0
    };

    public String generateHashByKupyna(String message) {
        return bytesToHexString(processMessage(message));
    }

    private byte[] processMessage(String message) {
        byte[] messageByBlocks = generateInputMessage(message);

        byte[] resultBlockOperation = processBlockOperation(messageByBlocks);

        return resultBlockOperation;
    }

    private byte[] processBlockOperation(byte[] messageByBlocks) {

        byte[] messageForTPlusCircle = XOR(messageByBlocks, META_256);

        byte[] messageForTPlus = messageByBlocks;

        byte[] messageAfterTPlusCircle = generateMessageAfterTPlusCircle(messageForTPlusCircle);

        byte[] messageAfterTPlus = generateMessageAfterTPlus(messageForTPlus);

        byte[] messageAfterLastBlockOperation = lastBlockOperation(messageAfterTPlusCircle, messageAfterTPlus);

        byte[] messageAfterFinalOperation = finalOperation(messageAfterLastBlockOperation);

        byte[] cutMessage = cutTo256Bit(messageAfterFinalOperation);

        return cutMessage;
    }

    private byte[] lastBlockOperation(byte[] messageAfterTPlusCircle, byte[] messageAfterTPlus) {
        byte[] xorInputParameters = XOR(messageAfterTPlusCircle, messageAfterTPlus);

        return XOR(xorInputParameters, META_256);
    }

    private byte[] finalOperation(byte[] messageAfterLastBlockOperation) {
        byte[] messageForXOR = messageAfterLastBlockOperation;

        byte[] messageAfterTPlusCircle = generateMessageAfterTPlusCircle(messageAfterLastBlockOperation);

        return XOR(messageForXOR, messageAfterTPlusCircle);
    }

    private byte[] cutTo256Bit(byte[] messageAfterFinalOperation) {
        byte[] message256Bit = new byte[32];

        for (int i = 0; i < 32; i++) {
            message256Bit[i] = messageAfterFinalOperation[32 + i];
        }

        return message256Bit;
    }

}
