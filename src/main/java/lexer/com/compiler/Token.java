package lexer.com.compiler;

import lexer.utils.Tokens;

public class Token {

    private String name;
	private String attribute;
	
	public Token(String name, String attribute){
		this.name = name;
		this.attribute = attribute;
	}
	
	public Token(String name){
		this.name = name;
		this.attribute = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	public String toString(){
		return attribute==null? name : "< "+name+", \""+attribute+"\" >";
				
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	public int compareTo(Token token1){
		return Tokens.compareTo(Tokens.valueOf(name), Tokens.valueOf(token1.name));
	}

	public boolean is(Tokens tokenType){
		if( tokenType.equals(Tokens.valueOf(name)))
			return true;
		return false;
	}
	
}
