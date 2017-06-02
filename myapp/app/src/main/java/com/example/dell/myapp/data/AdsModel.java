package com.example.dell.myapp.data;

/**
 * Created by shahb on 29.05.2017.
 */

public class AdsModel {
   public int driverId;
   public String loadplace;
   public String uploadplace;
   public String loaddate;
   public String type;
   public int cena;
   public String status;

   public AdsModel(String loadplace, String uploadplace, String type, String loaddate, int cena) {
      this.loadplace = loadplace;
      this.uploadplace = uploadplace;
      this.type = type;
      this.loaddate = loaddate;
      this.cena = cena;
   }
}
