package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

    private final String immatriculation;
    private final List<Stationnement> myStationnements = new LinkedList<>();

    public Voiture(String i) {
        if (null == i)
            throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
        
        immatriculation = i;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

   
    public void entreAuGarage(Garage g) throws Exception {
        // Et si la voiture est déjà dans un garage ?
       if(estDansUnGarage()){
           throw new IllegalArgumentException("La voiture est dans un garage");
       }else{
        
        Stationnement s = new Stationnement(this, g);
        myStationnements.add(s);
        }
    }
    

    public void sortDuGarage() throws Exception {
        int i = myStationnements.size();
        if (myStationnements.get(i-1).getFin()==null){
            myStationnements.get(i-1).terminer();
        }else{
            throw new IllegalArgumentException("La voiture doit être dans un garage");
        }

    }

    /**
     * @return l'ensemble des garages visités par cette voiture
     */
    public Set<Garage> garagesVisites() {
        Set<Garage> result = new HashSet<>();
        for (Stationnement x : myStationnements){
            result.add(x.getGarage());
            
                    }
        return result;
    }

    /**
     * @return vrai si la voiture est dans un garage, faux sinon
     */
    public boolean estDansUnGarage() {
        if (myStationnements.isEmpty()){
            return false;
        }else{
            int nbr=myStationnements.size();
            Stationnement dernier = myStationnements.get(nbr-1);
            return dernier.estEnCours();
        }
        
    }
    
   
    public void imprimeStationnements(PrintStream out) {
        for (int i =0; i<garagesVisites().size(); i++){
           out.println(myStationnements.get(i).getGarage());
           for(int j = 0; j <myStationnements.size(); j++){
               if(myStationnements.get(i).getGarage().equals(myStationnements.get(j).getGarage())){
                   out.println(myStationnements.get(j).toString());
               }
           }
        }
    }

}
