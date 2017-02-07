package hw1;

import java.util.Arrays;

public class CustomerServiceRepScores 
{
	private int repQuantity;
	private int numberOfPossibleScores;
	private int[][] scores;
	private double[][] tracker; //This array will be used to track the reps average score for the last 20 scores
	private int[][] scoreTracker;
	

	public CustomerServiceRepScores(int repQuantity, int scoreQuantity)
	{
		this.repQuantity = repQuantity;
		this.numberOfPossibleScores = scoreQuantity;
		this.scores = new int[this.repQuantity][this.numberOfPossibleScores];
		this.tracker = new double[this.repQuantity][3];
		this.scoreTracker = new int[this.repQuantity][2];
		//Initialize all score counts to zero
		for (int i=0; i<this.scores.length; i++)
		{
			Arrays.fill(this.scores[i], 0);
		}
		//Initialize the Tracker Array
		for (int i=0; i<this.tracker.length; i++)
		{
			Arrays.fill(this.tracker[i],i++);
		}
		//Initialize the ScoreTracker Array
		for (int i=0; i<this.scoreTracker.length; i++)
		{
			Arrays.fill(this.scoreTracker[i],i++);
		}
	}
	/*-----Example of initialized CustomerServiceRepScores-----*/
	/*
	 * new CustomerServiceRepScores(3,2) =
	 * 
	 * [0, 0] - Scores for ALL Reps for first score value
	 * [0, 0] - scores for ALL Reps for second score value
	 * [0, 0]
	 * --------------------------------------------
	 * scores[0,1] = 1; rep 0 score for first score value changed to 1
	 * [0, 1] 
	 * [0, 0]
	 * [0, 0]  
	 * 
	 * scores[1,0] = 2; rep 1 score for second value changes to 2 
	 * [0, 0] 
	 * [2, 0] 
	 * [0, 0]  
	 * */
	/*-----End of Example-----*/

	/*-----Tracker Example-----*/
	/*
	 * if there are 5 reps
	 * Tracker =
	 * Rep 0 - [0,0,0] //[Cumulative Score, Average Score, Counter]
	 * Rep 1 - [0,0,0] 
	 * Rep 2 - [0,0,0]
	 * Rep 3 - [0,0.0]
	 * Rep 4 - [0,0,0]
	 * 
	 * */
	/*-----End of Tracker Example-----*/

	public static void main(String[] args) {
		CustomerServiceRepScores csrs = new CustomerServiceRepScores(100, 5);
		csrs.addNewScore(0, 1);
		csrs.addNewScore(0, 1);
		csrs.addNewScore(0, 4);
		int[] cumScores = csrs.getCumulativeScoresForRep(0);
		for (int score : cumScores)
		{
			System.out.println(score);
		}
		double[][] tracker = csrs.tracker;
		System.out.println(tracker[0][0]);
		System.out.println(tracker[0][1]);
		System.out.println(tracker[0][2]);
		System.out.println();
	}
	/**
	 * Method to add a new score for a rep
	 * @param repID the rep who receives this score
	 * @param score the score received
	 * */
	public void addNewScore(int repID, int score)
	{
		this.scores[repID][score-1] += 1;
		updateTracker(repID, score);
	}
	/**
	 * method to get the array of scores for a given rep
	 * @param repID the ID of the rep
	 * */
	public int[] getCumulativeScoresForRep(int repID)
	{
		return Arrays.copyOf(this.scores[repID], this.scores[repID].length);
	}
	public int[][] getScores()
	{
		return this.scores;
	}
	/**
	 * Method to update the tracker when a new score is added
	 * */
	private void updateTracker(int repID, int score)
	{
		int[] currentScores = getCumulativeScoresForRep(repID);
		int currentScore = 0;
		for (int i=0; i<currentScores.length; i++)
		{
			currentScore += currentScores[i] * (i+1);
		}
		this.tracker[repID][2]++; //increase counter
		this.tracker[repID][0] = currentScore; //Set the current score counter in the tracker to the current score
		double averageScore = currentScore / this.tracker[repID][2]; //compute the average
		this.tracker[repID][1] = averageScore;


		if ((currentScore / this.tracker[repID][2]) < 2.5)
		{
			System.out.println("Rep " + repID + "\'s running average has dropped to " + this.tracker[repID][1]);
		}
	}
	public double getCounter(int repID)
	{
		return this.tracker[repID][2];
	}
	private void incrementScoreTracker(int repID)
	{
		this.scoreTracker[repID][1]++;
	}

}
