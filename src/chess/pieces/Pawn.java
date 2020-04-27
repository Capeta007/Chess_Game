package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);
		
		if(getColor() == Color.WHITE) {
		
			p.setValues(position.getRow()-1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().therelsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			Position p2 = new Position(position.getRow()-2, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().therelsAPiece(p) && getBoard().positionExists(p2) && !getBoard().therelsAPiece(p2) && getMoveCount() == 0) {
				mat[p2.getRow()][p2.getColumn()] = true;
			}
			
			p.setValues(position.getRow()-1, position.getColumn()-1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow()-1, position.getColumn()+1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		else {
			p.setValues(position.getRow()+1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().therelsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			Position p2 = new Position(position.getRow()+2, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().therelsAPiece(p) && getBoard().positionExists(p2) && !getBoard().therelsAPiece(p2) && getMoveCount() == 0) {
				mat[p2.getRow()][p2.getColumn()] = true;
			}
			
			p.setValues(position.getRow()+1, position.getColumn()+1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow()+1, position.getColumn()-1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		return mat;
	}

	@Override
	public String toString() {
		if(getColor() == Color.WHITE) {
			return "/chess/pieces/pictures/whitePawn.jpg";
		}else {
			return "/chess/pieces/pictures/blackPawn.jpg";
		}
	}
	
	

}
