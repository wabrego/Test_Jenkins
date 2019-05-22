package es.codeurjc.ais.tictactoe;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerResult;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;


public class DoblesTest {
	private TicTacToeGame juego ;
	private Player player1,player2;
	Connection  connection1;
	Connection  connection2;
	@Before
	public void setUp() {
		connection1 = mock(Connection.class);
		connection2 = mock(Connection.class);
		juego = new TicTacToeGame();
		juego.addConnection(connection1);
		juego.addConnection(connection2);
		player1 = new Player(10, "1", "Ernesto");
		player2 = new Player(11, "2", "Guille");
		juego.addPlayer(player1);
		juego.addPlayer(player2);
	}
	@Test
	public void GanaPlayer1() {

		verify(connection1,times(2)).sendEvent(
	            eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		verify(connection2,times(2)).sendEvent(
	            eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		
		juego.mark(0);
		verify(connection1).sendEvent(eq(EventType.SET_TURN),eq(player1));
		reset(connection1);
		
		juego.mark(5);
		verify(connection2).sendEvent(eq(EventType.SET_TURN),eq(player2));
		reset(connection2);
		
		juego.mark(1);
		verify(connection1).sendEvent(eq(EventType.SET_TURN),eq(player1));
		reset(connection1);
		
		juego.mark(7);
		verify(connection2).sendEvent(eq(EventType.SET_TURN),eq(player2));
		reset(connection2);
		
		juego.mark(2);
		ArgumentCaptor<WinnerValue> argument =
				ArgumentCaptor.forClass(WinnerValue.class);
				verify(connection1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
				WinnerValue event = argument.getValue();
				assertEquals(event.player,player1);
			
	}
	@Test
	public void GanaPlayer2() {

		verify(connection1,times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		verify(connection2,times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		
		juego.mark(0);
		verify(connection1).sendEvent(eq(EventType.SET_TURN),eq(player1));
		reset(connection1);
		
		juego.mark(3);
		verify(connection2).sendEvent(eq(EventType.SET_TURN),eq(player2));
		reset(connection2);
		
		juego.mark(1);
		verify(connection1).sendEvent(eq(EventType.SET_TURN),eq(player1));
		reset(connection1);
		
		juego.mark(4);
		verify(connection2).sendEvent(eq(EventType.SET_TURN),eq(player2));
		reset(connection2);
		
		juego.mark(7);
		verify(connection1).sendEvent(eq(EventType.SET_TURN),eq(player1));
		reset(connection1);
		
		juego.mark(5);
		ArgumentCaptor<WinnerValue> argument =
				ArgumentCaptor.forClass(WinnerValue.class);
				verify(connection2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
				WinnerValue event = argument.getValue();
				assertEquals(event.player,player2);
			
	}
	@Test
	public void Empate() {

		verify(connection1,times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		verify(connection2,times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		
		juego.mark(0);
		verify(connection1).sendEvent(eq(EventType.SET_TURN),eq(player1));
		reset(connection1);
		
		juego.mark(1);
		verify(connection2).sendEvent(eq(EventType.SET_TURN),eq(player2));
		reset(connection2);
		
		juego.mark(4);
		verify(connection1).sendEvent(eq(EventType.SET_TURN),eq(player1));
		reset(connection1);
		
		juego.mark(2);
		verify(connection2).sendEvent(eq(EventType.SET_TURN),eq(player2));
		reset(connection2);
		
		juego.mark(5);
		verify(connection1).sendEvent(eq(EventType.SET_TURN),eq(player1));
		reset(connection1);
		
		juego.mark(3);
		verify(connection2).sendEvent(eq(EventType.SET_TURN),eq(player2));
		reset(connection2);
		
		juego.mark(6);
		verify(connection1).sendEvent(eq(EventType.SET_TURN),eq(player1));
		reset(connection1);
		
		juego.mark(8);
		verify(connection2).sendEvent(eq(EventType.SET_TURN),eq(player2));
		reset(connection2);
		
		juego.mark(7);
		ArgumentCaptor<WinnerValue> argument =
				ArgumentCaptor.forClass(WinnerValue.class);
				verify(connection1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
				WinnerValue event = argument.getValue();
				assertEquals(event,null);
			
	}	
}
