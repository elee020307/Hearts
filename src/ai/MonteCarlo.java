package ai;

import game.*;
import player.RandomPlayer;

import java.util.*;

public class MonteCarlo {

    public static final int DEFAULT_SEARCH_DEPTH = 5;
    public static final int DEFAULT_NUMBER_SIMULATIONS = 22;

    /**
     * Simulates many games and picks the move that won most of them by distributing the remaining cards randomly and
     * then building a game tree and running Minimax on the tree up to the specified depth
     * @param info The game info for the current state of the game
     * @param currentPlayer the id of player currently making the move
     * @param fn the heuristic function to evaluate leaf nodes
     * @param exp the expansion function that determines whether to expand a node or not
     * @param times the number of times to simulate
     * @return The best move from the simulated games
     */
    public static Move Simulate(SealedGameInfo info, int currentPlayer, HeuristicFunction fn, ExpansionFunction exp, int times) {
        if (times < 1) return null;
        Map<Move,Integer> map = new HashMap<>();
        Move max = null;
        for (int i = 0; i < times; i++) {
            GameInfo g = GameInfo.DistributeRandomly(info, getPlayers(), currentPlayer);
            GameTree tree = new GameTree(new GameTreeInfo(g),null);
            Move m = tree.BestMove(fn,exp);
            if (!map.containsKey(m)) map.put(m,0);
            map.put(m,map.get(m)+1);
            if (max == null || map.get(max) < map.get(m)) max = m;
        }
        return max;
    }

    /**
     * Creates an array of random players to use in the card distribution
     * @return an array of four players
     */
    private static IPlayer[] getPlayers() {
        IPlayer[] players = new IPlayer[4];
        for (int i = 0; i < players.length; i++) {
            players[i] = new RandomPlayer();
        }
        return players;
    }


}
