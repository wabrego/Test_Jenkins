package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerResult;



public class UnitaryTest {
	
	private Player player1,player2;
	private TicTacToeGame juego;
	@Before
	public void setUp() {
		player1 = new Player(10, "1", "Ernesto");
		player2 = new Player(11, "2", "Guille");
		juego = new TicTacToeGame();
	}
	@Test
	public void PrimerJugadorGana() {
		
		//Given
		juego.addPlayer(player1);
		juego.addPlayer(player2);
		int[] array={0,1,2};
		WinnerResult result;
		
		
		//When 
		juego.mark(0);
		juego.mark(5);
		juego.mark(1);
		juego.mark(7);
		juego.mark(2);
		result = juego.checkWinner();
		
		//Then
		assertArrayEquals(array,result.pos);
		assertEquals(juego.checkDraw(),false);
	}
	
	@Test
	public void SegundoJugadorGana() {
		
		//Given
		juego.addPlayer(player1);
		juego.addPlayer(player2);
		int[] array={3,4,5};
		WinnerResult result;
		
		
		//When 
		juego.mark(0);
		juego.mark(3);
		juego.mark(1);
		juego.mark(4);
		juego.mark(7);
		juego.mark(5);
		result = juego.checkWinner();
		
		//Then
		assertArrayEquals(array,result.pos);
		assertEquals(juego.checkDraw(),false);
	}
	
	@Test
	public void empate() {
		
		//Given
		juego.addPlayer(player1);
		juego.addPlayer(player2);
		WinnerResult result;
		
		
		//When 
		juego.mark(0);
		juego.mark(1);
		juego.mark(4);
		juego.mark(2);
		juego.mark(5);
		juego.mark(3);
		juego.mark(6);
		juego.mark(8);
		juego.mark(7);
		result = juego.checkWinner();
		
		//Then
		assertEquals(false,result.win);
		assertEquals(juego.checkDraw(),true);
	}
}
