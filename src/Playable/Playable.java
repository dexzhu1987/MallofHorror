package Playable;

import java.util.*;
import Character.*;

public class Playable {
    protected String name;
    protected String color;
    List<GameCharacter> characters;
    List<GameCharacter> charactersselect;

    public Playable() {
        name = "";
        color = "";

        characters = new ArrayList<>();
        characters.add(new GunMan());
        characters.add(new ToughGuy());
        characters.add(new Model());

        charactersselect = new ArrayList<>();
        charactersselect.add(new GunMan());
        charactersselect.add(new ToughGuy());
        charactersselect.add(new Model());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int remaingCharacter() {
        return characters.size();
    }

    public List<GameCharacter> getGameCharacters() {
        return characters;
    }

    public GameCharacter choose(String charname) {
        int q = 0;
        for (int i = 0; i < characters.size(); i++) {
            if (charname.equalsIgnoreCase(characters.get(i).getName())) {
                q = i;
            }
        }
        return characters.get(q);
    }

    public void chardeath(String charname) {
        int q=0;
        for (int i = 0; i < charactersselect.size(); i++) {
            if (charname.equalsIgnoreCase(characters.get(i).getName())) {
                q=i;
            }
        };
        characters.remove(q);
    }

    public List<GameCharacter> getCharactersselect() {
        return charactersselect;
    }

    public String getCharactersselectstr() {
        String fin="";
        if (charactersselect.size()>1){
            for (int i=0; i<charactersselect.size(); i++){
                if (i==charactersselect.size()-1){
                    fin+=charactersselect.get(i).getName();
                }
                else {
                    fin+=charactersselect.get(i).getName()+ " and ";
                }
            }
        }
        else if(charactersselect.size()==1){
            fin += charactersselect.get(0).getName();
        }
        return fin;
    }

    public GameCharacter selectchoose(String charname) {
        int q = 0;
        for (int i = 0; i < charactersselect.size(); i++) {
            if (charname.equalsIgnoreCase(charactersselect.get(i).getName())) {
                q = i;
            }
        }
        return charactersselect.get(q);
    }

    public void selectchooseremove(String charname) {
        int q=0;
        for (int i = 0; i < charactersselect.size(); i++) {
            if (charname.equalsIgnoreCase(charactersselect.get(i).getName())) {
                q=i;
            }
        };
        charactersselect.remove(q);
    }




    @Override
    public String toString() {
       return  "Player " + color + ": " + name;
    }



    public static void main(String[] args) {
        Playable p1 = new Player();
        System.out.println( p1.choose("Gunman"));
        p1.chardeath("Tough guy");
        System.out.println(p1.remaingCharacter());
        System.out.println(p1.getGameCharacters());

        System.out.println();
        System.out.println(p1.selectchoose("Model"));
        System.out.println(p1.getCharactersselect());
        p1.selectchooseremove("Gun Man");
        System.out.println(p1.getCharactersselect());
        p1.selectchooseremove("Tough Guy");
        System.out.println(p1.getCharactersselect());
        p1.selectchooseremove("Model");
        System.out.println(p1.getCharactersselect());
    }


}
