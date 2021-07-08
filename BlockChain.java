import java.util.Date;

public class BlockChain {
	
	public Block chain;

	public BlockChain() {
		chain = new Block(null, "0", null);
	}


	public Block addBlock(String data) {
		chain = new Block(chain, data, StringUtil.Sha256(chain.toString()));
		return chain;
	}


	public String toString() {
		return this.chain.toString();
	}
}


