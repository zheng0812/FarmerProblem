/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.zheng.android;


/**
 *
 * @author zheng
 */
public class FarmerState implements State{
    public FarmerState(String F, String W,String G,String C){
        this.f=F;
        this.w=W;
        this.g=G;
        this.c=C;
      
    }
    public String getFarmer(){
        return this.f;
    }
    public String getWolve(){
        return this.w;
    }
    public String getGoat(){
        return this.g;
    }
    public String getCab(){
        return this.c;
    }

//return true or false if other equals this content
@Override
public boolean equals(Object other){
    FarmerState otherState = (FarmerState)other;
    //return this.contents==(otherState.contents);
    if (this ==other)
    {
        return true;
    }
    if (other == null){
        return false;
    }
    if( this.getClass() != other.getClass()){
        return false;
    }
    return this.f.equals(otherState.f) && this.w.equals(otherState.w) && this.g.equals(otherState.g) && this.c.equals(otherState.c);
   
}

    @Override
    public int hashCode() {
//        int hash = 5;
//        hash = 23 * hash + Objects.hashCode(this.f);
//        hash = 23 * hash + Objects.hashCode(this.w);
//        hash = 23 * hash + Objects.hashCode(this.g);
//        hash = 23 * hash + Objects.hashCode(this.c);
        int hash =0;
        if(this.f.equals("West")){
            hash=hash+1;
        }
         if(this.w.equals("West")){
            hash=hash+2;
        }
        if(this.g.equals("West")){
            hash=hash+4;
        }
        if(this.c.equals("West")){
           hash=hash+8;
        }
        return hash;
    }


@Override
public String toString(){
   //String out= "The value is: " + this.contents;
    //return out;
    StringBuilder sb = new StringBuilder();
    sb.append("   |  |   \n");
    
    if(this.f.equals("West"))
    {
        sb.append(" F |  |   \n");
    }
    else 
    {
        sb.append("   |  | F \n");
    }
    if(this.w.equals("West"))
    {
        sb.append(" W |  |   \n");
    }
    else 
    {
        sb.append("   |  | W \n");
    }
    if(this.g.equals("West"))
    {
        sb.append(" G |  |   \n");
    }
    else 
    {
        sb.append("   |  | G \n");
    }
    if(this.c.equals("West"))
    {
        sb.append(" C |  |   \n");
    }
    else 
    {
        sb.append("   |  | C \n");
    }
    sb.append("   |  |   ");
    return sb.toString();
}

 
private final String f;
private final String w;
private final String g;
private final String c;






    
    
}
