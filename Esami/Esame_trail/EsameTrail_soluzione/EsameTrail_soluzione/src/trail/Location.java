package trail;

import java.util.LinkedList;
import java.util.List;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class Location {
	private String name;
	private int pos;
	List<Delegate> delegates = new LinkedList<>();
	List<Passage> passages = new LinkedList<>();

    public Location(String name, int pos) {
    		this.name = name;
    		this.pos = pos;
	}

	public String getName(){
        return name;
    }

    public int getOrderNum(){
        return pos;
    }

	public void addDelegate(Delegate d) {
		delegates.add(d);
	}


	public List<String> getDelegates() {
		return delegates.stream()
				.sorted(comparing(Delegate::getSurname)
						.thenComparing(Delegate::getName))
				.map(Delegate::toString)
				.collect(toList());
	}

	public void addPassage(Passage passage) {
		passages.add(passage);
	}

	public long getPassTime(Runner r) {
//		return passages.stream().filter(p -> p.r==r)
//				.mapToLong(p -> p.timestamp).findFirst().orElse(-1);
		// OR
		for(Passage p : passages){
			if(p.r == r){
				return p.timestamp;
			}
		}
		return -1;
		
	}

	public boolean hasDelegate(Delegate d) {
		return delegates.contains(d);
	}
}
