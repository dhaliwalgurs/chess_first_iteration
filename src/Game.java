package Game;

import Game.Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by GDhaliwal on 2018-07-26.
 */

public class Game extends JPanel implements ActionListener, MouseListener {
    // fields
    public static int cellX = 0;
    public static int cellY = 0;

    public static int redTileX = 0;
    public static int redTileY = 0;
    public static boolean tileSelected = false;
    public static ArrayList<Piece> piecesArray = new ArrayList<>();
    Timer t;

    // constructor
    public Game()                                                               // create an instance of the Game.Game
    {
        piecesArray.add(new Rook(0, 0, true));
        piecesArray.add(new Knight(1, 0, true));
        piecesArray.add(new Bishop(2, 0, true));
        piecesArray.add(new Queen(3, 0, true));
        piecesArray.add(new King(4, 0, true));
        piecesArray.add(new Bishop(5, 0, true));
        piecesArray.add(new Knight(6, 0, true));
        piecesArray.add(new Rook(7, 0, true));

        piecesArray.add(new Pawn(0, 1, true));
        piecesArray.add(new Pawn(1, 1, true));
        piecesArray.add(new Pawn(2, 1, true));
        piecesArray.add(new Pawn(3, 1, true));
        piecesArray.add(new Pawn(4, 1, true));
        piecesArray.add(new Pawn(5, 1, true));
        piecesArray.add(new Pawn(6, 1, true));
        piecesArray.add(new Pawn(7, 1, true));

        piecesArray.add(new Rook(0, 7, false));
        piecesArray.add(new Knight(1, 7, false));
        piecesArray.add(new Bishop(2, 7, false));
        piecesArray.add(new Queen(3, 7, false));
        piecesArray.add(new King(4, 7, false));
        piecesArray.add(new Bishop(5, 7, false));
        piecesArray.add(new Knight(6, 7, false));
        piecesArray.add(new Rook(7, 7, false));

        piecesArray.add(new Pawn(0, 6, false));
        piecesArray.add(new Pawn(1, 6, false));
        piecesArray.add(new Pawn(2, 6, false));
        piecesArray.add(new Pawn(3, 6, false));
        piecesArray.add(new Pawn(4, 6, false));
        piecesArray.add(new Pawn(5, 6, false));
        piecesArray.add(new Pawn(6, 6, false));
        piecesArray.add(new Pawn(7, 6, false));

        t = new Timer(10,this);                                                 // update every 10 milliseconds
        t.start();                                                              // start timer
        addMouseListener(this);
        setFocusable(true);                                             // default true
    }

    // MOUSE LISTENER
    @Override
    public void mouseClicked(MouseEvent e) {
        for (Piece piece : piecesArray) {/*move selected piece*/
            if (piece.isSelected && Piece.isLegalMove(e, piece)) {
                piece.movePiece(e, piece);
                break;
            } else {/*nothing is selected*/
                piece.selectPiece(e, piece);
            }
        }
    }

    public static void paintBoard(Graphics2D g){
        for (int x = 0; x < 8 ; x++){
            for (int y = 0; y < 8; y++){
                if (((x + y) % 2) == 0){
                    g.setColor(new Color(90,100,50));
                    g.fillRect(x * 100, y * 100, 100, 100);
                }
                else if (((x + y) % 2) == 1){
                    g.setColor(new Color(70,70,70));
                    g.fillRect(x * 100, y * 100, 100, 100);
                }
            }
        }
    }

    public static void paintRedTile (Graphics2D g){
        if (tileSelected) {
            g.setColor(new Color(181, 53, 192));
            g.fillRect(redTileX, redTileY, 100, 100);
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        paintBoard(g2d);
        paintRedTile(g2d);
        for (Piece piece : piecesArray){
            if (piece.isActive){
                piece.paintPiece(g2d);
            }
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
