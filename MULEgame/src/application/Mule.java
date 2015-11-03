package application;

import java.io.Serializable;

import application.GameRunner.MuleType;

public class Mule implements Serializable {
   
	private static final long serialVersionUID = 1311388712054206946L;
	private MuleType type;
   
   public Mule() {
	   type = MuleType.EMPTY;
   }
   
   public void setMuleType(MuleType mulType) {
	   type = mulType;
   }
   
   public MuleType getMuleType() {
	   return type;
   }


   
}