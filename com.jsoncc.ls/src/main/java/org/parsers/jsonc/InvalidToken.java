/* Generated by: JavaCC 21 Parser Generator. InvalidToken.java */
package org.parsers.jsonc;

/**
 * Token subclass to represent lexically invalid input
 */
public class InvalidToken extends Token {
    public InvalidToken(String image, String inputSource) {
        super(TokenType.INVALID, image, inputSource);
        super.setUnparsed(true);
        this.setDirty(true);
    }

    public String getNormalizedText() {
        return"Lexically Invalid Input:"+getImage();
    }

}
