import java.util.*;

public class Block {
	public String hash;
	public String prevHash;
	private String data;
	private long timeStamp;
	private int nonce;
	
	public Block(String data,String prevHash) {
		this.data=data;
		this.prevHash=prevHash;
		timeStamp=new Date().getTime();
		this.hash=calcHash();
	}
	public String calcHash() {
		String calcHash=StringUtil.applySha256(prevHash+Long.toString(timeStamp)+data+Integer.toString(nonce));
		return calcHash;
	}
	
	public void mineCoin(int diff) {
		String target =new String(new char[diff]).replace('\0', '0');
		while(!hash.substring(0,diff).equals(target)) {
			nonce++;
			hash=calcHash();
		}
		System.out.println("Block mined ! :"+hash);
	}
}
