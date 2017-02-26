import java.util.Observer;

//the map interface, just display of the map
public interface Map {
	//render map, buildings, team interface
	public void map_init();

	//spwan minions
	public void spawn_minions();

	//display minion action, animation
	public void display_minions();

}