package net.rmbruntz.TreeTest;

public class Chooser {

	private final int choiceCount;
	private boolean[] choices;
	
	public Chooser(int choiceCount) {
		this.choiceCount = choiceCount;
		this.choices = new boolean[choiceCount];
		for (int i = 0; i < choiceCount; i++) {
			choices[i] = (Math.random() > 0.5);
		}
	}
	
	public Chooser generateNew(double chance) {
		Chooser newChooser = new Chooser(choiceCount);
		for (int i = 0; i < choiceCount; i++) {
			if (chance > Math.random()) {
				newChooser.choices[i] = !this.choices[i];
			} else {
				newChooser.choices[i] = this.choices[i];
			}
		}
		return newChooser;
	}
	
	public int getAccuracy(boolean[] correct) {
		int right = 0;
		for (int i = 0; i < choiceCount; i++) {
			if (choices[i] == correct[i]) right++;
		}
		return right;
	}
}
