package Lab5;

public class Q9 {
}



/*

Push sequence: 5, 3, 7, 2
Push(5) → min stack = [5]
Push(3) → min stack = [5, 3]
Push(7) → min stack = [5, 3] (7 > 3, so don’t push)
Push(2) → min stack = [5, 3, 2]
Now
getMinimum() → returns 2
 */