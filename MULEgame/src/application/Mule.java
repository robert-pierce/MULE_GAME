package application;

import application.GameRunner.MuleType;

public class Mule {
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