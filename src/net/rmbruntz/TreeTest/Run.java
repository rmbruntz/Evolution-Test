package net.rmbruntz.TreeTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Run {
	private final static int CHOICES = 40;
	private final static int MAX_CHOOSERS = 5;
	private static boolean[] correct;
	private final static double CHANCE = 0.01;
	
	public static void main(String[] args) {
		correct = new boolean[CHOICES];
		for (int i = 0; i < CHOICES; i++) {
			correct[i] = (Math.random() > 0.5);
		}
		List<Chooser> choosers = new ArrayList<Chooser>();
		for (int i = 0; i < MAX_CHOOSERS; i++) {
			choosers.add(new Chooser(CHOICES));
		}
		
		int numCorrect;
		int round = 0;
		int roundAvg = CHOICES/2;
		
		do {
			int total = 0;
			round++;
			System.out.println("Round " + round + ":");
			numCorrect = 0;
			int currentAccuracy;
			System.out.print("Accuracies: ");
			for (int i = 0; i < MAX_CHOOSERS; i++) {
				currentAccuracy = choosers.get(i).getAccuracy(correct);
				if (currentAccuracy == CHOICES) numCorrect++;
				System.out.print(currentAccuracy + " ");
				for (int j = 0; j < Math.pow(5, currentAccuracy - roundAvg); j++) {
					choosers.add(choosers.get(i).generateNew(CHANCE));
				}
				total += currentAccuracy;
			}
			System.out.println("\nCorrect: " + numCorrect + "\n");
			Collections.shuffle(choosers);
			choosers = choosers.subList(0, MAX_CHOOSERS);
			roundAvg = total/MAX_CHOOSERS;
			total = 0;
		} while (numCorrect < MAX_CHOOSERS);
	}
}
