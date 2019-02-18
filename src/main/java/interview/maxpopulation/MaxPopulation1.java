package interview.maxpopulation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxPopulation1 {

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
        int maximumPopulation = 0;
        int currenPopulation = 0;
        List<Event> timeline = new ArrayList<>();
        for (int personNumber = 0; personNumber < people.size(); personNumber++) {
            Person person = people.get(personNumber);
            timeline.add(new Event(person.yearOfBirth, EVENT_TYPE.BIRTH));
            timeline.add(new Event(person.yearOfDeth, EVENT_TYPE.DETH));
        }
        timeline.sort(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.year - o2.year;
            }
        });
        for (int i = 0; i < timeline.size() - 1; i++) {
            Event e = timeline.get(i);
            if (e.type == EVENT_TYPE.BIRTH) {
                currenPopulation++;
                if (maximumPopulation < currenPopulation) {
                    maximumPopulation = currenPopulation;
                }
            } else {
                currenPopulation--;
            }

        }
        return maximumPopulation;
    }

    enum EVENT_TYPE {
        BIRTH, DETH;
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

    static class Event implements Comparable<Event> {
        int year;
        EVENT_TYPE type;

        Event(int year, EVENT_TYPE type) {
            this.year = year;
            this.type = type;
        }

        @Override
        public int compareTo(Event o) {
            return this.year - o.year;
        }
    }
}
