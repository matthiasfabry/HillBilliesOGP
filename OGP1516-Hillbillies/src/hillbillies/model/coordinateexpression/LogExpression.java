package hillbillies.model.coordinateexpression;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Coordinate;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.expression.PositionExpression;

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
		Coordinate[] coordinatelist = position.adjacentCoordinates();
		Set<Coordinate> coordinateSet = new HashSet<Coordinate>();
		for (Coordinate c: coordinatelist){
			coordinateSet.add(c);
		}
		while (someLog == null){
			for (Log worldLog: world.getLogSet()){
				if (coordinateSet.contains(worldLog.getPosition()))
					someLog = worldLog;
			} 
			for (Coordinate cc: coordinateSet){
				Coordinate[] clist = cc.adjacentCoordinates();
				for (Coordinate ccc: clist){
					if (! coordinateSet.contains(ccc))
						coordinateSet.add(ccc);
				}
			}	
		}	
		Coordinate place = someLog.getPosition();
		return place;
	}

}
