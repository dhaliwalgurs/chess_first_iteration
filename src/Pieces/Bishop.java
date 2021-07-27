package Game.Pieces;

import Game.Piece;

import java.awt.event.MouseEvent;

/**
 * Created by GDhaliwal on 2018-07-26.
 */
public class Bishop extends Piece {

    // constructor
    public Bishop (int x, int y, boolean colour){
        this.code = 'B';
        this.isBlack = colour;
        this.readSpriteSheetX = 333*2;
        this.x = x * 100;
        this.y = y * 100;

    }

    // methods
    public static boolean isLegalMove(MouseEvent e, Piece piece){
        int proposedX = roundFifty(e.getPoint().x) - 50; /*make this click move the piece to new location*/
        int proposedY = roundFifty(e.getPoint().y) - 50;
        // diagonals
        return Math.abs(proposedX - piece.x) == Math.abs(proposedY - piece.y);
    }
}
