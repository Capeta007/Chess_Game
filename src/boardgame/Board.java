package boardgame;

import boardgame.exceptions.BoardException;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: é necessário que haja pelo menos 1 linha e coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
		
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}

	public Piece piece(int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		if(therelsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
		
	}
	
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		if(piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
		
	}
	
	public boolean positionExists(Position position) {
		
		return positionExists(position.getRow(), position.getColumn()); 
		
	}
	
	private boolean positionExists(int row, int column) {
		
		return row < rows && row >= 0 && column < columns && column >= 0;
		
	}
	
	public boolean therelsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
		
	}

}
