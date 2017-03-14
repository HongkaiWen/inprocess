package java8.lab;

import java8.entity.BirdMan;
import java8.intf.ManAction;

/**
 * Created by hongkai on 2017/3/14.
 */
public class MultipleExtendsPlay {

    public static void main(String args[]){
        BirdMan birdMan = new BirdMan();
        ManAction.hi(birdMan);
    }

}
