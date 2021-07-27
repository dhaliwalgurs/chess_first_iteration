package Game;

import Game.Pieces.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;

/**
 * Created by GDhaliwal on 2018-07-26.
 */
public abstract class Piece {
    // CONSTANTS
    private static String ROOT_FILE = new File("").getAbsolutePath();
    private static String PIECES_FILE = ROOT_FILE.concat("\\src\\Game\\Pieces\\sprites.png");

    // VARIABLES
    public char code;

    public int x = 0;
    public int y = 0;

    public boolean isBlack = true;
    public boolean isActive = true;
    public boolean isSelected = false;
    public boolean canCastle = true;

    public int readSpriteSheetX = 0;
    public int readSpriteSheetY = 0;
    public static int spriteWidth = 333;
    public static int spriteHeight = 333;

    public static boolean isLegalMove() {
        return true;
    }

    public void setIsActive(boolean b) {
        isActive = b;
    }

    public void setBlack(boolean b) {
        isBlack = b;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS:
     */
    public void selectPiece(MouseEvent e, Piece piece) {
        if (e.getPoint().x % 100 <= 50) {
            Game.cellX = roundFifty(e.getPoint().x) - 50;
        } else {
            Game.cellX = roundFifty(e.getPoint().x) - 50;
        }
        if (e.getPoint().y % 100 <= 50) {
            Game.cellY = roundFifty(e.getPoint().y) - 50;
        } else {
            Game.cellY = roundFifty(e.getPoint().y) - 50;
        }

        if (piece.x == Game.cellX && piece.y == Game.cellY) {
            piece.isSelected = true;                     /*change the isSelected value of the nearest piece*/
            Game.tileSelected = true;
            Game.redTileX = piece.x;
            Game.redTileY = piece.y;
        }
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS:
     */
    public static boolean isLegalMove(MouseEvent e, Piece piece) {
        // which piece is it?
        switch (piece.code) {
            case 'B':
                return Bishop.isLegalMove(e, piece);
            case 'K':
                return King.isLegalMove(e, piece);
            case 'N':
                return Knight.isLegalMove(e, piece);
            case 'P':
                return Pawn.isLegalMove(e, piece);
            case 'Q':
                return Queen.isLegalMove(e, piece);
            case 'R':
                return Rook.isLegalMove(e, piece);
        }
        return false;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS:
     */
    public void movePiece(MouseEvent e, Piece piece){
        // include a case for deleting an opponent
        piece.x = roundFifty(e.getPoint().x) - 50; /*make this click move the piece to new location*/
        piece.y = roundFifty(e.getPoint().y) - 50;
        piece.isSelected = false;                  /*deselect the piece after moving, or dropping in same place*/
        Game.tileSelected = false;
        for (Piece attackedSquare : Game.piecesArray){
            if (    attackedSquare.x == roundFifty(e.getPoint().x) - 50
                    && attackedSquare.y == roundFifty(e.getPoint().y) - 50
                    && attackedSquare.isBlack != piece.isBlack){
                Game.piecesArray.remove(attackedSquare);
            }
        }
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS:
     */
    public static int roundFifty(int n){
        int mod = n % 100;
        if (mod < 25){ // round up to nearest 50
            return n - mod + 50;
        } else if (25 <= mod && mod < 50){ // round down to nearest 50
            return n - mod + 50;
        } else if (50 <= mod && mod < 75) {
            return n - mod + 50;
        } else {
            return n - mod + 50;
        }
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS:
     */
    public void paintPiece(Graphics2D g){
        try {
            BufferedImage image = ImageIO.read(new File(PIECES_FILE));
              readSpriteSheetY = isBlack ? 333 : 0;
            Image sprite = image.getSubimage(readSpriteSheetX, readSpriteSheetY, spriteWidth, spriteHeight);
            Image sizedSprite = sprite.getScaledInstance(spriteWidth / 3, spriteHeight / 3, Image.SCALE_DEFAULT);
            g.drawImage(sizedSprite, this.x - 4, this.y - 6, null);
        } catch (Exception IOException) {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("\\src\\Game\\Pieces\\sprites.png");
            System.out.println(filePath);
            System.out.print("IOException");
        }
    }
}
