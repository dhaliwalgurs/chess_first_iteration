package Game.Pieces;

import Game.Game;
import Game.Piece;

import java.awt.event.MouseEvent;

/**
 * Created by GDhaliwal on 2018-07-26.
 */
public class King extends Piece {
    // constructor
    public King(int x, int y, boolean colour) {
        this.code = 'K';
        this.isBlack = colour;
        this.readSpriteSheetX = 0;
        this.x = x * 100;
        this.y = y * 100;
        this.canCastle = true;
    }

    // methods
    public static boolean isLegalMove(MouseEvent e, Piece piece) {
        int proposedX = roundFifty(e.getPoint().x) - 50; /*make this click move the piece to new location*/
        int proposedY = roundFifty(e.getPoint().y) - 50;
        // castle
        if (piece.canCastle) {
            for (Piece rookSearch : Game.piecesArray) {
                if (    piece.isBlack && (proposedX - piece.x) == 200 &&
                        rookSearch.x == 700 && rookSearch.y == 0) {
                    Game.piecesArray.remove(rookSearch);
                    Game.piecesArray.add(new Rook(5, 0, true));
                    piece.canCastle = false;
                    return true;
                }
                if (    piece.isBlack && (proposedX - piece.x) == -200 &&
                        rookSearch.x == 0 && rookSearch.y == 0) {
                    Game.piecesArray.remove(rookSearch);
                    Game.piecesArray.add(new Rook(3, 0, true));
                    piece.canCastle = false;
                    return true;
                }
                if (    !piece.isBlack && (proposedX - piece.x) == 200 &&
                        rookSearch.x == 700 && rookSearch.y == 700) {
                    Game.piecesArray.remove(rookSearch);
                    Game.piecesArray.add(new Rook(5, 7, false));
                    piece.canCastle = false;
                    return true;
                }
                if (    !piece.isBlack && (proposedX - piece.x) == -200 &&
                        rookSearch.x == 0 && rookSearch.y == 700) {
                    Game.piecesArray.remove(rookSearch);
                    Game.piecesArray.add(new Rook(3, 7, false));
                    piece.canCastle = false;
                    return true;
                }
            }
        }
        // one step in any direction
        piece.canCastle = false;
        return  Math.abs(proposedX - piece.x) <= 100 && Math.abs(proposedY - piece.y) <= 100;
    }
}
