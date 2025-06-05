package examples.RMIShape;

import java.awt.Rectangle;
import java.awt.Color;
import java.io.Serializable;

public class GraphicalObject implements Serializable{
    public String type;
    public Rectangle enclosing;
    public Color line;
    public Color fill;
    public boolean isFilled;
    
    //	constructors
    public GraphicalObject() { }
    
    public GraphicalObject(String aType, Rectangle anEnclosing, Color aLine, Color aFill, boolean anIsFilled) {
		type = aType;
		enclosing = anEnclosing;
		line = aLine;
		fill = aFill;
		isFilled = anIsFilled;
    }
    
    public void print(){
		System.out.print(type);
		System.out.print(enclosing.x + " , " + enclosing.y + " , " + enclosing.width + " , "  + enclosing.height);
		if(isFilled) System.out.println("- filled");else System.out.println("not filled");
	}
}