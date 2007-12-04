package hotgammon.view;

import hotgammon.domain.Color;
import hotgammon.domain.Location;

public class StateChange {
	Location from;
	Location to;
	Color c;

	StateChange(Location l, Location t, Color col) {
		from = l;
		to = t;
		c = col;
	}
}
