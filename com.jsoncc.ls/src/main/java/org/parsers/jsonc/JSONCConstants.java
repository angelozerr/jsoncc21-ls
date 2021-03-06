/* Generated by: JavaCC 21 Parser Generator. Do not edit. JSONCConstants.java */
package org.parsers.jsonc;

/**
 * Token literal values and constants.
 */
public interface JSONCConstants {
    public enum TokenType {
        EOF, SINGLE_LINE_COMMENT, MULTI_LINE_COMMENT, WHITESPACE, COLON, COMMA, OPEN_BRACKET, CLOSE_BRACKET, OPEN_BRACE, CLOSE_BRACE, TRUE, FALSE, NULL, ESCAPE1, ESCAPE2, REGULAR_CHAR, STRING_LITERAL, ZERO, NON_ZERO, FRACTION, EXPONENT, NUMBER, INVALID
    }
    /**
   * Lexical States
   */
    public enum LexicalState {
        JSON, 
    }
    String[] tokenImage= {"<EOF>", "<SINGLE_LINE_COMMENT>", "<MULTI_LINE_COMMENT>", "<WHITESPACE>", "\":\"", "\",\"", "\"[\"", "\"]\"", "\"{\"", "\"}\"", "\"true\"", "\"false\"", "\"null\"", "<ESCAPE1>", "<ESCAPE2>", "<REGULAR_CHAR>", "<STRING_LITERAL>", "\"0\"", "<NON_ZERO>", "<FRACTION>", "<EXPONENT>", "<NUMBER>", };
}
