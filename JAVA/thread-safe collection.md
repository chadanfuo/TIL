

[JAVA] 동기화된 컬렉션(thread-safe collection), 병렬처리 가능한 컬렉션


 
동기화된 컬렉션(thread-safe한 collection), 병렬처리 컬렉션
컬렉션 프레임워크 대부분 싱글 스레드 환경에서 사용할 수 있도록 설계

Vector, Hashtable은 동기화된 (synchronized) 메소드로 구성되어 있기 때문에 멀티 스레드 환경에서 안전함

하지만 ArrayList, HashSet, HashMap 은 멀티스레드 환경에서 안전하지 않다.

 

따라서 자바에서는 Collections의 synchronizedXXX() 메소드를 제공한다.

매개값으로 컬렉션을 대입하면 동기화된 컬렉션을 리턴한다.

리턴 타입	메소드(매개변수)	설명

List	synchronizedList(List list)	List를 동기화된 List로 리턴
Map<K,V>	synchronizedMap(Map<K,V> m)	Map을 동기화된 Map으로 리턴
Set	synchronizedSet(Set s)	Set을 동기화된 Set으로 리턴
List<String> list = Collections.synchronizedList(new ArrayList<String>());
// 이런식으로 thread-safe한 리스트를 만들 수 있다.
 

하지만 단점이 있었으니...!

동기화된(synchronized) 컬렉션은 멀티 스레드 환경에서 하나의 스레드가 요소를 안전하게 처리하도록 도와주지만, 전체 요소를 빠르게 처리하지는 못한다. 왜냐하면 스레드가 작업을 할때 락이 발생하기 때문이다. (스레드가 병렬적으로 요소들을 처리할 수 없음)

자바에서는 멀티스레드환경에서 안전하면서도, 스레드가 병렬적으로 작업을 처리할 수 있도록 java.util.concurrent 패키지에서 ConcurrentHashMap, ConcurrentLinkedQueue를 제공한다.


 
이 구현체들은 부분(segment) 잠금을 사용하기 때문에 병렬적으로 작업 수행이 가능하다.

예를 들면, Map에 10개의 요소가 저장되어 있을 경우, 1개의 요소를 처리할 때 그 처리하는 곳이 포함된 부분만 락을 걸고 나머지부분은 다른스레드가 변경할 수 있게 하는 것이다. (1개의 요소를 처리할 때 나머지 10개 요소에 락을 거는것이 전체 잠금)

Map<K,V> map = new ConcurrentHashMap<K,V>();
Queue<E> queue = new ConcurrentQueue<E>();
 

 


RF:https://cornswrold.tistory.com/209
