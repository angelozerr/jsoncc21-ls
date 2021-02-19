/* Generated by: JavaCC 21 Parser Generator. ParseException.java */
package org.parsers.jsonc;

import java.util.*;
/**
 * This exception is thrown when parse errors are encountered.
 * You can explicitly create objects of this exception type by
 * calling the method generateParseException in the generated
 * parser.
 *
 * You can modify this class to customize your error reporting
 * mechanisms so long as you retain the public fields.
 */
@SuppressWarnings("serial")
public class ParseException extends Exception implements JSONCConstants {
    // The token we tripped up on.
    private Token token;
    //We were expecting one of these token types
    private EnumSet<TokenType> expectedTypes;
    private List<JSONCParser.NonTerminalCall> callStack;
    private boolean alreadyAdjusted;
    public ParseException() {
        super();
    }

    public ParseException(Token token, EnumSet<TokenType> expectedTypes, List<JSONCParser.NonTerminalCall> callStack) {
        this.token= token;
        this.expectedTypes= expectedTypes;
        this.callStack= new ArrayList<> (callStack);
    }

    public ParseException(JSONCParser parser, String message) {
        super(message);
        this.token= parser.lastConsumedToken;
        if (token!=null&&token.getNext()!=null) {
            token= token.getNext();
        }
        this.callStack= new ArrayList<> (parser.parsingStack);
    }

    public ParseException(JSONCParser parser, EnumSet<TokenType> expectedTypes, List<JSONCParser.NonTerminalCall> callStack) {
        this.token= parser.lastConsumedToken;
        if (token.getNext()!=null) token= token.getNext();
        this.expectedTypes= expectedTypes;
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Token token) {
        this.token= token;
    }

    @Override public String getMessage() {
        String msg= super.getMessage();
        if (token== null&&expectedTypes== null) {
            return msg;
        }
        StringBuilder buf= new StringBuilder();
        if (msg!=null) buf.append(msg);
        buf.append("\nEncountered an error at (or somewhere around) "+token.getLocation());
        if (expectedTypes!=null&&token!=null&&expectedTypes.contains(token.getType())) {
            return buf.toString();
        }
        if (expectedTypes!=null) {
            buf.append("\nWas expecting one of the following:\n");
            boolean isFirst= true;
            for (TokenType type : expectedTypes) {
                if (!isFirst) buf.append(", ");
                isFirst= false;
                buf.append(type);
            }
        }
        String content= token.getImage();
        if (content.length()> 32) content= content.substring(0, 32)+"...";
        buf.append("\nFound string \""+addEscapes(content)+"\" of type "+token.getType());
        return buf.toString();
    }

    @Override public StackTraceElement[] getStackTrace() {
        adjustStackTrace();
        return super.getStackTrace();
    }

    @Override public void printStackTrace(java.io.PrintStream s) {
        adjustStackTrace();
        super.printStackTrace(s);
    }

    /**
   * Returns the token which causes the parse error and null otherwise.
   * 
   * @return the token which causes the parse error and null otherwise.
   */
    public Token getToken() {
        return token;
    }

    public static String addEscapes(String str) {
        StringBuilder retval= new StringBuilder();
        for (char ch : str.toCharArray()) {
            switch(ch) {
                case'\b':
                retval.append("\\b");
                continue;
                case'\t':
                retval.append("\\t");
                continue;
                case'\n':
                retval.append("\\n");
                continue;
                case'\f':
                retval.append("\\f");
                continue;
                case'\r':
                retval.append("\\r");
                continue;
                case'\"':
                retval.append("\\\"");
                continue;
                case'\'':
                retval.append("\\\'");
                continue;
                case'\\':
                retval.append("\\\\");
                continue;
                default:
                if (Character.isISOControl(ch)) {
                    String s= "0000"+java.lang.Integer.toString(ch, 16);
                    retval.append("\\u"+s.substring(s.length()-4, s.length()));
                }
                else {
                    retval.append(ch);
                }
                continue;
            }
        }
        return retval.toString();
    }

    private void adjustStackTrace() {
        if (alreadyAdjusted||callStack== null||callStack.isEmpty()) return;
        List<StackTraceElement> fullTrace= new LinkedList<> ();
        List<StackTraceElement> ourCallStack= new LinkedList<> ();
        for (JSONCParser.NonTerminalCall ntc : callStack) {
            ourCallStack.add(ntc.createStackTraceElement());
        }
        StackTraceElement[] jvmCallStack= super.getStackTrace();
        for (StackTraceElement regularEntry : jvmCallStack) {
            if (ourCallStack.isEmpty()) break;
            String methodName= regularEntry.getMethodName();
            StackTraceElement ourEntry= lastElementWithName(ourCallStack, methodName);
            if (ourEntry!=null) {
                fullTrace.add(ourEntry);
            }
            fullTrace.add(regularEntry);
        }
        StackTraceElement[] result= new StackTraceElement[fullTrace.size()];
        setStackTrace(fullTrace.toArray(result));
        alreadyAdjusted= true;
    }

    private StackTraceElement lastElementWithName(List<StackTraceElement> elements, String methodName) {
        for (ListIterator<StackTraceElement> it= elements.listIterator(elements.size()); it.hasPrevious(); ) {
            StackTraceElement elem= it.previous();
            if (elem.getMethodName().equals(methodName)) {
                it.remove();
                return elem;
            }
        }
        return null;
    }

}
