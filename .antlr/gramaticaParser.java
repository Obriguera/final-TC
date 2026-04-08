// Generated from c:/Users/obrig/OneDrive/Desktop/UBP/Tecnicas de Compilacion/FINAL/final-TC/gramatica.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class gramaticaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, IF=2, ELSE=3, WHILE=4, BREAK=5, RETURN=6, TRUE=7, FALSE=8, ID=9, 
		FLOAT_NUMBER=10, NUMBER=11, CHAR_LITERAL=12, EQUAL=13, ASSIGN=14, NOT_EQUAL=15, 
		PLUS=16, MINUS=17, MUL=18, DIV=19, MOD=20, POW=21, MAYOREQUAL=22, MENOREQUAL=23, 
		MAYOR=24, MENOR=25, DOT=26, LPAREN=27, RPAREN=28, LBRACKET=29, RBRACKET=30, 
		LBRACE=31, RBRACE=32, SEMICOLON=33, COMMA=34, WS=35, LINE_COMMENT=36, 
		BLOCK_COMMENT=37, ERROR_CHAR=38;
	public static final int
		RULE_r = 0, RULE_token = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"r", "token"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'if'", "'else'", "'while'", "'break'", "'return'", "'true'", 
			"'false'", null, null, null, null, "'=='", "'='", "'!='", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'^'", "'>='", "'<='", "'>'", "'<'", "'.'", "'('", 
			"')'", "'['", "']'", "'{'", "'}'", "';'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT", "IF", "ELSE", "WHILE", "BREAK", "RETURN", "TRUE", "FALSE", 
			"ID", "FLOAT_NUMBER", "NUMBER", "CHAR_LITERAL", "EQUAL", "ASSIGN", "NOT_EQUAL", 
			"PLUS", "MINUS", "MUL", "DIV", "MOD", "POW", "MAYOREQUAL", "MENOREQUAL", 
			"MAYOR", "MENOR", "DOT", "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", 
			"LBRACE", "RBRACE", "SEMICOLON", "COMMA", "WS", "LINE_COMMENT", "BLOCK_COMMENT", 
			"ERROR_CHAR"
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

	@Override
	public String getGrammarFileName() { return "gramatica.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public gramaticaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(gramaticaParser.EOF, 0); }
		public List<TokenContext> token() {
			return getRuleContexts(TokenContext.class);
		}
		public TokenContext token(int i) {
			return getRuleContext(TokenContext.class,i);
		}
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_r);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34359738366L) != 0)) {
				{
				{
				setState(4);
				token();
				}
				}
				setState(9);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(10);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TokenContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(gramaticaParser.INT, 0); }
		public TerminalNode IF() { return getToken(gramaticaParser.IF, 0); }
		public TerminalNode ELSE() { return getToken(gramaticaParser.ELSE, 0); }
		public TerminalNode WHILE() { return getToken(gramaticaParser.WHILE, 0); }
		public TerminalNode BREAK() { return getToken(gramaticaParser.BREAK, 0); }
		public TerminalNode RETURN() { return getToken(gramaticaParser.RETURN, 0); }
		public TerminalNode TRUE() { return getToken(gramaticaParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(gramaticaParser.FALSE, 0); }
		public TerminalNode ID() { return getToken(gramaticaParser.ID, 0); }
		public TerminalNode FLOAT_NUMBER() { return getToken(gramaticaParser.FLOAT_NUMBER, 0); }
		public TerminalNode NUMBER() { return getToken(gramaticaParser.NUMBER, 0); }
		public TerminalNode CHAR_LITERAL() { return getToken(gramaticaParser.CHAR_LITERAL, 0); }
		public TerminalNode EQUAL() { return getToken(gramaticaParser.EQUAL, 0); }
		public TerminalNode ASSIGN() { return getToken(gramaticaParser.ASSIGN, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(gramaticaParser.NOT_EQUAL, 0); }
		public TerminalNode PLUS() { return getToken(gramaticaParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(gramaticaParser.MINUS, 0); }
		public TerminalNode MUL() { return getToken(gramaticaParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(gramaticaParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(gramaticaParser.MOD, 0); }
		public TerminalNode POW() { return getToken(gramaticaParser.POW, 0); }
		public TerminalNode MAYOREQUAL() { return getToken(gramaticaParser.MAYOREQUAL, 0); }
		public TerminalNode MENOREQUAL() { return getToken(gramaticaParser.MENOREQUAL, 0); }
		public TerminalNode MAYOR() { return getToken(gramaticaParser.MAYOR, 0); }
		public TerminalNode MENOR() { return getToken(gramaticaParser.MENOR, 0); }
		public TerminalNode DOT() { return getToken(gramaticaParser.DOT, 0); }
		public TerminalNode LPAREN() { return getToken(gramaticaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(gramaticaParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(gramaticaParser.SEMICOLON, 0); }
		public TerminalNode LBRACKET() { return getToken(gramaticaParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(gramaticaParser.RBRACKET, 0); }
		public TerminalNode LBRACE() { return getToken(gramaticaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(gramaticaParser.RBRACE, 0); }
		public TerminalNode COMMA() { return getToken(gramaticaParser.COMMA, 0); }
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_token);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 34359738366L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001&\u000f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001"+
		"\u0000\u0005\u0000\u0006\b\u0000\n\u0000\f\u0000\t\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0000\u0000\u0002\u0000"+
		"\u0002\u0000\u0001\u0001\u0000\u0001\"\r\u0000\u0007\u0001\u0000\u0000"+
		"\u0000\u0002\f\u0001\u0000\u0000\u0000\u0004\u0006\u0003\u0002\u0001\u0000"+
		"\u0005\u0004\u0001\u0000\u0000\u0000\u0006\t\u0001\u0000\u0000\u0000\u0007"+
		"\u0005\u0001\u0000\u0000\u0000\u0007\b\u0001\u0000\u0000\u0000\b\n\u0001"+
		"\u0000\u0000\u0000\t\u0007\u0001\u0000\u0000\u0000\n\u000b\u0005\u0000"+
		"\u0000\u0001\u000b\u0001\u0001\u0000\u0000\u0000\f\r\u0007\u0000\u0000"+
		"\u0000\r\u0003\u0001\u0000\u0000\u0000\u0001\u0007";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}