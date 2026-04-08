// Generated from c:/Users/obrig/OneDrive/Desktop/UBP/Tecnicas de Compilacion/FINAL/final-TC/gramatica.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class gramaticaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, IF=2, ELSE=3, WHILE=4, BREAK=5, RETURN=6, TRUE=7, FALSE=8, ID=9, 
		NUMBER=10, EQUAL=11, ASSIGN=12, NOT_EQUAL=13, PLUS=14, MINUS=15, MUL=16, 
		DIV=17, POW=18, MAYOREQUAL=19, MENOREQUAL=20, MAYOR=21, MENOR=22, LPAREN=23, 
		RPAREN=24, SEMICOLON=25, WS=26, LINE_COMMENT=27, BLOCK_COMMENT=28, ERROR_CHAR=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"INT", "IF", "ELSE", "WHILE", "BREAK", "RETURN", "TRUE", "FALSE", "ID", 
			"NUMBER", "EQUAL", "ASSIGN", "NOT_EQUAL", "PLUS", "MINUS", "MUL", "DIV", 
			"POW", "MAYOREQUAL", "MENOREQUAL", "MAYOR", "MENOR", "LPAREN", "RPAREN", 
			"SEMICOLON", "WS", "LINE_COMMENT", "BLOCK_COMMENT", "ERROR_CHAR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'if'", "'else'", "'while'", "'break'", "'return'", "'true'", 
			"'false'", null, null, "'=='", "'='", "'!='", "'+'", "'-'", "'*'", "'/'", 
			"'^'", "'>='", "'<='", "'>'", "'<'", "'('", "')'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT", "IF", "ELSE", "WHILE", "BREAK", "RETURN", "TRUE", "FALSE", 
			"ID", "NUMBER", "EQUAL", "ASSIGN", "NOT_EQUAL", "PLUS", "MINUS", "MUL", 
			"DIV", "POW", "MAYOREQUAL", "MENOREQUAL", "MAYOR", "MENOR", "LPAREN", 
			"RPAREN", "SEMICOLON", "WS", "LINE_COMMENT", "BLOCK_COMMENT", "ERROR_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public gramaticaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "gramatica.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001d\u00b5\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0005"+
		"\bh\b\b\n\b\f\bk\t\b\u0001\t\u0004\tn\b\t\u000b\t\f\to\u0001\n\u0001\n"+
		"\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0019\u0004\u0019\u0095\b\u0019\u000b\u0019\f\u0019\u0096\u0001"+
		"\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0005"+
		"\u001a\u009f\b\u001a\n\u001a\f\u001a\u00a2\t\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u00aa\b\u001b"+
		"\n\u001b\f\u001b\u00ad\t\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u00ab\u0000\u001d\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/"+
		"\u00181\u00193\u001a5\u001b7\u001c9\u001d\u0001\u0000\u0005\u0002\u0000"+
		"AZaz\u0004\u000009AZ__az\u0001\u000009\u0003\u0000\t\n\r\r  \u0002\u0000"+
		"\n\n\r\r\u00b9\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000"+
		"3\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001"+
		"\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0001;\u0001\u0000\u0000"+
		"\u0000\u0003?\u0001\u0000\u0000\u0000\u0005B\u0001\u0000\u0000\u0000\u0007"+
		"G\u0001\u0000\u0000\u0000\tM\u0001\u0000\u0000\u0000\u000bS\u0001\u0000"+
		"\u0000\u0000\rZ\u0001\u0000\u0000\u0000\u000f_\u0001\u0000\u0000\u0000"+
		"\u0011e\u0001\u0000\u0000\u0000\u0013m\u0001\u0000\u0000\u0000\u0015q"+
		"\u0001\u0000\u0000\u0000\u0017t\u0001\u0000\u0000\u0000\u0019v\u0001\u0000"+
		"\u0000\u0000\u001by\u0001\u0000\u0000\u0000\u001d{\u0001\u0000\u0000\u0000"+
		"\u001f}\u0001\u0000\u0000\u0000!\u007f\u0001\u0000\u0000\u0000#\u0081"+
		"\u0001\u0000\u0000\u0000%\u0083\u0001\u0000\u0000\u0000\'\u0086\u0001"+
		"\u0000\u0000\u0000)\u0089\u0001\u0000\u0000\u0000+\u008b\u0001\u0000\u0000"+
		"\u0000-\u008d\u0001\u0000\u0000\u0000/\u008f\u0001\u0000\u0000\u00001"+
		"\u0091\u0001\u0000\u0000\u00003\u0094\u0001\u0000\u0000\u00005\u009a\u0001"+
		"\u0000\u0000\u00007\u00a5\u0001\u0000\u0000\u00009\u00b3\u0001\u0000\u0000"+
		"\u0000;<\u0005i\u0000\u0000<=\u0005n\u0000\u0000=>\u0005t\u0000\u0000"+
		">\u0002\u0001\u0000\u0000\u0000?@\u0005i\u0000\u0000@A\u0005f\u0000\u0000"+
		"A\u0004\u0001\u0000\u0000\u0000BC\u0005e\u0000\u0000CD\u0005l\u0000\u0000"+
		"DE\u0005s\u0000\u0000EF\u0005e\u0000\u0000F\u0006\u0001\u0000\u0000\u0000"+
		"GH\u0005w\u0000\u0000HI\u0005h\u0000\u0000IJ\u0005i\u0000\u0000JK\u0005"+
		"l\u0000\u0000KL\u0005e\u0000\u0000L\b\u0001\u0000\u0000\u0000MN\u0005"+
		"b\u0000\u0000NO\u0005r\u0000\u0000OP\u0005e\u0000\u0000PQ\u0005a\u0000"+
		"\u0000QR\u0005k\u0000\u0000R\n\u0001\u0000\u0000\u0000ST\u0005r\u0000"+
		"\u0000TU\u0005e\u0000\u0000UV\u0005t\u0000\u0000VW\u0005u\u0000\u0000"+
		"WX\u0005r\u0000\u0000XY\u0005n\u0000\u0000Y\f\u0001\u0000\u0000\u0000"+
		"Z[\u0005t\u0000\u0000[\\\u0005r\u0000\u0000\\]\u0005u\u0000\u0000]^\u0005"+
		"e\u0000\u0000^\u000e\u0001\u0000\u0000\u0000_`\u0005f\u0000\u0000`a\u0005"+
		"a\u0000\u0000ab\u0005l\u0000\u0000bc\u0005s\u0000\u0000cd\u0005e\u0000"+
		"\u0000d\u0010\u0001\u0000\u0000\u0000ei\u0007\u0000\u0000\u0000fh\u0007"+
		"\u0001\u0000\u0000gf\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000"+
		"ig\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000j\u0012\u0001\u0000"+
		"\u0000\u0000ki\u0001\u0000\u0000\u0000ln\u0007\u0002\u0000\u0000ml\u0001"+
		"\u0000\u0000\u0000no\u0001\u0000\u0000\u0000om\u0001\u0000\u0000\u0000"+
		"op\u0001\u0000\u0000\u0000p\u0014\u0001\u0000\u0000\u0000qr\u0005=\u0000"+
		"\u0000rs\u0005=\u0000\u0000s\u0016\u0001\u0000\u0000\u0000tu\u0005=\u0000"+
		"\u0000u\u0018\u0001\u0000\u0000\u0000vw\u0005!\u0000\u0000wx\u0005=\u0000"+
		"\u0000x\u001a\u0001\u0000\u0000\u0000yz\u0005+\u0000\u0000z\u001c\u0001"+
		"\u0000\u0000\u0000{|\u0005-\u0000\u0000|\u001e\u0001\u0000\u0000\u0000"+
		"}~\u0005*\u0000\u0000~ \u0001\u0000\u0000\u0000\u007f\u0080\u0005/\u0000"+
		"\u0000\u0080\"\u0001\u0000\u0000\u0000\u0081\u0082\u0005^\u0000\u0000"+
		"\u0082$\u0001\u0000\u0000\u0000\u0083\u0084\u0005>\u0000\u0000\u0084\u0085"+
		"\u0005=\u0000\u0000\u0085&\u0001\u0000\u0000\u0000\u0086\u0087\u0005<"+
		"\u0000\u0000\u0087\u0088\u0005=\u0000\u0000\u0088(\u0001\u0000\u0000\u0000"+
		"\u0089\u008a\u0005>\u0000\u0000\u008a*\u0001\u0000\u0000\u0000\u008b\u008c"+
		"\u0005<\u0000\u0000\u008c,\u0001\u0000\u0000\u0000\u008d\u008e\u0005("+
		"\u0000\u0000\u008e.\u0001\u0000\u0000\u0000\u008f\u0090\u0005)\u0000\u0000"+
		"\u00900\u0001\u0000\u0000\u0000\u0091\u0092\u0005;\u0000\u0000\u00922"+
		"\u0001\u0000\u0000\u0000\u0093\u0095\u0007\u0003\u0000\u0000\u0094\u0093"+
		"\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0094"+
		"\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0001\u0000\u0000\u0000\u0098\u0099\u0006\u0019\u0000\u0000\u00994\u0001"+
		"\u0000\u0000\u0000\u009a\u009b\u0005/\u0000\u0000\u009b\u009c\u0005/\u0000"+
		"\u0000\u009c\u00a0\u0001\u0000\u0000\u0000\u009d\u009f\b\u0004\u0000\u0000"+
		"\u009e\u009d\u0001\u0000\u0000\u0000\u009f\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a3\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000\u0000\u0000"+
		"\u00a3\u00a4\u0006\u001a\u0000\u0000\u00a46\u0001\u0000\u0000\u0000\u00a5"+
		"\u00a6\u0005/\u0000\u0000\u00a6\u00a7\u0005*\u0000\u0000\u00a7\u00ab\u0001"+
		"\u0000\u0000\u0000\u00a8\u00aa\t\u0000\u0000\u0000\u00a9\u00a8\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000"+
		"\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ac\u00ae\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00af\u0005*\u0000"+
		"\u0000\u00af\u00b0\u0005/\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000"+
		"\u00b1\u00b2\u0006\u001b\u0000\u0000\u00b28\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b4\t\u0000\u0000\u0000\u00b4:\u0001\u0000\u0000\u0000\u0006\u0000"+
		"io\u0096\u00a0\u00ab\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}