
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SHADOW
 */
public class myclass {
    public double C;
    public ArrayList<Double> E=new ArrayList<>(), Errer=new ArrayList<>();
    private int indice;
    public myclass(double c){
        this.C=c;
    }
    public void add_E(double d){
        E.add(d);
    }
    public double min(){
        double min=Errer.get(0);
        for(int i=0;i<Errer.size();i++){
            if(min>Errer.get(i)) {
                min=Errer.get(i);
                indice=i;
            }
        }
        return min;
    }
    public Double get_E(){
        return E.get(indice);
    }
    public void add_erreur(double e){
        Errer.add(e);
    }
}
