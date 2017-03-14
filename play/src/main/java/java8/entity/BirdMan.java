package java8.entity;

import java8.intf.BirdAction;
import java8.intf.ManAction;

/**
 * Created by hongkai on 2017/3/14.
 */
public class BirdMan implements BirdAction, ManAction {

    @Override
    public void breath() {

    }

    @Override
    public void sayHi() {
        ManAction.super.sayHi();
    }

}
