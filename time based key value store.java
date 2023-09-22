import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {
    private Map<String, TreeMap<Integer, String>> data;

    public TimeMap() {
        data = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        data.putIfAbsent(key, new TreeMap<>());
        data.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!data.containsKey(key)) {
            return "";
        }

        TreeMap<Integer, String> treeMap = data.get(key);
        Integer floorKey = treeMap.floorKey(timestamp);

        return (floorKey != null) ? treeMap.get(floorKey) : "";
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        System.out.println(timeMap.get("foo", 1)); // Output: "bar"
        System.out.println(timeMap.get("foo", 3)); // Output: "bar"
        timeMap.set("foo", "bar2", 4);
        System.out.println(timeMap.get("foo", 4)); // Output: "bar2"
        System.out.println(timeMap.get("foo", 5)); // Output: "bar2"
    }
}
