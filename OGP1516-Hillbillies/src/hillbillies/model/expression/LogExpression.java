package hillbillies.model.expression;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;

/**
*
*
* @author Lukas Van Riel
* @version 1.0
*
*/
public class LogExpression extends PositionExpression<Coordinate> {

	public LogExpression(Unit unit) {
		this.thisUnit = unit;
		this.logPosition = determineLogPosition();
	}
	private final Unit thisUnit;
	private final Coordinate logPosition;
	
	@Override
	public Coordinate evaluate() {
		return this.logPosition;
	}

	public Coordinate determineLogPosition(){
		Coordinate position = thisUnit.getInWorldPosition();
		World world = thisUnit.getWorld();
		Log someLog = null;
		Set<Coordinate> coordinateSet = new HashSet<Coordinate>();
		coordinateSet.add(position);
		while (someLog == null){
			for (Coordinate c: coordinateSet){
				Coordinate[] clist = c.adjacentCoordinates();
				for (Coordinate cc: clist){
					if (! coordinateSet.contains(cc))
						coordinateSet.add(cc);
				}
			}
			for (Log worldLog: world.getLogSet()){
				if (coordinateSet.contains(worldLog.getPosition()))
					someLog = worldLog;
			} 
		}	
		Coordinate place = someLog.getPosition();
		return place;
	}

	@Override
	public boolean check() throws FormException{
		return true;
	}

}
