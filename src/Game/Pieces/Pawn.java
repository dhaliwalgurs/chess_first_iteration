package Game.Pieces;

import Game.*;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by GDhaliwal on 2018-07-26.
 */
public class Pawn extends Piece {

    // constructor
    public Pawn(int x, int y, boolean colour) {
        this.code = 'P';
        this.isBlack = colour;
        this.readSpriteSheetX = 333 * 5;
        this.x = x * 100;
        this.y = y * 100;
        this.isBlack = colour;
    }

    // generic methods for class
    public static boolean isLegalMove(MouseEvent e, Piece piece) {
        int proposedX = roundFifty(e.getPoint().x) - 50; /*make this click move the piece to new location*/
        int proposedY = roundFifty(e.getPoint().y) - 50;

        if (proposedX == piece.x){
            // move one to promotion
            if (piece.isBlack && piece.y == 600 && proposedY == 700){
                Game.piecesArray.remove(piece);
                Game.piecesArray.add(new Queen(proposedX/100, proposedY/100, true));
                return true;
            }
            if (!piece.isBlack && piece.y == 100 && proposedY == 0){
                Game.piecesArray.remove(piece);
                Game.piecesArray.add(new Queen(proposedX/100, proposedY/100, false));
                return true;
            }
            // move one normally
            if (    (piece.isBlack && proposedY == piece.y + 100)  ||
                    (!piece.isBlack && proposedY == piece.y - 100)) {
                return true;
            }
            // move two off start block
            if (    (piece.isBlack && piece.y == 100 && proposedY == 300) ||
                    (!piece.isBlack && piece.y == 600 && proposedY == 400)) {
                return true;
            }
        }

        // move one diagonally to capture
        for (Piece diagonalPiece : Game.piecesArray){
            if (    diagonalPiece.x == proposedX &&
                    diagonalPiece.y == proposedY){
                if (    piece.isBlack && // black diagonal capture
                        proposedY == piece.y + 100 &&
                        Math.abs(proposedX - piece.x) == 100) {
                    if (piece.y == 600){
                        Game.piecesArray.remove(piece);
                        Game.piecesArray.add(new Queen(proposedX/100, proposedY/100, true));
                        return true;
                    }
                    return true;
                }
                if (    !piece.isBlack && // white diagonal capture
                        proposedY == piece.y - 100 &&
                        Math.abs(proposedX - piece.x) == 100) {
                    if (piece.y == 100){
                        Game.piecesArray.remove(piece);
                        Game.piecesArray.add(new Queen(proposedX/100, proposedY/100/100, false));
                        return true;
                    }
                    return true;
                }
            }
        }
        // en passe capture
        return false;
    }

}

