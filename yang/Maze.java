package Maze;

import java.util.ArrayList;
import java.util.Stack;
import java.lang.*;
import javax.swing.JOptionPane;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }
    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
    	if((x >= maze.getNCols()) || (y >= maze.getNRows()) || x < 0 || y < 0) {
    		return false;
    	}
    	else if(maze.getColor(x, y) != NON_BACKGROUND ) {
    		return false;
    	}
    	else if((x == maze.getNCols()-1) &&(y == maze.getNRows()-1)) {
    		maze.recolor(x, y, PATH);
    		return true;
    	}
    	else {
			maze.recolor(x, y, PATH);
    		if(findMazePath(x,y+1)||findMazePath(x+1,y)||findMazePath(x,y-1)||findMazePath(x-1,y)) {
    			return true;
    		}
    		else {
    			maze.recolor(x, y, TEMPORARY);
    			return false;
    		}
    	}
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
    		ArrayList<ArrayList<PairInt>> result = new ArrayList<ArrayList<PairInt>>();
    		Stack<PairInt> trace = new Stack<>();
    		findMazePathStackBased(0,0,result, trace);
    		if(result.size()==0)
    		{
    			result.add(new ArrayList<PairInt>());
    		}
    		return result;
    }
    
    private void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	if((x >= maze.getNCols()) || (y >= maze.getNRows()) || x < 0 || y < 0) {
    		return;
    	}
    	else if(maze.getColor(x, y) != NON_BACKGROUND ) {
    		return;
    	}
    	else if((x == maze.getNCols()-1) &&(y == maze.getNRows()-1)) {
    		trace.push(new PairInt(x,y));
    		result.add(new ArrayList<PairInt>(trace));
    		trace.pop();
    	}
    	else {
    		trace.push(new PairInt(x,y));
    		maze.recolor(x,y, PATH);
    		findMazePathStackBased(x,y+1,result,trace);
    		findMazePathStackBased(x+1,y,result,trace);
    		findMazePathStackBased(x,y-1,result,trace);
    		findMazePathStackBased(x-1,y,result,trace);
    		trace.pop();
    		maze.recolor(x,y, NON_BACKGROUND);
    	}
	}

	// ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
    	ArrayList <ArrayList <PairInt>> result = findAllMazePaths(x, y);
    	int d=0;
    	int min = result.get(0).size();
    	for(int i=1;i<result.size();i++)
    	{
    		if(result.get(i).size()<min)
    		{	
    			d=i;
    		}
    	}
    	//System.out.println(result.get(d));
    	return result.get(d);
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
