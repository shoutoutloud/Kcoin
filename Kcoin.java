import java.util.*;
import com.google.gson.GsonBuilder;

public class Kcoin {
	
	public static ArrayList<Block> block=new ArrayList<Block>();
	public static int diff=5;
	public static void main(String[] args) {
		block.add(new Block("First block","0"));
		//System.out.println("Hash for genesis block: "+genesisBlock.hash);
		System.out.println("Mining started...");
		block.get(0).mineCoin(diff);
		
		block.add(new Block("Second block",block.get(block.size()-1).hash));
		//System.out.println("Hash for second block: "+secondBlock.hash);
		System.out.println("Started mining...");
		block.get(1).mineCoin(diff);
		
		block.add(new Block("Third block",block.get(block.size()-1).hash));
		//System.out.println("Hash for third block: "+thirdBlock.hash);
		System.out.println("Started mining...");
		block.get(2).mineCoin(diff);
		
		System.out.println("Is block-chain valid: "+isValidChain());
		
		String blockjson = new GsonBuilder().setPrettyPrinting().create().toJson(block);
		System.out.println("The chain: ");
		System.out.println(blockjson);
	}
	
	public static boolean isValidChain() {
		Block presBlock;
		Block prevBlock;
		String targetHash=new String(new char[diff]).replace('\0', '0');
		
		
		for(int i=1;i<block.size();i++) {
			presBlock=block.get(i);
			prevBlock=block.get(i-1);
			
			if(!presBlock.hash.equals(presBlock.calcHash())) {
				System.out.println("Present block is not equal to the previous one!");
				return false;
			}
			
			if(!prevBlock.hash.equals(presBlock.prevHash)) {
				System.out.println("Previous block is not equal to the current one!");
				return false;
			}
			if(!presBlock.hash.substring(0,diff).equals(targetHash)) {
				System.out.println("Block is not mined!");
				return false;
			}
		}
		return true;
	}
	
}
