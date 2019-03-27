package incident.util;

public class Test
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    Tokenizer tokenizer = new Tokenizer();
    tokenizer.add("sin|cos|exp|ln|sqrt", 1);
    tokenizer.add("\\(", 2);
    tokenizer.add("\\)", 3);
    tokenizer.add("\\+|-", 4);
    tokenizer.add("\\*|/", 5);
    tokenizer.add("[0-9]+",6);
    tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 7);

    Tokenizer brsTokenizer  =new Tokenizer();
    
    brsTokenizer.add(BigraphERTokens.TOKEN_CONTAINMENT, BigraphERTokens.CONTAINMENT);
	brsTokenizer.add(BigraphERTokens.TOKEN_COMPOSITION, BigraphERTokens.COMPOSITION);
    brsTokenizer.add(BigraphERTokens.TOKEN_BIGRAPH_JUXTAPOSITION, BigraphERTokens.BIGRAPH_JUXTAPOSITION); 
    brsTokenizer.add(BigraphERTokens.TOKEN_ENTITY_JUXTAPOSITION, BigraphERTokens.ENTITY_JUXTAPOSITION); 
	brsTokenizer.add(BigraphERTokens.TOKEN_SITE, BigraphERTokens.SITE); 
	brsTokenizer.add(BigraphERTokens.TOKEN_OPEN_BRACKET, BigraphERTokens.OPEN_BRACKET); 
	brsTokenizer.add(BigraphERTokens.TOKEN_CLOSED_BRACKET, BigraphERTokens.CLOSED_BRACKET); 
	brsTokenizer.add(BigraphERTokens.TOKEN_OPEN_BRACKET_CONNECTIVITY, BigraphERTokens.OPEN_BRACKET_CONNECTIVITY); 
	brsTokenizer.add(BigraphERTokens.TOKEN_CLOSED_BRACKET_CONNECTIVITY, BigraphERTokens.CLOSED_BRACKET_CONNECTIVITY); 
	brsTokenizer.add(BigraphERTokens.TOKEN_CLOSED_CONNECTIVITY, BigraphERTokens.CLOSED_CONNECTIVITY);
    brsTokenizer.add(BigraphERTokens.TOKEN_COMMA, BigraphERTokens.COMMA);
	brsTokenizer.add(BigraphERTokens.TOKEN_SMALL_SPACE, BigraphERTokens.SMALL_SPACE);
    brsTokenizer.add(BigraphERTokens.TOKEN_WORD, BigraphERTokens.WORD);
    
    try
    {
    	String brs = "act   .( /con1 ps1{con1,con2} . ac1) || ps2*id";
    	
      brsTokenizer.tokenize(brs);

      System.out.println("condition: " + brs);
      
      for (Tokenizer.Token tok : brsTokenizer.getTokens())
      {
        System.out.println("" + tok.token + " " + tok.sequence);
      }
    }
    catch (ParserException e)
    {
      System.out.println(e.getMessage());
    }

  }
}
