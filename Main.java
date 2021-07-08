import java.util.Date;
import java.util.Random;

public class Main {
	static int difficulty;
	
	public static String randomNonce(int length) {
		String retString = "";
		Random random = new Random();
		
		for (int i=0; i<length; i++) {
			retString += (char) (random.nextInt(26)+'a'); //how do concatonate string, then how do find random element in string
		}
		return retString;

	}

	public static void setDifficulty(long lastBlockTime, long timeNow) {
		//if block.timestamp - block.lasttimestamp < 5000 milliseconds
		//difficulty++
		System.out.println("Time in MS for Difficulty: "+(timeNow-lastBlockTime));
		if (lastBlockTime != -1) {
			if (timeNow - lastBlockTime < 1000) {
				difficulty++;
			}
			else if (difficulty > 1) {
				difficulty--;
			}
		}
		else {
			difficulty = 1;
			lastBlockTime = new Date().getTime();
		}
	}

	public static String multString(String string, int times) {
		String retString = "";
		for (int i=0; i<times; i++) {
			retString+=string;
		}
		return retString;
	}

	public static void main(String[] args) {
		//Block block = new Block("0", StringUtil.Sha256("0"));
		BlockChain blockChain = new BlockChain();
		String guessHash = StringUtil.Sha256("o");
		int i = 0;
		long lastBlockTime = -1;

		//"MINING"
		while (true) {
			int nonceLength = 1;
			int guessCounter = 0;
			double maxGuesses;
			difficulty = 1;
			boolean cont = true;
			String nonce;

			guessHash = StringUtil.Sha256("o");
			

			while (cont) {  //This condition is never being met
				
				//System.out.println(guessHash.substring((guessHash.length()-difficulty), guessHash.length()-1));

				nonce = randomNonce(nonceLength);
				guessHash = StringUtil.Sha256(blockChain.toString()+nonce);
				guessCounter++;
				maxGuesses = Math.pow(26, nonceLength); 
				if (guessCounter > maxGuesses) {
					nonceLength++;
				}
				System.out.println("Noncelength: "+nonceLength+" guesscounter: "+guessCounter+" guesshash: "+guessHash+" nonce: "+nonce+" Difficulty: "+difficulty);

				if (difficulty == 1) {
					cont = !(guessHash.substring(guessHash.length()-1).equals(multString("0", difficulty)));
				}
				else {
					System.out.println("difficulty is not one");
					cont = !(guessHash.substring(guessHash.length()-difficulty, guessHash.length()-1).equals(multString("0", difficulty)));
				}

				setDifficulty(lastBlockTime, new Date().getTime());
			}
			lastBlockTime = new Date().getTime();
			
			System.out.println(blockChain.toString());
			
			blockChain.addBlock(Integer.toString(i));

			i++;
			//break;
		}

	}
}
