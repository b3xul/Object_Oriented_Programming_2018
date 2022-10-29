package trail;

class Passage {
	Location l;
	Runner r;
	long timestamp;

	public Passage(Location l, Runner r) {
		this.l = l;
		this.r = r;
		timestamp = System.currentTimeMillis();
		r.addPassage(this);
		l.addPassage(this);
	}
	
	Runner getRunner(){return r;}
	Location getLocation(){return l;}
	long getTime(){return timestamp;}

}
