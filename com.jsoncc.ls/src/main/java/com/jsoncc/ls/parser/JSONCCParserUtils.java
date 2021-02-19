package com.jsoncc.ls.parser;

import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.parsers.jsonc.JSONCParser;
import org.parsers.jsonc.Node;
import org.parsers.jsonc.ParseException;

public class JSONCCParserUtils {

	private static CancelChecker DEFAULT_CANCEL_CHECKER = () -> {
	};

	public static Node parse(String content) {
		return parse(content, null, DEFAULT_CANCEL_CHECKER);
	}

	public static Node parse(String content, String fileName, CancelChecker cancelChecker) {
		if (cancelChecker == null) {
			cancelChecker = DEFAULT_CANCEL_CHECKER;
		}
		// Grammar grammar = new Grammar();
		// try {
		// grammar.setFilename(URLDecoder.decode(fileName.replace("file:///",
		// "").replace("file://", ""), "UTF-8"));
		// } catch (UnsupportedEncodingException e1) {
		// e1.printStackTrace();
		// }
		// grammar.setReporter(new LSPJavaCCErrorReporter());

		JSONCParser parser = new JSONCParser(fileName, content);

		parser.setParserTolerant(true);
		parser.setBuildTree(true);
		try {
			parser.Value();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parser.rootNode();
	}
}
