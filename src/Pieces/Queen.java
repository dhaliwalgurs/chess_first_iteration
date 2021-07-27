package Game.Pieces;

import Game.Piece;

import java.awt.event.MouseEvent;

/**
 * Created by GDhaliwal on 2018-07-26.
 */
public class Queen extends Piece {

    // constructor
    public Queen (int x, int y, boolean colour){
        this.code = 'Q';
        this.isBlack = colour;
        this.readSpriteSheetX = 333*1;
        this.x = x * 100;
        this.y = y * 100;
    }

    // methods
    public static boolean isLegalMove(MouseEvent e, Piece piece){
        int proposedX = roundFifty(e.getPoint().x) - 50; /*make this click move the piece to new location*/
        int proposedY = roundFifty(e.getPoint().y) - 50;
        // diagonals and straight lines
        return Math.abs(proposedX - piece.x) == Math.abs(proposedY - piece.y) ||   // straight line
               proposedX == piece.x || proposedY == piece.y;                       // diagonal
    }
}
