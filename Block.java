import java.util.Date;

public class Block {
	public Block previousBlock;
	public String hash;
	private String data; //our data will be a simple message.
	public long timeStamp; //as number of milliseconds since 1/1/1970.

	//Block Constructor.
	public Block(Block previousBlock, String data, String hash) {
		this.previousBlock = previousBlock;	
		this.data = data;
		this.hash = hash;
		this.timeStamp = new Date().getTime();
	}


	public String toString() {
		return this.hash + " " +
		this.data + " ";
	}
}


