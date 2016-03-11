/*
 * Title: A* Pathfinding Application
 * Author: Matthew Boyette
 * Date: 10/25/2013
 * 
 * This application is a demonstration of a two dimensional A* pathfinding routine.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import api.gui.swing.ApplicationWindow;
import api.util.EventHandler;
import api.util.Support;
import api.util.games.AStarShortestPath2D;
import api.util.stdlib.StdOut;

public class PathfinderDemo
{
    public static final void main(final String[] args)
    {
        new PathfinderDemo(args);
    }
    
    private boolean           debugMode = false;
    private ApplicationWindow window    = null;
    
    public PathfinderDemo(final String[] args)
    {
        this.setDebugging(Support.promptDebugMode(this.getWindow()));
        
        if (args.length > 0)
        {
            final AStarShortestPath2D shortestPath = new AStarShortestPath2D(this.isDebugging(), args[0]);
            
            if (this.isDebugging())
            {
                StdOut.println("Start point: " + shortestPath.getStartPoint().toString());
                StdOut.println("Goal point: " + shortestPath.getGoalPoint().toString());
                
                StdOut.println("\nVertices (" + shortestPath.getVertices().size() + "):");
                
                for (int j = 0; j < shortestPath.getVertices().size(); j++)
                {
                    StdOut.println(shortestPath.getVertices().get(j));
                }
                
                StdOut.println("\nEdges (" + shortestPath.getEdges().size() + "):");
                
                for (int j = 0; j < shortestPath.getEdges().size(); j++)
                {
                    StdOut.println(shortestPath.getEdges().get(j));
                }
                
                if (shortestPath.getGoalPoint().isWithinLineOfSightOf(shortestPath.getStartPoint(), shortestPath.getEdges()))
                {
                    StdOut.println("\nThe goal point is within line of sight of the start point.");
                }
                else
                {
                    StdOut.println("\nThe goal point is not within line of sight of the start point.");
                }
                
                StdOut.println("\nVertices adjacent to start point (" + shortestPath.getStartPoint().getNodesWithinLOS().size() + "):");
                
                for (int j = 0; j < shortestPath.getStartPoint().getNodesWithinLOS().size(); j++)
                {
                    StdOut.println(shortestPath.getStartPoint().getNodesWithinLOS().get(j));
                }
                
                StdOut.println("\nVertices adjacent to goal point (" + shortestPath.getGoalPoint().getNodesWithinLOS().size() + "):");
                
                for (int j = 0; j < shortestPath.getGoalPoint().getNodesWithinLOS().size(); j++)
                {
                    StdOut.println(shortestPath.getGoalPoint().getNodesWithinLOS().get(j));
                }
            }
            
            if (!shortestPath.getSolution().isEmpty())
            {
                StdOut.println("\nSolution:");
                
                for (int i = 0; i < shortestPath.getSolution().size(); i++)
                {
                    StdOut.println(shortestPath.getSolution().get(i).toString() + "\n");
                }
            }
            
            // Define a self-contained interface construction event handler.
            // @formatter:off
            EventHandler<PathfinderDemo> myDrawGUI = new EventHandler<PathfinderDemo>(this)
            {
                private final static long serialVersionUID = 1L;

                @Override
                public final void run(final ApplicationWindow window)
                {
                    Container contentPane = window.getContentPane();
                    AStarShortestPath2D.MapDisplay mapDisplay = new AStarShortestPath2D.MapDisplay(Color.WHITE, Color.BLACK, shortestPath, 10.0);

                    contentPane.setLayout(new BorderLayout());
                    contentPane.add(mapDisplay, BorderLayout.CENTER);
                    mapDisplay.repaint();
                }
            };

            this.setWindow(new ApplicationWindow(null,
                "A* Pathfinding Application",
                new Dimension(425, 250),
                this.isDebugging(),
                false,
                null,
                myDrawGUI));
            this.getWindow().setIconImageByResourceName("icon.png");
            // @formatter:on
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