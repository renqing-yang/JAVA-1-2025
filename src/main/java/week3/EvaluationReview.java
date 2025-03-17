package week3;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * java 8
 *
 */
class MyStream {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 2, 3, 4, 2);
//        Set<Integer> set = list.stream().collect(
//                HashSet::new,
//                HashSet::add,
//                (l1, l2) -> l1.addAll(l2)
//        );
//        System.out.println(set);
        Map<Integer, Integer> map = list.stream().collect(
                HashMap::new,
                (col, ele) -> col.merge(ele, 1, Integer::sum),
                (l1, l2) -> {}
        );
        System.out.println(map);
//
//        IntStream.range(0, 10)
//                .mapToObj(idx -> arr[idx])
//                .map()
    }
}
/**
 *  list.stream().map(x -> x * 2).filter().collect()..
 *  ReferencePipeline(supplier: () -> list.iterator()) < - > ReferencePipeline (map: x -> x * 2) < - > RP(filter .. ) <-> terminal
 *
 *  Sink(iterator){nextSink.accept(ele)}  -> Sink(x -> x * 2)  ->  Sink(filter)  ->  Sink(collect)
 *
 *
 *  begin
 *  accept
 *  end
 */


/**
 * how to impl "Fair Lock"
 *
 * 1. Abstract queued synchronizer => waiting list for thread (thread safe linked list)
 * 2. check queue first
 *      if queue is empty => try lock
 *      else => enqueue
 */


/**
 * today's questions you need to know
 * 1. coding: impl blocking queue
 * 2. coding: java 8 stream api on whiteboard
 * 3. how does java 8 stream api work
 * 4. how to impl fair lock
 * 5. diff ReentrantLock and Synchronized
 * 6. Semaphore , CountdownLatch, CyclicBarrier
 * 7. How does ConcurrentHashmap work
 * 8. what is fail fast
 * 9. what is ConcurrentModificationException
 * 10. how to create customized annotation
 * 11. memory leak and out of memory error
 *      young gen   minor gc
 *      old   gen   major gc
 *      full gc = minor gc + major gc
 *
 *      out of memory error
 *      1. restart app
 *      2. assign more area to young / old gen
 *          java -jar xx --XMS=....
 *      3. check memory leak -> check "heap dump"
 *          JProfiler
 *          Java mission control
 *          Memory Analyzer
 *      4. SoftReference
 *         WeakReference
 *         PhantomReference + Reference Queue
 *
 * 12. why java pass by value not reference
 *      public void dfs(Map<...> object)
 *
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyA{
    String type() default "abc";
}

@MyA(type = "cd")
class AnnotationTest {

    public void get() {}

    public static void main(String[] args) throws Exception {
        Class<?> clazz = AnnotationTest.class;
        Method m = clazz.getDeclaredMethod("get");
        Annotation[] annotations = m.getDeclaredAnnotations();
        System.out.println(Arrays.toString(annotations));
        MyA myA = (MyA) annotations[0];
        System.out.println(myA.type());
    }
}

