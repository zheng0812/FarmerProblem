/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.zheng.android;
import java.util.function.UnaryOperator;


 
/**
 * This class extends the Mover class to implement a problem solving framework 
 * by creating, displaying, and doing the four arithmetic states that is involved 
 * in this application.
 * 
 * @author Calvin Zheng
 */
 
public class FarmerMover extends Mover {
     
    public static final String goesAlone = "Farmer Goes Alone";
    public static final String takesWolf = "Farmer Takes Wolf";
    public static final String takesGoat = "Farmer Takes Goat";
    public static final String takesCabbage = "Farmer Takes Cabbage";
 //   public static final String takesGorilla = "Farmer Takes Gorilla";
     
            public FarmerMover() {
            //super.addMove(goesAlone, s -> tryAlone(s));
                super.addMove(goesAlone, new UnaryOperator<State>() {
                    @Override
                    public State apply(State state) {
                        return tryAlone(state);
                    }
                });
            //super.addMove(takesWolf, s -> tryWolf(s));
                super.addMove(takesWolf, new UnaryOperator<State>() {
                    @Override
                    public State apply(State state) {
                        return tryWolf(state);
                    }
                });
            //super.addMove(takesGoat, s -> tryGoat(s));
                super.addMove(takesGoat, new UnaryOperator<State>() {
                    @Override
                    public State apply(State state) {
                        return tryGoat(state);
                    }
                });
            //super.addMove(takesCabbage, s -> tryCabbage(s));
                super.addMove(takesCabbage, new UnaryOperator<State>() {
                    @Override
                    public State apply(State state) {
                        return tryCabbage(state);
                    }
                });
 //           super.addMove(takesGorilla, s-> takesGorilla(null));
        }
             
            private State tryAlone(State state) {
                FarmerState currentState = (FarmerState) state;
                FarmerState aloneState = new FarmerState("East", "East", "West", "East");
                FarmerState anotherState = new FarmerState("East", "West", "East", "West");
                if(currentState.equals(aloneState)) {
                    return new FarmerState("West", "East", "West", "East");
                }
                else if(currentState.equals(anotherState)) {
                    return new FarmerState("West", "West", "East", "West");
                }
                return null;
            }
             
            private State tryWolf(State state) {
//                FarmerState currentState = (FarmerState) state;
//                FarmerState stateOne = new FarmerState("West", "West", "East", "West");
//                FarmerState stateTwo = new FarmerState("East" ,"East", "East", "West");
//                FarmerState stateThree = new FarmerState("West", "West", "West", "East");
//                if(currentState.equals(stateOne)) {
//                    return new FarmerState("East", "East", "East", "West");
//                }
//                else if (currentState.equals(stateTwo)) {
//                    return new FarmerState("West", "West", "East", "West");
//                }
//                else if(currentState.equals(stateThree)) {
//                    return new FarmerState("East", "East", "West", "East");
//                }
//                return null;
                   FarmerState currentState = (FarmerState) state;
                FarmerState returnState = null;
                try{
                    if(!currentState.getFarmer().equals(currentState.getWolve())){
                        
                    }
                    else if(currentState.getFarmer().equals("West") && !currentState.getCab().equals(currentState.getGoat())){
                        returnState = new FarmerState("East","East",currentState.getGoat(),currentState.getCab());
                    }else if(currentState.getFarmer().equals("East")&& !currentState.getCab().equals(currentState.getGoat())){
                        returnState = new FarmerState("West","West",currentState.getGoat(),currentState.getCab());
                    }else{
                        throw new Exception("Invalid FarmerState or WolfState");
                    }
     
                }
                catch(Exception e){
                    System.out.println(e);
                }
                return returnState;

            }
             
            private State tryGoat(State state) {
//                FarmerState currentState = (FarmerState) state;
//                FarmerState stateOne = new FarmerState("West", "West", "West", "West");
//                FarmerState stateTwo = new FarmerState("East", "West", "East", "West");
//                FarmerState stateThree = new FarmerState("East", "West", "East", "East");
//                FarmerState stateFour = new FarmerState("West", "East", "West", "East");
//                FarmerState stateFive = new FarmerState("East","East","East","West");
//                if(currentState.equals(stateOne)) {
//                    return new FarmerState("East", "West", "East", "West");
//                }
//                else if (currentState.equals(stateTwo)) {
//                    return new FarmerState("West", "West", "West", "West");
//                }
//                else if (currentState.equals(stateThree)) {
//                    return new FarmerState("West", "West", "West", "East");
//                }
//                else if (currentState.equals(stateFour)) {
//                    return new FarmerState("East", "East", "East", "East");
//                }
//                else if(currentState.equals(stateFive)){
//                    return 
//                }
//                return null;
                FarmerState currentState = (FarmerState) state;
                FarmerState returnState = null;
                try{
                    if(!currentState.getFarmer().equals(currentState.getGoat())){
                        
                    }
                    else if(currentState.getFarmer().equals("West")){
                        returnState = new FarmerState("East",currentState.getWolve(),"East",currentState.getCab());
                    }else if(currentState.getFarmer().equals("East")){
                        returnState = new FarmerState("West",currentState.getWolve(),"West",currentState.getCab());
                    }else{
                        throw new Exception("Invalid FarmerState or GoatState");
                    }
     
                }
                catch(Exception e){
                    System.out.println(e);
                }
                return returnState;

            }
             
            private State tryCabbage(State state) {
                FarmerState currentState = (FarmerState) state;
                FarmerState stateOne = new FarmerState("West", "East", "West", "West");
                FarmerState stateTwo = new FarmerState("West", "West", "East", "West");
                if(currentState.equals(stateOne)) {
                    return new FarmerState ("East", "East", "West", "East");
                }
                else if (currentState.equals(stateTwo)) {
                    return new FarmerState("East", "West", "East", "East");
                }
                return null;
            }
             
            private State takesGorilla(State state) {
                FarmerState currentState = (FarmerState) state;
                FarmerState stateOne = new FarmerState("West", "West", "West", "West");
                if(currentState.equals(stateOne)) {
                    return stateOne;
                }
                return null;
            }
             
            private State illegalMove(State state) {
                return null;
        }
 
} 