/*
	Title:  A* Pathfinding Application
	Author: Matthew Boyette
	Date:   10/25/2013
	
	This application is a demonstration of a two dimensional A* pathfinding routine.
*/

import api.gui.*;
import api.util.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

public class PathfinderDemo
{
	private boolean				debugMode	= false;
	private ApplicationWindow	window		= null;
	
	public static final void main(final String[] args)
	{
		new PathfinderDemo(args);
	}
	
	public PathfinderDemo(final String[] args)
	{
		this.setDebugging(Support.promptDebugMode(this.getWindow()));
		
		if (args.length > 0)
		{
			final Games.AStarShortestPath2D shortestPath = new Games.AStarShortestPath2D(this.isDebugging(), args[0]);
			
			if (this.isDebugging())
			{
				System.out.println("Start point: " + shortestPath.getStartPoint().toString());
				System.out.println("Goal point: " + shortestPath.getGoalPoint().toString());
				
				System.out.println("\nVertices (" + shortestPath.getVertices().size() + "):");
				
				for (int j = 0; j < shortestPath.getVertices().size(); j++)
				{
					System.out.println(shortestPath.getVertices().get(j));
				}
				
				System.out.println("\nEdges (" + shortestPath.getEdges().size() + "):");
				
				for (int j = 0; j < shortestPath.getEdges().size(); j++)
				{
					System.out.println(shortestPath.getEdges().get(j));
				}
				
				if (shortestPath.getGoalPoint().isWithinLineOfSightOf(shortestPath.getStartPoint(), shortestPath.getEdges()))
				{
					System.out.println("\nThe goal point is within line of sight of the start point.");
				}
				else
				{
					System.out.println("\nThe goal point is not within line of sight of the start point.");
				}
				
				System.out.println("\nVertices adjacent to start point (" + shortestPath.getStartPoint().getNodesWithinLOS().size() + "):");
				
				for (int j = 0; j < shortestPath.getStartPoint().getNodesWithinLOS().size(); j++)
				{
					System.out.println(shortestPath.getStartPoint().getNodesWithinLOS().get(j));
				}
				
				System.out.println("\nVertices adjacent to goal point (" + shortestPath.getGoalPoint().getNodesWithinLOS().size() + "):");
				
				for (int j = 0; j < shortestPath.getGoalPoint().getNodesWithinLOS().size(); j++)
				{
					System.out.println(shortestPath.getGoalPoint().getNodesWithinLOS().get(j));
				}
			}
			
			if (!shortestPath.getSolution().isEmpty())
			{
				System.out.println("\nSolution:");
				
				for (int i = 0; i < shortestPath.getSolution().size(); i++)
				{
					System.out.println(shortestPath.getSolution().get(i).toString() + "\n");
				}
			}
			
			// Define a self-contained interface construction event handler.
			EventHandler myDrawGUI = new EventHandler(this)
			{
				private static final long serialVersionUID = 1L;

				public final void run(final Object... arguments) throws IllegalArgumentException
				{
					if (arguments.length <= 0)
					{
						throw new IllegalArgumentException("myDrawGUI Error : incorrect number of arguments.");
					}
					else if (!(arguments[0] instanceof ApplicationWindow))
					{
						throw new IllegalArgumentException("myDrawGUI Error : argument[0] is of incorrect type.");
					}

					ApplicationWindow                    window      = (ApplicationWindow)arguments[0];
					Container                            contentPane = window.getContentPane();
					Games.AStarShortestPath2D.MapDisplay mapDisplay  = new Games.AStarShortestPath2D.MapDisplay(Color.WHITE, Color.BLACK, shortestPath, 10.0);

					contentPane.setLayout(new BorderLayout());
					contentPane.add(mapDisplay, BorderLayout.CENTER);
					mapDisplay.repaint();
				}
			};
			
			this.setWindow(new ApplicationWindow(null, "A* Pathfinding Application", new Dimension(425, 250), this.isDebugging(), false, 
				null, myDrawGUI));
			this.getWindow().setIconImageByResourceName("icon.png");
		}
	}
	
	public final ApplicationWindow getWindow()
	{
		return this.window;
	}
	
	public final boolean isDebugging()
	{
		return this.debugMode;
	}
	
	protected final void setDebugging(final boolean debugMode)
	{
		this.debugMode = debugMode;
	}
	
	protected final void setWindow(final ApplicationWindow window)
	{
		this.window = window;
	}
}