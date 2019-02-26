package interview.streams;

import java.util.stream.IntStream;

public class Streams {



    public static void main(String[] args) {

        IntStream intStream = IntStream.of(1,2,3,4,7765,5);

        long count = intStream.count();

        System.out.print(count);


    }

}
