public class processRecord {
    TowerPoint startTowerPoint, endTowerPoint;

    public processRecord(TowerPoint start, TowerPoint end) {
        startTowerPoint = start;
        endTowerPoint = end;
    }

    public TowerPoint getStartTowerPoint() {
        return startTowerPoint;
    }

    public TowerPoint getEndTowerPoint() {
        return endTowerPoint;
    }
}
