package Creamer;
import java.util.concurrent.ThreadLocalRandom;
public class MakeSomeCream {
	public static void main(String[] args) {
		Creamy cream = new Creamy();
		cream.creampercent = ThreadLocalRandom.current().nextInt(0, 101);
		if(cream.creampercent > 90) {
			cream.creaminess = Creamy.CreamLevel.creamiest;
		}
		else if(cream.creampercent > 50) {
			cream.creaminess = Creamy.CreamLevel.creamier;
		}
		else {
			cream.creaminess = Creamy.CreamLevel.creamy;
		}
		System.out.println("You made " + cream.creaminess + " cream by mixing it " + cream.creampercent + "% of the way!");
	}
}