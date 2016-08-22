import java.util.*;

/**
 * Simple class to simulate the behavior of the program + establish enums
 */

public class Sim {

    /**
     * I have made use of enums to generate random cars.
     * For brevity, I have not included the model of specific car models, yet
     * this would be simple to implement.
     */
    public enum CarMake {
        FORD, NISSAN, TOYOTA, FERRARI;
        private static final List<CarMake> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static String randomCarMake() {
            return VALUES.get(RANDOM.nextInt(SIZE)).name();
        }
    }

    public enum CarColor {
        RED, BLUE, YELLOW, WHITE, SILVER, BLACK, PURPLE;
        private static final List<CarColor> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static String randomCarColor() {
            return VALUES.get(RANDOM.nextInt(SIZE)).name();
        }
    }

    /**
     * Generates list of random cars (zero indexed)
     * @param size : list size
     * @return Car List
     */
    public List<Car> getCars(int size){
        List<Car> cars = new ArrayList<>();
        for(int i = 0; i < size; i++){
            Car car = new Car(CarColor.randomCarColor(), CarMake.randomCarMake());
            cars.add(car);
            car.print();
        }
        return cars;
    }

    public static void main(String[] args) {
        int SIZE = 4000000; //could also be passed in a string arg, then converted to int in a try/catch parse
        Sim sim = new Sim();
        List<Car> cars = sim.getCars(10000000);
        Searchable carSearch = new CarSearch(cars);

        //proper unit tests would be made for production code

        /********************** COLOR SEARCH *********************/
        carSearch.setSearchable(Car.COLOR);
        carSearch.setSearchable(Car.COLOR); // test to confirm the hashmap is not being rebuilt

        List<String> strList = new ArrayList<>();
        strList.add(CarColor.BLUE.name());
        strList.add(CarColor.RED.name());   // test for union
        strList.add("REDD");                // test to handle a key that was never entered

        System.out.println("\nNow printing colors: ");
        List<Integer> list = carSearch.getObject(Car.COLOR,strList);
        for(Integer i : list) System.out.println(i);

        /********************** MAKE SEARCH **********************/
        carSearch.setSearchable(Car.MAKE);
        List<String> strList2 = new ArrayList<>();
        strList2.add(CarMake.FORD.name());
        System.out.println("\nNow printing makes: ");
        List<Integer> list2 = carSearch.getObject(Car.MAKE,strList2);
        for(Integer i : list2) System.out.println(i);

        /**
         * There is also the question of how to go about making a query like
         * "select all fords and toyotas that are red or blue"
         * an optimal way to make such a union/intersection could be made if we could determine
         * the smallest set that would be returned from each distinct property. So for example, in the
         * example query previously mentioned, we would want to get the smallest property union. Lets say
         * the set of red and blue cars contain 1000 less items than the set of fords and toyotas. We could
         * take the smaller set returned from the color query, and filter out all cars that are not fords
         * or toyotas (checks could be made with a hashset for O(1) access). This entire process would cost
         * O(n) time to do under the current convention of an 'unsorted' integer arraylist.
         *
         */
    }
}
