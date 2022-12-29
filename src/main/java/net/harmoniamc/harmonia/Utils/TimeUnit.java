package net.harmoniamc.harmonia.Utils;

import java.util.HashMap;

public enum TimeUnit {

    SECOND("Second", "s", 1),
    MINUTE("Minute", "m", 60),
    HOUR("Hour", "h", 60*60),
    DAY("Day", "d", 60*60*24),
    WEEK("Week", "w", 60*60*24*7),
    MONTHS("Months", "mo", 60*60*24*7*30),
    YEAR("Year", "y", 60*60*24*7*30*365);

    private String name;
    private String shortcut;
    private long toSecond;

    private static HashMap<String, TimeUnit> id_shortcut = new HashMap<>();

    private TimeUnit(String name, String shortcut, long toSecond){
        this.name = name;
        this.shortcut = shortcut;
        this.toSecond = toSecond;
    }

    static {
        for(TimeUnit units : values()){
            id_shortcut.put(units.shortcut, units);
        }
    }

    /**
     * @param shortcut
     * @return TimeUnit
     */
    public static TimeUnit getFromShortcut(String shortcut){
        return id_shortcut.get(shortcut);
    }

    public String getName() {
        return name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public long getToSecond() {
        return toSecond;
    }

    public static boolean existFromShortcut(String shortcut){
        return id_shortcut.containsKey(shortcut);
    }
}
