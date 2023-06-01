package player;//
//
//  @ Project : Vak Varázslók Földje
//  @ File Name : player.GameTable.java
//  @ Date : 2022. 03. 22.
//  @ Author : Richly Bálint yo685y
//
//


import code.CrazyVirusCode;
import code.ForgetVirusCode;
import code.FreezeVirusCode;
import code.VaccineCode;
import equipment.Axe;
import equipment.Glove;
import equipment.Jacket;
import equipment.Sack;
import field.Bunker;
import field.Field;
import field.Laboratory;
import field.Storage;
import game.FieldV;
import game.PlayerV;
import game.WindowV;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameTable extends AbstractTableModel {
	private Boolean winGame;

	public LinkedList<Virologist> getPlayers() {
		return players;
	}

	private LinkedList<Virologist> players;

	private List<Field> board;

	private final Integer width = 10;
	private final Integer height = 10;


	/**
	 * Game table konstruktor
	 */
	public GameTable(List<String> playerNames){
		winGame = false;
		players = new LinkedList<>();

		for (String n : playerNames) {
			players.add(new Virologist(n));
		}

		generateMap();
	}

	public Virologist getCurrentPlayer() {
		return players.getFirst();
	}

	public void stepPlayers() {
		players.addLast(players.pollFirst());		// step fifo
		fire();
	}

	public void generateRandomMap() {
		int n = 100;
		board = new ArrayList<>();

		for (Integer w = 0; w < width; w++) {
			for (Integer h = 0; h < height; h++) {
				board.add(null);
			}
		}

		// Speciális mezők feltöltése

		int remaining = 4;
		while (remaining > 0) {		// laboratoriumok
			Random rnd = new Random();
			int fieldId = rnd.nextInt(100);

			if (board.get(fieldId) == null) {
				switch (remaining) {        // all different types
					case 1:
						board.set(fieldId, new Laboratory(new CrazyVirusCode()));
						board.get(fieldId).setFieldNumber(fieldId);
						break;
					case 2:
						board.set(fieldId, new Laboratory(new FreezeVirusCode()));
						board.get(fieldId).setFieldNumber(fieldId);
						break;
					case 3:
						board.set(fieldId, new Laboratory(new ForgetVirusCode()));
						board.get(fieldId).setFieldNumber(fieldId);
						break;
					case 4 :
						board.set(fieldId, new Laboratory(new VaccineCode()));
						board.get(fieldId).setFieldNumber(fieldId);
						break;
				}
				board.get(fieldId).view = new FieldV(fieldId / 10, fieldId % 10);
				board.get(fieldId).view.c = Color.white;
				remaining--;
			}
		}

		remaining = 4;
		while (remaining > 0) {		// bunkerek
			Random rnd = new Random();
			int fieldId = rnd.nextInt(100);

			if (board.get(fieldId) == null) {
				switch (remaining) {        // all different types
					case 1 :
						board.set(fieldId, new Bunker(new Jacket()));
						board.get(fieldId).setFieldNumber(fieldId);
						board.get(fieldId).view = new FieldV(fieldId / 10,fieldId % 10);
						board.get(fieldId).view.setJacket();
						break;
					case 2 :
						board.set(fieldId, new Bunker(new Glove()));
						board.get(fieldId).setFieldNumber(fieldId);
						board.get(fieldId).view = new FieldV(fieldId / 10,fieldId % 10);
						board.get(fieldId).view.setGlove();
						break;
					case 3:
						board.set(fieldId, new Bunker(new Axe()));
						board.get(fieldId).setFieldNumber(fieldId);
						board.get(fieldId).view = new FieldV(fieldId / 10,fieldId % 10);
						board.get(fieldId).view.setAxe();
						break;
					case 4 :
						board.set(fieldId, new Bunker(new Sack()));
						board.get(fieldId).setFieldNumber(fieldId);
						board.get(fieldId).view = new FieldV(fieldId / 10,fieldId % 10);
						board.get(fieldId).view.setSack();
						break;
				}

				board.get(fieldId).view.c = Color.green;
				remaining--;
			}
		}

		remaining = 4;
		while (remaining > 0) {		// raktar
			Random rnd = new Random();
			int fieldId = rnd.nextInt(100);

			if (board.get(fieldId) == null) {
				board.set(fieldId, new Storage());
				board.get(fieldId).setFieldNumber(fieldId);
				board.get(fieldId).view = new FieldV(fieldId / 10,fieldId % 10);
				board.get(fieldId).view.c = Color.blue;
				remaining--;
			}
		}

		// maradék mező feltöltése
		for (int i = 0; i < n; i++) {
			if (board.get(i) == null) {
				board.set(i, new Field());
				board.get(i).setFieldNumber(i);
				board.get(i).view = new FieldV(i / 10, i % 10);
				board.get(i).view.c = Color.pink;
			}
		}


		// szomszedok
		for (int i = 0; i < n; i++) {
			List<Field> neighbours = new ArrayList<>();
			try {
				neighbours.add(board.get(i-1));	// elotte
			} catch (Exception e) {}
			try {
				neighbours.add(board.get(i-10));	// felette
			} catch (Exception e) {}
			try {
				neighbours.add(board.get(i+1));	// utana
			} catch (Exception e) {}
			try {
				neighbours.add(board.get(i+10));	// alatta
			} catch (Exception e) {}

			board.get(i).setNeighbours(neighbours);
		}

		placePlayersRandomly();
	}

	public void generateMap() {
		int n = 100;
		board = new ArrayList<>();

		for (Integer w = 0; w < width; w++) {
			for (Integer h = 0; h < height; h++) {
				board.add(null);
			}
		}

		// Speciális mezők feltöltése

		int remaining = 4;
		int fieldId = 57;
		while (remaining > 0) {		// laboratoriumok
			if (board.get(fieldId) == null) {
				switch (remaining) {        // all different types
					case 1:
						board.set(fieldId, new Laboratory(new CrazyVirusCode()));
						board.get(fieldId).setFieldNumber(fieldId);
						break;
					case 2:
						board.set(fieldId, new Laboratory(new FreezeVirusCode()));
						board.get(fieldId).setFieldNumber(fieldId);
						break;
					case 3:
						board.set(fieldId, new Laboratory(new ForgetVirusCode()));
						board.get(fieldId).setFieldNumber(fieldId);
						break;
					case 4 :
						board.set(fieldId, new Laboratory(new VaccineCode()));
						board.get(fieldId).setFieldNumber(fieldId);
						break;
				}
				board.get(fieldId).view = new FieldV(fieldId / 10, fieldId % 10);
				board.get(fieldId).view.c = Color.white;
				remaining--;
				fieldId += 10;
			}
		}

		remaining = 4;
		fieldId = 56;
		while (remaining > 0) {		// bunkerek
			Random rnd = new Random();
			if (board.get(fieldId) == null) {
				switch (remaining) {        // all different types
					case 1 :
						board.set(fieldId, new Bunker(new Jacket()));
						board.get(fieldId).setFieldNumber(fieldId);
						board.get(fieldId).view = new FieldV(fieldId / 10,fieldId % 10);
						board.get(fieldId).view.setJacket();
						break;
					case 2 :
						board.set(fieldId, new Bunker(new Glove()));
						board.get(fieldId).setFieldNumber(fieldId);
						board.get(fieldId).view = new FieldV(fieldId / 10,fieldId % 10);
						board.get(fieldId).view.setGlove();
						break;
					case 3:
						board.set(fieldId, new Bunker(new Axe()));
						board.get(fieldId).setFieldNumber(fieldId);
						board.get(fieldId).view = new FieldV(fieldId / 10,fieldId % 10);
						board.get(fieldId).view.setAxe();
						break;
					case 4 :
						board.set(fieldId, new Bunker(new Sack()));
						board.get(fieldId).setFieldNumber(fieldId);
						board.get(fieldId).view = new FieldV(fieldId / 10,fieldId % 10);
						board.get(fieldId).view.setSack();
						break;
				}

				board.get(fieldId).view.c = Color.green;
				remaining--;
				fieldId += 10;
			}
		}

		remaining = 4;
		fieldId = 55;
		while (remaining > 0) {		// raktar
			if (board.get(fieldId) == null) {
				board.set(fieldId, new Storage());
				board.get(fieldId).setFieldNumber(fieldId);
				board.get(fieldId).view = new FieldV(fieldId / 10,fieldId % 10);
				board.get(fieldId).view.c = Color.blue;
				remaining--;
				fieldId += 10;
			}
		}

		// maradék mező feltöltése
		for (int i = 0; i < n; i++) {
			if (board.get(i) == null) {
				board.set(i, new Field());
				board.get(i).setFieldNumber(i);
				board.get(i).view = new FieldV(i / 10, i % 10);
				board.get(i).view.c = Color.pink;
			}
		}


		// szomszedok
		for (int i = 0; i < n; i++) {
			List<Field> neighbours = new ArrayList<>();
			try {
				neighbours.add(board.get(i-1));	// elotte
			} catch (Exception e) {}
			try {
				neighbours.add(board.get(i-10));	// felette
			} catch (Exception e) {}
			try {
				neighbours.add(board.get(i+1));	// utana
			} catch (Exception e) {}
			try {
				neighbours.add(board.get(i+10));	// alatta
			} catch (Exception e) {}

			board.get(i).setNeighbours(neighbours);
		}

		placePlayers();
	}

	private void placePlayersRandomly() {
		// jatekosok elhelyezese a palyan
		for (Virologist v : players) {
			Random rnd = new Random();
			int fieldId = rnd.nextInt(100);
			v.setMyField(board.get(fieldId));
		}
	}

	private void placePlayers() {
		// jatekosok elhelyezese a palyan
		int fieldId = 53;
		for (Virologist v : players) {
			v.setMyField(board.get(fieldId));
			++fieldId;
		}
	}

	public ImageIcon isHereVirologist(int row, int col) {
		for (Virologist v : players) {
			if(v.getMyField().view.getPos().getY() == col &&
					v.getMyField().view.getPos().getX() == row) {
				return v.view.getIcon();
			}

		}
		return null;
	}

	public void setWinGame(boolean b) {
		winGame = b;
	}
	public boolean getWinGame() {
		return winGame;
	}

	public Virologist getPlayer(int id) throws Exception{
		return players.get(id);
	}

	public Virologist getPlayerById(int id){
		for(var player : players)
			if(player.getID() == id)
				return player;

		return null;
	}

	///Fieldhez gettert és settert adtam(Dávid)
	public void addField(Field f){
		for(int i=0; i<board.size(); i++){
			if(board.get(i).getNumber()==f.getNumber()){
				return;
			}
		}
		board.add(f);
	}

	///Index alapján getter
	public Field getField(int index){
		return board.get(index);
	}

	///Sorszám alapján getter
	public Field getFieldSorszam(Integer sorszam){
		for(int i=0; i<board.size(); i++){
			if(board.get(i).getNumber()==sorszam){
				return board.get(i);
			}
		}
		return null;
	}


	/**
	 * sorszám lekérdező
	 * @return sorok száma
	 */
	@Override
	public int getRowCount() {
		return height;
	}

	/**
	 * oszlopszám lekérdező
	 * @return oszlopok száma
	 */
	@Override
	public int getColumnCount() {
		return width;
	}

	/**
	 *
	 * @param rowIndex - a lekerdezni kivant cella y kordinataja
	 * @param columnIndex  - a lekerdezni kivant cella x kordinataja
	 * @return - a cella tartalma, egy Field példány
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		FieldV f = board.get((rowIndex)*width + columnIndex).view;

		return f;
	}

	/**
	 *
	 * @param aValue - beallitani kivant ertek
	 * @param rowIndex - cella y koordinátája
	 * @param columnIndex - cella x koordinátája
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		fireTableCellUpdated(rowIndex, columnIndex);
		fireTableDataChanged();
	}

	/**
	 * Táblázat frissítése
	 */
	private void fire() {
		fireTableDataChanged();
	}
}