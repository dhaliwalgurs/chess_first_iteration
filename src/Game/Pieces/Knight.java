package Game.Pieces;

import Game.Piece;

import java.awt.event.MouseEvent;

/**
 * Created by GDhaliwal on 2018-07-26.
 */
public class Knight extends Piece {

    // constructor
    public Knight (int x, int y, boolean colour){
        this.code = 'N';
        this.isBlack = colour;
        this.readSpriteSheetX = 333*3;
        this.x = x * 100;
        this.y = y * 100;
    }

    // methods
    public static boolean isLegalMove(MouseEvent e, Piece piece){
        int proposedX = roundFifty(e.getPoint().x) - 50; /*make this click move the piece to new location*/
        int proposedY = roundFifty(e.getPoint().y) - 50;
        // L-movements or circular range
        return  Math.abs(proposedX - piece.x) <= 200 &&
                Math.abs(proposedY - piece.y) <= 200 &&                             // within a (2,2) tile range
                !(Math.abs(proposedX - piece.x) == Math.abs(proposedY - piece.y) || // opposite of queen move in tile range
                proposedX == piece.x || proposedY == piece.y);
    }

}
