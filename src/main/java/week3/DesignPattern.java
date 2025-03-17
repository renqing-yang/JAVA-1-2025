package week3;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton
 */
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}

class LazyLoadingSingleton {
    private static volatile LazyLoadingSingleton instance;

    private LazyLoadingSingleton() {}

    public static LazyLoadingSingleton getInstance() {
        if(instance == null) {
            //T1 T2 T3
            synchronized (LazyLoadingSingleton.class) {
                if(instance == null) {
                    instance = new LazyLoadingSingleton();
                }
            }
        }
        return instance;
    }
}

class SingletonReflectionIssue {
    public static void main(String[] args) throws Exception {
        Class clazz = LazyLoadingSingleton.class;
        Constructor constructor = clazz.getDeclaredConstructors()[0];
        System.out.println(constructor);
        constructor.setAccessible(true);
        LazyLoadingSingleton a = (LazyLoadingSingleton) constructor.newInstance();
        LazyLoadingSingleton b = LazyLoadingSingleton.getInstance();
        System.out.println(a == b);
    }
}
enum EnumSingleton {
    INSTANCE;
}

/**
 * Builder
 *
 * option1:
 *  Week3Student stu = new Week3Student().setName("..").setAge(xx);
 *
 * option2:
 *  Week3Student stu = new Week3StudentBuilder().setName("..").setAge(xx).build();
 */
class BuilderTest {
    public static void main(String[] args) {
        Week3Student stu1 = new Week3Student().setName("abc").setAge(5);
        System.out.println(stu1);

        Week3Student stu2 = new Week3StudentBuilder().setName("dce").setAge(5).build();
        System.out.println(stu2);
    }
}
class Week3StudentBuilder {
    private String name;
    private int age;

    public Week3StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Week3StudentBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public Week3Student build() {
        return new Week3Student(name, age);
    }
}
class Week3Student {
    private String name;
    private int age;

    public Week3Student() {
    }

    public Week3Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Week3Student setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Week3Student setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "Week3Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * factory
 *
 * class XXFactory {
 *     public static XX getXX() {
 *         return new XX();
 *     }
 * }
 */

/**
 * Facade
 *      -> entry point ->
 *                     ->
 *                     ->
 */

/**
 * Observer
 */
class Sub {
    public void receive(String msg) {
        //..
    }
}
class Topic {
    private final List<Sub> subList = new ArrayList<>();

    public void publish(String msg) {
        subList.forEach(s -> s.receive(msg));
    }
}

/**
 *  Prototype => cloneable
 */

/**
 *  Strategy
 *
 *  list.map(Function).collect();
 */
@FunctionalInterface
interface CalculationStrategy {
    int execute(int a, int b);
}

interface Calculator {
    int calculate(int a, int b, CalculationStrategy strategy);
}
class CalculatorImpl implements Calculator {
    @Override
    public int calculate(int a, int b, CalculationStrategy strategy) {
        return strategy.execute(a, b);
    }

    public static void main(String[] args) {
        CalculatorImpl calculator = new CalculatorImpl();
        System.out.println(calculator.calculate(1, 2, (a, b) -> a * b));
    }
}

/**
 * Template: Polymorphism
 */

/**
 * Bridge pattern
 */
class Week3A {
    private Week3B b;

    public Week3A(Week3B b) {
        this.b = b;
    }
}

class Week3B {}


/**
 * Is-A : Inheritance
 *
 * Has-A concept : Bridge Pattern
 *
 */


/**
 *  Proxy
 */

class CalculatorImplProxy extends CalculatorImpl implements Calculator {
    @Override
    public int calculate(int a, int b, CalculationStrategy strategy) {
        System.out.println("before");
        int res = super.calculate(a, b, strategy);
        System.out.println(res);
        System.out.println("after");
        return res;
    }

    public static void main(String[] args) {
        Calculator calculator = new CalculatorImplProxy();
        calculator.calculate(1, 2, (a, b) -> a + b);
    }
}

/**
 *  Dynamic Proxy
 */
class MyInvocationHandler implements InvocationHandler {
    private final Object obj;

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Integer res = (Integer)method.invoke(obj, args);
        System.out.println(method);
        System.out.println(res);
        System.out.println("after");
        return res;
    }
}
class DPTest {
    public static void main(String[] args) {
        Calculator calculator = (Calculator) Proxy.newProxyInstance(
                DPTest.class.getClassLoader(),
                new Class[]{Calculator.class},
                new MyInvocationHandler(new CalculatorImpl())
        );
        calculator.calculate(1, 2, (a, b) -> a + b);
    }
}


/**
 * Questions
 * 1. why singleton
 * 2. diff factory and builder
 * 3. why factory
 * 4. coding: impl double check thread safe singleton class
 * 5. issues with singleton implementations : Reflection issue, Cloneable issue, Serializable issue.
 *
 */



