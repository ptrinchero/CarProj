import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Multimap;

/**
 * This class implements the Searchable interface to essentially query a list of car objects
 * according to specific parameters. Should the Car class be modified in the future to contain
 * many more member variables, I would believe it wise to work with some type of database
 * that best fits the needs of the program objectives and constraints.
 *
 * In writing this, I had some internal debate as to the proper course of design. A singleton
 * pattern came to mind, but its known issues (e.g. testing) and taboo nature made me hesitant. The uncertainty of the
 * nature of what exactly is unmodifiable (i.e. cars, or the car list, or both) furthered doubt. If CarSearch is
 * to be used throughout the life of the main program, I believe a singleton pattern may make sense,
 * despite the problem with garbage collection (which could also cause problems).
 *
 * If the rest of the program (I'm programming under the assumption this would act as a module of something
 * larger) was well designed, few or even one instance of this class could be instantiated in underlying base
 * classe(s) from which access could extend.
 *
 * It also appears Google's Guava has something of a similar interface that could be used.
 *
 * Pardon this verbose description :)
 */

public class CarSearch implements Searchable {

    private List<Car> cars;

    private HashMap<String, List<Integer>> makeMap = new HashMap<>();
    private HashMap<String, List<Integer>> colorMap = new HashMap<>();
    //private HashMap<String, List<Integer>> modelMap = new HashMap<>();

    private HashMap<String, HashMap<String,List<Integer>>> link;

    public CarSearch(List<Car> cars) {
        this.cars = cars;
        link = new HashMap<>();
        link.put("color",colorMap);
        link.put("make",makeMap);
        //link.put("model",modelMap);
    }

    /**
     * Generates a HashMap for the specific property we are looking for.
     * setSearchable should only be called to initially create the HashMap from the
     * constructor parameter's contents. Should the HashMap for a certain property already
     * exists from inserted data, a new update method should be created if there is such a need.
     */
    public void setSearchable(String propertyName) {
        HashMap<String,List<Integer>> hmap = link.get(propertyName);
        if(!hmap.isEmpty()){
            System.out.println("___ no need to rebuild :) ___");
            return;
        }
        for(int i = 0; i < cars.size(); i++){
            if(!hmap.containsKey(cars.get(i).getProperty(propertyName))) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                hmap.put(cars.get(i).getProperty(propertyName), list);
            }else{
                hmap.get(cars.get(i).getProperty(propertyName)).add(i);
            }
        }
    }

    /**
     * Modified the second paramter to accept an array list, so queries such as
     * 'get all the indexes of Ford cars that are red or blue'
     *
     * setSearchable must be called before getObject for a specific property type
     */
    public List<Integer> getObject(String propertyName, List<String> value){
        List<Integer> list = new ArrayList<>();
        for(String s : value){
            if(link.get(propertyName).get(s) != null)
                list.addAll(link.get(propertyName).get(s));
        }
        return list;
    }
}
