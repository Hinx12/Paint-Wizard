package paintWizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PaintWizard {
	
	private Room room;
	private ArrayList<Paint> paints;
	private Scanner in;

	
	public PaintWizard() {
		
		paints = new ArrayList<Paint>();
		paints.add(new Paint("CheapoMax", 19.99, 20, 10));
		paints.add(new Paint("AverageJoes", 17.99, 15, 11));
		paints.add(new Paint("DuluxourousPaints", 25.00, 10, 20));	
	}

	public static void main(String[] args) {
		
		PaintWizard paintWizard = new PaintWizard();
		paintWizard.introGame();
		paintWizard.startGame();
	}
	
	public void introGame() {
		
		System.out.println("Please eneter the size of your room");
		double input = 0; 
		in = new Scanner(System.in);
		
		try { 
			input = in.nextDouble();
		
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		
		room = new Room(input);
	}
	

	public void startGame() {
		
		System.out.println("\nThe size of your room is " + room.getSize() + "m2");
		
		System.out.println("\nThe following paints are available to paint your room\n");
		
		printPaints();
		
		checkWaste();
		
		checkCheapest();
	}
	
	public void checkWaste() {
		
		System.out.println("\nChecking to find out which paint wastes the least\n");
		
		for (Paint paint : paints) {
			
			double exactPaintNeeded = room.getSize() / (paint.getCoverage());
			double totalTinsNeeded = Math.ceil(exactPaintNeeded);
			paint.setWaste(totalTinsNeeded - exactPaintNeeded);
			System.out.println(paint.getName() + " wastes " + paint.getWaste());
		}
		
		Paint minWaste = Collections.min(paints, Comparator.comparing(c -> c.getWaste()));
		
		System.out.println("\nTherefore, " +  minWaste.getName() + " wastes the least amount of paint with " + minWaste.getWaste() + "\n");
		
	}
	
	public void printPaints() {
		
		for (Paint paint : paints) {
			System.out.println(paint);
		}
	}
	
	public void checkCheapest() {
		
		double exactPaintNeeded;
		double priceForPaint;
		
		for (Paint paint : paints) {
			
			exactPaintNeeded = room.getSize() / (paint.getCoverage());
			priceForPaint = exactPaintNeeded * paint.getPrice();
			paint.setCostForRoom(priceForPaint);
			System.out.println(paint.getName() + " costs £" + paint.getCostForRoom() + " to paint your room");
		}
		
		Paint cheapest = Collections.min(paints, Comparator.comparing(c -> c.getCostForRoom()));
		Paint cheapestFor1Tin = Collections.min(paints, Comparator.comparing(c -> c.getPrice()));
		
		System.out.println("\nThe cheapest method to paint your room\n");
		
		double divider = (room.getSize() / cheapest.getCoverage());
		double modulus = (room.getSize() / cheapest.getCoverage());
		
		
		
		// If divider is 1 or less - the best option is to buy the cheapest tin of paint.
		if (divider <= 1) {    
			System.out.println(cheapestFor1Tin.getName() + " is the cheapest paint to buy for your room");
			System.out.println("You need to buy 1 tin of paint, costing £" + cheapestFor1Tin.getPrice());
			System.out.println("Therefore the total cost is £" + cheapestFor1Tin.getPrice());
		
		// if the room can be painted with an exact number of tins
		} else if (modulus == 0) {
			System.out.println(cheapest.getName() + " is the cheapest paint to buy for you room");
			System.out.println("You need to buy " + divider + " tins of paint, costing £" + cheapest.getPrice());
			System.out.println("Thefore the total cost is £" + (divider * cheapest.getPrice()));
		
		// if it can't, the possible cheapest paint is rounded down - then the cheapest paint per tin is added (Average Joes) (Combo)
		} else {
			int roundedDownTins = (int) divider;
			double exactPrice = (roundedDownTins * cheapest.getPrice()) + cheapestFor1Tin.getPrice();
			System.out.println(cheapest.getName() + " is the cheapest paint to buy for your room");
			System.out.println("You need to buy " + roundedDownTins + " tins of paints, costing £ " + cheapest.getPrice());
			System.out.println("There the best plain is to buy " + roundedDownTins + " tins of paint, costing £ " + (roundedDownTins * cheapest.getPrice()));
			System.out.println("Then in additon, you should buy 1 tin of " + cheapestFor1Tin.getName() + " costing " + cheapestFor1Tin.getPrice());
			System.out.println("There for the total cost is £" + exactPrice);
		}
	}
}
