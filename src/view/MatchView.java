package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.exceptions.ChessException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MatchView extends JFrame {

	
	private Board board;
	private ChessMatch match;
	
	private JPanel contentPane;
	private boolean clicked;
	private JPanel[][] pnBoardPositions;
	private JLabel[][] lbBoardPieces;
	private JLabel lbNumberTurn;
	private JLabel lbPlayer;
	private JLabel lbChessGameEvents;
	private Position source;
	private JPanel divLine;

	public MatchView(Board board, ChessMatch match) {
		// setBoard
		this.board = board;
		// match
		this.match = match;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 650);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// BoardClicked
		clicked = false;

		constructionBoard();

		constructionScoreBoard();

	}

	private void constructionScoreBoard() {
		JLabel lblTurn = new JLabel("Turn:");
		lblTurn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurn.setFont(new Font("Arial", Font.BOLD, 40));
		lblTurn.setBounds(629, 527, 256, 45);
		contentPane.add(lblTurn);

		lbNumberTurn = new JLabel("");
		lbNumberTurn.setFont(new Font("Arial", Font.BOLD, 40));
		lbNumberTurn.setBounds(815, 527, 56, 45);
		contentPane.add(lbNumberTurn);

		JLabel lbCurrentPlayer = new JLabel("Current Player:");
		lbCurrentPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lbCurrentPlayer.setFont(new Font("Arial", Font.BOLD, 33));
		lbCurrentPlayer.setBounds(629, 27, 270, 45);
		contentPane.add(lbCurrentPlayer);

		lbPlayer = new JLabel("WHITE");
		lbPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lbPlayer.setFont(new Font("Arial", Font.BOLD, 40));
		lbPlayer.setBounds(629, 83, 270, 64);
		contentPane.add(lbPlayer);
		
		lbChessGameEvents = new JLabel();
		lbChessGameEvents.setHorizontalAlignment(SwingConstants.CENTER);
		lbChessGameEvents.setFont(new Font("Arial", Font.BOLD, 40));
		lbChessGameEvents.setBounds(631, 204, 278, 178);
		contentPane.add(lbChessGameEvents);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(629, 0, 290, 621);
		contentPane.add(panel);
		
		divLine = new JPanel();
		divLine.setBackground(Color.GRAY);
		divLine.setBounds(619, 0, 10, 621);
		contentPane.add(divLine);
		
	}

	private void constructionBoard() {

		pnBoardPositions = new JPanel[8][8];
		lbBoardPieces = new JLabel[8][8];

		int y = 25;
		int chessHouse = 70;
		int aux = 0;
		
		String[] values = {"A","B","C","D","E","F","G","H"};

		for (int i = 0; i < pnBoardPositions.length; i++) {
			
			JLabel lbChessPositionX = new JLabel(values[i]);
			lbChessPositionX.setBounds(((i * 69) + 58 ), 575, 50, 50);
			lbChessPositionX.setFont(new Font("arial", 1, 25));
			
			for (int j = 0; j < pnBoardPositions.length; j++) {
				
				JLabel lbChessPositionY = new JLabel((8-j) + "");
				lbChessPositionY.setBounds(10, ((j * 70) + 34 ), 50, 50);
				lbChessPositionY.setFont(new Font("arial", 1, 25));

				constructionChessHouse( i, j, y, chessHouse);
				
				constructEvent(i, j);

				constructionPieces(i,j,chessHouse);
				

				pnBoardPositions[i][j].add(lbBoardPieces[i][j]);
				contentPane.add(pnBoardPositions[i][j]);
				contentPane.add(lbChessPositionY);
			}
			aux++;
			y += 70;
			contentPane.add(lbChessPositionX);
		}
		
		updateBoard();

	}

	// Construindo as jlabels das peças
	private void constructionPieces(int i, int j, int chessHouse) {
		lbBoardPieces[i][j] = new JLabel("-");
		lbBoardPieces[i][j].setFont(new Font("arial", 1, 50));
		lbBoardPieces[i][j].setSize(chessHouse, chessHouse);
		
	}

	// Construindo o tabuleiro
	private void constructionChessHouse(int i, int j, int y, int chessHouse) {
		pnBoardPositions[i][j] = new JPanel();
		pnBoardPositions[i][j].setBorder(new LineBorder(new Color(0, 0, 0)));
		pnBoardPositions[i][j].setBounds(30 + (j * 70), y, chessHouse, chessHouse);
		
	}

	private void constructEvent(int i, int j) {
		pnBoardPositions[i][j].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(i, j));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(i, j));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(i, j));

			}

		});
	}



	private JPanel panelPosition(Position position) {

		return pnBoardPositions[position.getRow()][position.getColumn()];

	}

	private void eventMouseEntered(MouseEvent arg0, Position position) {

		if (clicked != true) {
			if (board.piece(position) != null) {
				if (match.getCurrentPlayer() == ((ChessPiece) board.piece(position)).getColor()) {
					panelPosition(position).setBackground(Color.green);
				}else {
					panelPosition(position).setBackground(Color.red);
				}
			} else {
				panelPosition(position).setBackground(Color.red);
			}

		}
	}

	private void eventMouseExited(MouseEvent arg0, Position position) {

		if (clicked != true) {
			updateBoard();
		}

	}

	private void eventMouseClicked(MouseEvent arg0, Position position) {

		try {

			if (clicked == false) {
				if (board.therelsAPiece(position)) {
					if (match.getCurrentPlayer() == ((ChessPiece) board.piece(position)).getColor()) {
						if (board.therelsAPiece(position)) {
							if(((ChessPiece) board.piece(position)).isThereAnyPossibleMoves()) {
								possibleMoves(position);
								clicked = true;
							}else {
								lbChessGameEvents.setText("Any possible moves");
							}
						}
						source = position;
					}else {
						panelPosition(position).setBackground(Color.red);
					}	
				} else {
					panelPosition(position).setBackground(Color.red);
					clicked = false;
				}
			} else {
				Position target = position;
				ChessPiece chesspiece = match.getPieces()[position.getRow()][position.getColumn()];

				if (target.equals(source)) {
					clicked = false;
					updateBoard();
					panelPosition(position).setBackground(Color.green);
				} else {
					match.performChessMove(source, target);
					clicked = false;
					updateBoard();
				}
			}

		} catch (ChessException e) {
			panelPosition(position).setBackground(Color.red);
			clicked = false;
			updateBoard();
		}

	}

	public void possibleMoves(Position position) {
		boolean[][] possibles = board.piece(position).possibleMoves();
		for (int i = 0; i < possibles.length; i++) {
			for (int j = 0; j < possibles.length; j++) {
				if (possibles[i][j]) {
					pnBoardPositions[i][j].setBackground(Color.green);
				}
			}
		}
	}

	public void recorderPiece(Piece piece, int i, int j) {

		if (piece == null) {
			lbBoardPieces[i][j].setText("-");
			lbBoardPieces[i][j].setIcon(null);
		} else {
			lbBoardPieces[i][j].setText("");
			lbBoardPieces[i][j].setIcon(new ImageIcon(getClass().getResource(piece.toString())));
		}

	}

	private void updateBoard() {

		Piece[][] pieces = board.getPieces();

		int aux = 0;

		for (int i = 0; i < pnBoardPositions.length; i++) {
			for (int j = 0; j < pnBoardPositions.length; j++) {

				// Construindo o tabuleiro
				if (aux % 2 == 0) {
					if (j % 2 == 0) {
						pnBoardPositions[i][j].setBackground(Color.white);
					} else {
						pnBoardPositions[i][j].setBackground(Color.gray);
					}
				} else {
					if (j % 2 == 0) {
						pnBoardPositions[i][j].setBackground(Color.gray);
					} else {
						pnBoardPositions[i][j].setBackground(Color.white);
					}
				}


			    recorderPiece(pieces[i][j], i, j);
					

			}
			aux++;
		}

	}

	public void updateScore(int turn, chess.Color color) {

		lbNumberTurn.setText("" + turn);
		if (color == chess.Color.WHITE) {
			lbPlayer.setText(chess.Color.WHITE + "");
			lbPlayer.setForeground(Color.white);
		} else {
			lbPlayer.setText(chess.Color.BLACK + "");
			lbPlayer.setForeground(Color.black);
		}

	}
	
	public void updateCheck(boolean check, String msg) {
		
		if(check == true) {
			lbChessGameEvents.setText(msg);
		}else {
			lbChessGameEvents.setText(msg);
		}
		
	}
	
	public void checkMate(chess.Color color) {
		
		JOptionPane.showMessageDialog(null, "Check Mate");
	}
}
