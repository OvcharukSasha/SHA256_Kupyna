import algorithms.kupyna.hash.KupynaProcess;
import org.apache.commons.lang.time.StopWatch;
import algorithms.sha256.Sha256;

import java.nio.charset.StandardCharsets;

import static algorithms.utils.Utils.bytesToHexString;

public class ProofOfWork {


    public static String proofOfWorkSha256(String previousTx, String currentTx, String difficult) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int nounce = 0;
        Sha256 sha256 = new Sha256();
        while (true) {

            String originalString = previousTx + currentTx + nounce;

            String sha256hex = bytesToHexString(sha256.hash(originalString.getBytes(StandardCharsets.US_ASCII)));

            if (sha256hex.startsWith(difficult)) {
                stopWatch.stop();
                System.out.println("Difficulty level: " + difficult + "\nNounce: " + nounce + "\nElapsed time: " + stopWatch.getTime() + " milli seconds\nHashedString: " + sha256hex);
                System.out.println("----------------------------------------------------");
                return sha256hex;
            }
            nounce++;
        }
    }

    public static String proofOfWorkKupyna(String previousTx, String currentTx, String difficult) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int nounce = 0;
        KupynaProcess kupynaProcess = new KupynaProcess();
        while (true) {
            String originalString = previousTx + currentTx + nounce;
            String sha256hex = kupynaProcess.generateHashByKupyna(originalString);

            if (sha256hex.startsWith(difficult)) {
                stopWatch.stop();
                System.out.println("Difficulty level: " + difficult + "\nNounce: " + nounce + "\nElapsed time: " + stopWatch.getTime() + " milli seconds\nHashedString: " + sha256hex);
                System.out.println("----------------------------------------------------");
                return sha256hex;
            }
            nounce++;
        }
    }

    public static void main(String[] args) {
        System.out.println("\nProof of work for SHA-256:");
        proofOfWorkSha256("previousTx", "currentTx", "0");
        proofOfWorkSha256("previousTx", "currentTx", "0000");

        System.out.println("\nProof of work for Kupyna:");
        proofOfWorkKupyna("previousTx", "currentTx", "0");
        proofOfWorkKupyna("previousTx", "currentTx", "0000");
    }
}