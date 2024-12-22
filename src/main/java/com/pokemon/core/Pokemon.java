package com.pokemon.core;

import java.util.ArrayList;

import com.pokemon.managers.EvolutionsManager;
import com.pokemon.managers.MovesManager;

public class Pokemon{
    private String name;
    private int hp;
    private int battlehp;
    private int exp;
    private int expToNextLevel;
    private int level;
    private int  attack;
    private int defense;
    private int speed;
    private ArrayList<String> type;
    private ArrayList<Move> moves;
    private ArrayList<Observer> observers;

    // costruttore per creare un pokemon
    public Pokemon(String n, int hp, int exp, int expToNextLevel, int level, int attack, int defense, int speed, ArrayList<String> type, ArrayList<Move> moves) {
        this.name = n;
        this.hp = hp;
        this.battlehp = hp;
        this.exp = exp;
        this.expToNextLevel = expToNextLevel;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.type = type;
        this.moves = moves;
        ArrayList<Observer> obs = new ArrayList<>();
        observers.add(EvolutionsManager.getInstance());
        observers.add(MovesManager.getInstance());
        this.observers = obs;
    }

    // costruttore per generare un copia esatta del pokemon
    public Pokemon(Pokemon p) {
        this.name = p.getName();
        this.hp = p.getHp();
        this.battlehp = p.getHp();
        this.exp = p.getExp();
        this.expToNextLevel = p.getExpToNextLevel();
        this.level = p.getLevel();
        this.attack = p.getAttack();
        this.defense = p.getDefense();
        this.speed = p.getSpeed();
        this.type = p.getType();
        this.moves = p.getMoves();
        this.observers = p.observers;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getExp() {
        return exp;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public int getLevel() {
        return level;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public void removeMove(int indx) {
        moves.remove(indx);
    }

    public void resetBattleHp() {
        this.battlehp = this.hp;
    }

    public int getBattleHp() {
        return this.battlehp;
    }

    public Boolean isFainted() {
        return this.battlehp <= 0;
    }

    public void getDamaged(int damage) {
        this.battlehp-=damage;
    }

    public void levelUp(int extraExp) {
        this.level++;
        this.exp = extraExp;
        this.expToNextLevel = (int) (this.expToNextLevel * 1.1);
        this.hp = (int) (this.hp * 1.1);
        this.attack = (int) (this.attack * 1.1);
        this.defense = (int) (this.defense * 1.1);
        this.speed = (int) (this.speed * 1.1);
        this.battlehp = this.hp;
        notifyObservers(); // notifico che è avvenuto il level up
    }

    public void addExp(int exp) {
        if(this.exp + exp < expToNextLevel) {
            this.exp += exp;
        } else {
            int extraExp = this.exp + exp - expToNextLevel;
            levelUp(extraExp);
        }
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }


    // notifico tutti gli observers
    private void notifyObservers() {
        //
        //for (Observer observer : observers) {
            //observer.update(this);
        //}

        EvolutionsManager.getInstance().update(this);
        MovesManager.getInstance().update(this);
    }

    @Override
    public String toString() {
        return name;
    }
}