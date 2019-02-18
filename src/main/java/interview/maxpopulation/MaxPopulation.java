package interview.maxpopulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxPopulation {

    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();
        people.add(new Person(1234, 2342));
        people.add(new Person(1234, 1500));
        people.add(new Person(1234, 2142));
        people.add(new Person(1234, 1342));
        people.add(new Person(1234, 1242));
        people.add(new Person(234, 442));
        people.add(new Person(1234, 2222));
        people.add(new Person(1000, 1222));

        System.out.println(findMaxPopulation(people));
    }

    static int findMaxPopulation(List<Person> people) {
        int max = 0;
        Map<Integer, Integer> population = new HashMap<>();

        for (int personNumber = 0; personNumber < people.size(); personNumber++) {
            Person person = people.get(personNumber);
            for (int year = person.yearOfBirth; year <= person.getYearOfDeth(); year++) {
                if (population.get(year) == null) {
                    population.put(year, 0);
                }
                int currentPopulation = population.get(year);
                population.put(year, currentPopulation + 1);
                if (population.get(year) > max) {
                    max = population.get(year);
                }
            }
        }

        return max;
    }

    static class Person {

        private int yearOfBirth;
        private int yearOfDeth;

        Person(int yearOfBirth, int yearOfDeth) {
            if (yearOfDeth < yearOfBirth) {
                throw new IllegalArgumentException("impossible");
            }
            this.yearOfBirth = yearOfBirth;
            this.yearOfDeth = yearOfDeth;
        }

        public int getYearOfBirth() {
            return yearOfBirth;
        }

        public void setYearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
        }

        public int getYearOfDeth() {
            return yearOfDeth;
        }

        public void setYearOfDeth(int yearOfDeth) {
            this.yearOfDeth = yearOfDeth;
        }
    }

}
