package ticketing;

import java.util.HashSet;
import java.util.Set;

public class Component {
	String name;
	String path;
	Component parentComponent=null;
	Set<Component> subComponents=new HashSet<>();
	Set<String> subComponentsNames=new HashSet<>();
	
	public Component(String name) {
		this(name,null);
	}
	
	public Component(String name,Component parentComponent) {
		this.name=name;
		this.parentComponent=parentComponent;
		this.path=((parentComponent==null)? "":(parentComponent.getPath())) + "/" +name;
	}
	
	public String getName() {
		return name;
	}
	public Component getParentComponent() {
		return parentComponent;
	}
	public String getPath() {
		return path;
	}
	public Set<Component> getSubComponents() {
		return subComponents;
	}
	public Set<String> getSubComponentsNames() {
		return subComponentsNames;
	}
	
	public void addSubComponent(Component component) {
		this.subComponents.add(component);
		this.subComponentsNames.add(component.getName());
	}
}
