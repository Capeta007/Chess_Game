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

public class MatchView extends JFrame {

	private Board board;
	private ChessMatch match;

	private JPanel contentPane;
	private int chessHouseWhidth;
	private int chessHouseHeight;
	private boolean clicked;
	private JPanel[][] pnPositions;
	private JLabel[][] lbPieces;
	private JLabel lbNumTurn;
	private JLabel lbPlayer;
	private JLabel lbEvento;
	private Position source;
	private JPanel panel_1;

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

		constructionEvents();

	}

	private void constructionScoreBoard() {
		JLabel lblTurn = new JLabel("Turn:");
		lblTurn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurn.setFont(new Font("Arial", Font.BOLD, 40));
		lblTurn.setBounds(629, 527, 256, 45);
		contentPane.add(lblTurn);

		lbNumTurn = new JLabel("");
		lbNumTurn.setFont(new Font("Arial", Font.BOLD, 40));
		lbNumTurn.setBounds(815, 527, 56, 45);
		contentPane.add(lbNumTurn);

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
		
		lbEvento = new JLabel();
		lbEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lbEvento.setFont(new Font("Arial", Font.BOLD, 40));
		lbEvento.setBounds(631, 204, 278, 178);
		contentPane.add(lbEvento);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(629, 0, 290, 621);
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(619, 0, 10, 621);
		contentPane.add(panel_1);
	}

	private void constructionBoard() {

		pnPositions = new JPanel[8][8];
		lbPieces = new JLabel[8][8];

		int y = 25;
		chessHouseWhidth = 70;
		chessHouseHeight = 70;
		int aux = 0;
		
		String[] values = {"A","B","C","D","E","F","G","H"};

		for (int i = 0; i < pnPositions.length; i++) {
			
			JLabel lbChessPositionX = new JLabel(values[i]);
			lbChessPositionX.setBounds(((i * 69) + 58 ), 575, 50, 50);
			lbChessPositionX.setFont(new Font("arial", 1, 25));
			
			for (int j = 0; j < pnPositions.length; j++) {
				
				JLabel lbChessPositionY = new JLabel((8-j) + "");
				lbChessPositionY.setBounds(10, ((j * 70) + 34 ), 50, 50);
				lbChessPositionY.setFont(new Font("arial", 1, 25));

				// Construindo o tabuleiro
				pnPositions[i][j] = new JPanel();
				pnPositions[i][j].setBorder(new LineBorder(new Color(0, 0, 0)));
				pnPositions[i][j].setBounds(30 + (j * 70), y, chessHouseWhidth, chessHouseHeight);

				if (aux % 2 == 0) {
					if (j % 2 == 0) {
						pnPositions[i][j].setBackground(Color.white);
					} else {
						pnPositions[i][j].setBackground(Color.gray);
					}
				} else {
					if (j % 2 == 0) {
						pnPositions[i][j].setBackground(Color.gray);
					} else {
						pnPositions[i][j].setBackground(Color.white);
					}
				}

				// Construindo as jlabels das peças
				lbPieces[i][j] = new JLabel("-");
				lbPieces[i][j].setFont(new Font("arial", 1, 50));
				lbPieces[i][j].setSize(chessHouseWhidth, chessHouseHeight);

				pnPositions[i][j].add(lbPieces[i][j]);
				contentPane.add(pnPositions[i][j]);
				contentPane.add(lbChessPositionY);
			}
			aux++;
			y += 70;
			contentPane.add(lbChessPositionX);
		}

	}

	public void constructionEvents() {

		pnPositions[0][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(0, 0));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(0, 0));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(0, 0));

			}

		});

		pnPositions[0][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(0, 1));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(0, 1));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(0, 1));

			}
		});

		pnPositions[0][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(0, 2));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(0, 2));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(0, 2));

			}
		});

		pnPositions[0][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(0, 3));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(0, 3));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(0, 3));

			}
		});

		pnPositions[0][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(0, 4));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(0, 4));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(0, 4));

			}
		});

		pnPositions[0][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(0, 5));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(0, 5));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(0, 5));

			}
		});

		pnPositions[0][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(0, 6));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(0, 6));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(0, 6));

			}
		});

		pnPositions[0][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(0, 7));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(0, 7));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(0, 7));

			}
		});

		pnPositions[1][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(1, 0));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(1, 0));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(1, 0));

			}
		});

		pnPositions[1][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(1, 1));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(1, 1));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(1, 1));

			}
		});

		pnPositions[1][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(1, 2));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(1, 2));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(1, 2));

			}
		});

		pnPositions[1][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(1, 3));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(1, 3));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(1, 3));

			}
		});

		pnPositions[1][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(1, 4));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(1, 4));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(1, 4));

			}
		});

		pnPositions[1][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(1, 5));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(1, 5));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(1, 5));

			}
		});

		pnPositions[1][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(1, 6));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(1, 6));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(1, 6));

			}
		});

		pnPositions[1][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(1, 7));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(1, 7));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(1, 7));

			}
		});

		pnPositions[2][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(2, 0));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(2, 0));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(2, 0));

			}
		});

		pnPositions[2][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(2, 01));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(2, 01));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(2, 01));

			}
		});

		pnPositions[2][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(2, 02));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(2, 02));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(2, 02));

			}
		});

		pnPositions[2][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(2, 03));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(2, 03));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(2, 03));

			}
		});

		pnPositions[2][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(2, 04));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(2, 04));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(2, 04));

			}
		});

		pnPositions[2][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(2, 05));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(2, 05));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(2, 05));

			}
		});

		pnPositions[2][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(2, 06));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(2, 06));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(2, 06));

			}
		});

		pnPositions[2][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(2, 07));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(2, 07));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(2, 07));

			}
		});

		pnPositions[3][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(3, 0));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(3, 0));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(3, 0));

			}
		});

		pnPositions[3][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(3, 1));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(3, 1));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(3, 1));

			}
		});

		pnPositions[3][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(3, 2));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(3, 2));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(3, 2));

			}
		});

		pnPositions[3][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(3, 3));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(3, 3));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(3, 3));

			}
		});

		pnPositions[3][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(3, 4));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(3, 4));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(3, 4));

			}
		});

		pnPositions[3][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(3, 5));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(3, 5));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(3, 5));

			}
		});

		pnPositions[3][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(3, 6));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(3, 6));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(3, 6));

			}
		});

		pnPositions[3][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(3, 7));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(3, 7));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(3, 7));

			}
		});

		pnPositions[4][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(4, 0));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(4, 0));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(4, 0));

			}
		});

		pnPositions[4][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(4, 1));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(4, 1));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(4, 1));

			}
		});

		pnPositions[4][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(4, 2));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(4, 2));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(4, 2));

			}
		});

		pnPositions[4][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(4, 3));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(4, 3));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(4, 3));

			}
		});

		pnPositions[4][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(4, 4));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(4, 4));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(4, 4));

			}
		});

		pnPositions[4][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(4, 5));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(4, 5));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(4, 5));

			}
		});

		pnPositions[4][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(4, 6));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(4, 6));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(4, 6));

			}
		});

		pnPositions[4][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(4, 7));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(4, 7));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(4, 7));

			}
		});

		pnPositions[5][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(5, 0));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(5, 0));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(5, 0));

			}
		});

		pnPositions[5][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(5, 1));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(5, 1));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(5, 1));

			}
		});

		pnPositions[5][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(5, 2));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(5, 2));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(5, 2));

			}
		});

		pnPositions[5][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(5, 3));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(5, 3));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(5, 3));

			}
		});

		pnPositions[5][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(5, 4));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(5, 4));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(5, 4));

			}
		});

		pnPositions[5][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(5, 5));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(5, 5));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(5, 5));

			}
		});

		pnPositions[5][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(5, 6));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(5, 6));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(5, 6));

			}
		});

		pnPositions[5][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(5, 7));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(5, 7));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(5, 7));

			}
		});

		pnPositions[6][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(6, 0));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(6, 0));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(6, 0));

			}
		});

		pnPositions[6][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(6, 1));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(6, 1));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(6, 1));

			}
		});

		pnPositions[6][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(6, 2));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(6, 2));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(6, 2));

			}
		});

		pnPositions[6][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(6, 3));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(6, 3));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(6, 3));

			}
		});

		pnPositions[6][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(6, 4));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(6, 4));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(6, 4));

			}
		});

		pnPositions[6][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(6, 5));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(6, 5));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(6, 5));

			}
		});

		pnPositions[6][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(6, 6));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(6, 6));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(6, 6));

			}
		});

		pnPositions[6][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(6, 7));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(6, 7));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(6, 7));

			}
		});

		pnPositions[7][0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(7, 0));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(7, 0));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(7, 0));

			}
		});

		pnPositions[7][1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(7, 1));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(7, 1));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(7, 1));

			}
		});

		pnPositions[7][2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(7, 2));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(7, 2));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(7, 2));

			}
		});

		pnPositions[7][3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(7, 3));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(7, 3));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(7, 3));

			}
		});

		pnPositions[7][4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(7, 4));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(7, 4));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(7, 4));

			}
		});

		pnPositions[7][5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(7, 5));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(7, 5));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(7, 5));

			}
		});

		pnPositions[7][6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(7, 6));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(7, 6));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(7, 6));

			}
		});

		pnPositions[7][7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				eventMouseEntered(arg0, new Position(7, 7));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

				eventMouseExited(arg0, new Position(7, 7));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				eventMouseClicked(arg0, new Position(7, 7));

			}
		});
	}

	private JPanel panelPosition(Position position) {

		return pnPositions[position.getRow()][position.getColumn()];

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
								lbEvento.setText("Any possible moves");
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
					pnPositions[i][j].setBackground(Color.green);
				}
			}
		}
	}

	public void recorderPiece(Piece piece, int i, int j) {

		if (piece == null) {
			lbPieces[i][j].setText("-");
			lbPieces[i][j].setIcon(null);
		} else {
			lbPieces[i][j].setText("");
			lbPieces[i][j].setIcon(new ImageIcon(getClass().getResource(piece.toString())));
		}

	}

	private void updateBoard() {

		Piece[][] pieces = board.getPieces();

		int aux = 0;

		for (int i = 0; i < pnPositions.length; i++) {
			for (int j = 0; j < pnPositions.length; j++) {

				// Construindo o tabuleiro
				if (aux % 2 == 0) {
					if (j % 2 == 0) {
						pnPositions[i][j].setBackground(Color.white);
					} else {
						pnPositions[i][j].setBackground(Color.gray);
					}
				} else {
					if (j % 2 == 0) {
						pnPositions[i][j].setBackground(Color.gray);
					} else {
						pnPositions[i][j].setBackground(Color.white);
					}
				}


			    recorderPiece(pieces[i][j], i, j);
					

			}
			aux++;
		}

	}

	public void updateScore(int turn, chess.Color color) {

		lbNumTurn.setText("" + turn);
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
			lbEvento.setText(msg);
		}else {
			lbEvento.setText(msg);
		}
		
	}
	
	public void checkMate(chess.Color color) {
		
		JOptionPane.showMessageDialog(null, "Check Mate");
	}
}
