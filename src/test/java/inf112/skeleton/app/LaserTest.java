package inf112.skeleton.app;

import inf112.skeleton.app.net.NetworkSettings;
import inf112.skeleton.app.sprites.Player;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LaserTest {
    private Board board;
    private Player player1;
    private int player1PcBefore;
    private Player player2;
    private int player2PcBefore;
    private int player4PcBefore;
    private Player player4;
    private Game game;
    private HashMap<Player, Integer> pcBefore;


    public void setUpBoard(int boardNum) {
        NetworkSettings networkSettings = new NetworkSettings("test", "localhost", 2, 2);
        game = new Game(networkSettings, 4);
        board = new Board();
        board.readBoard(boardNum);
        game.board = board;
        game.setBoardSize(board.getSize());
        player1 = board.getPlayerList().get(0);
        player1PcBefore = player1.getPc();
        player2 = board.getPlayerList().get(1);
        player2PcBefore = player2.getPc();
        player4 = board.getPlayerList().get(3);
        player4PcBefore = player4.getPc();
        pcBefore = makePlayerPcHashMap();

    }

    public HashMap<Player, Integer> makePlayerPcHashMap() {
        HashMap<Player, Integer> hashMap = new HashMap<>();
        for (Player player : board.getPlayerList()) {
            hashMap.put(player, player.getPc());
        }
        return hashMap;
    }

    @Test
    public void noPlayerTakesDamage() {
        setUpBoard(100);
        game.fireLasers(board.getLaserList());
        assertEquals(pcBefore, makePlayerPcHashMap());
    }

    @Test
    public void player1TakesDamageOtherPlayersDoesNot() {
        setUpBoard(101); //Testboard for this problem
        game.fireLasers(board.getLaserList());
        //Make a hashmap with expected pc values based on pc values before
        HashMap<Player, Integer> expectedPc = pcBefore;
        // Player 1 is expected to take 1 damage so subtract 1 from player1s pc value
        expectedPc.put(player1, expectedPc.get(player1)-1);
        // Make new hashmap with current pc values
        HashMap<Player, Integer> pcAfter = makePlayerPcHashMap();
        assertEquals(expectedPc, pcAfter, "Some player does not have the pc that was expected after firing lasers on this board");
        }

    @Test
    public void player1DoesNotTakeDamageThroughPlayer2() {
        setUpBoard(102);
        game.fireLasers(board.getLaserList());
        //Player 2 will take damage and stop the laser so it shouldn't hit player 1
        assertEquals(player2PcBefore-1, player2.getPc(), "Laser did not hit or did too much damage to player2");
        assertEquals(player1PcBefore, player1.getPc(), "Player 1 took damage somehow when they shouldn't");
    }
    @Test
    public void wallPreventsDamage() {
        setUpBoard(103);
        game.fireLasers(board.getLaserList());
        assertEquals(player1PcBefore, player1.getPc(), "Player 1 took damage through the wall!");

    }
    @Test
    public void moreThanOneLaserFiresInOneMethodCall() {
        setUpBoard(104);
        game.fireLasers(board.getLaserList());
        assertEquals(player1PcBefore -1, player1.getPc(),"Laser 1 didn't fire"); //Note it will break here and you don't know if laser 2 fired
        assertEquals(player4PcBefore -1, player4.getPc(), "Laser 2 didn't fire");
    }

    @Test
    public void allFourLasersFireAndDamage() {
        setUpBoard(105);
        game.fireLasers(board.getLaserList());
        assertEquals(player1PcBefore -4, player1.getPc());
    }

    }
